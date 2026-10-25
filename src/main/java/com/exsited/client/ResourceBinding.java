package com.exsited.client;

import com.exsited.errors.SdkAttributeError;
import com.exsited.errors.SdkUsageError;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/** The resource registry: which names exist and what each binds to a client. */
public final class ResourceBinding {

    private ResourceBinding() {}

    // Copied from the binding map: a method reference is a fresh object each time it is
    // evaluated, so registering those lines again would read as rebinding every name.
    private static final Map<String, Function<ExsitedClient, ? extends BoundResource>>
            RESOURCE_FACTORIES = new ConcurrentHashMap<>(ResourceBindings.bindings());

    /** Make a resource reachable as {@code client.resource(name)}; a clash is refused. */
    public static void registerResourceModule(String resourceName,
            Function<ExsitedClient, ? extends BoundResource> factory) {
        if (resourceName == null || resourceName.isBlank()) {
            throw new SdkUsageError("cannot register a resource under a blank name: "
                    + "pass the name it should be reached by, as in "
                    + "registerResourceModule(\"creditNotes\", CreditNotes::new)");
        }
        if (!isIdentifier(resourceName)) {
            // registeredResourceNames() promises every name it lists reads as an accessor.
            throw new SdkUsageError("cannot register resource '" + resourceName
                    + "': a resource name must read as an identifier - letters, digits and "
                    + "underscores, not starting with a digit - because it names an accessor");
        }
        if (factory == null) {
            throw new SdkUsageError("cannot register resource '" + resourceName
                    + "' with no factory: pass the constructor, as in CreditNotes::new");
        }
        Function<ExsitedClient, ? extends BoundResource> registered =
                RESOURCE_FACTORIES.putIfAbsent(resourceName, factory);
        if (registered != null && registered != factory) {
            throw new SdkUsageError("cannot register resource '" + resourceName
                    + "' against a second factory: it is already registered, and clients "
                    + "built before the change would keep the first one - pick another name");
        }
    }

    /** Every registered resource name, sorted, as a snapshot. */
    public static List<String> registeredResourceNames() {
        return RESOURCE_FACTORIES.keySet().stream().sorted().toList();
    }

    /** The factory registered under a name, or an {@link SdkAttributeError}. */
    public static Function<ExsitedClient, ? extends BoundResource> resolveResourceModule(
            String resourceName) {
        Function<ExsitedClient, ? extends BoundResource> factory =
                resourceName == null ? null : RESOURCE_FACTORIES.get(resourceName);
        if (factory == null) {
            List<String> available = registeredResourceNames();
            throw new SdkAttributeError("no resource '" + resourceName + "'; available: "
                    + String.join(", ", available));
        }
        return factory;
    }

    /** Whether a name reads as a Java identifier, which is what an accessor name is. */
    private static boolean isIdentifier(String name) {
        if (!Character.isJavaIdentifierStart(name.charAt(0))) {
            return false;
        }
        for (int index = 1; index < name.length(); index++) {
            if (!Character.isJavaIdentifierPart(name.charAt(index))) {
                return false;
            }
        }
        return true;
    }
}
