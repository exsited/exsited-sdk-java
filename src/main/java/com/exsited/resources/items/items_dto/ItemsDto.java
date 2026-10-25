package com.exsited.resources.items.items_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/**
 * Typed views over the v4 item bodies.
 *
 * <p>A DTO is a view of the body. {@code response.getMap()} is the body as it arrived and is
 * where to look when a field here reads {@code null} and you expected otherwise.
 *
 * <p>A field the API sent as {@code null} in every recorded response is declared
 * {@code Object}.
 *
 * <p>Two shape facts:
 *
 * <ul>
 *   <li><b>The sale, purchase and inventories reads answer a stub, not an
 *       item.</b> Where {@code getItem} returns the whole record,
 *       {@code getItemSale} returns {@code {id, name, sale}} - three keys - and its
 *       {@code id} is an <b>Integer</b> ({@code 2}), not the string item id
 *       ({@code "ITEM-0001"}) that every other endpoint uses and that every path
 *       takes. {@link Item} therefore declares {@code String id} and the three
 *       stubs get their own types, rather than one class declaring {@code Object}
 *       and losing the check everywhere.</li>
 *   <li><b>The same field name carries different types in different places.</b>
 *       {@code quantity_on_hand} is a {@code Long} inside
 *       {@code inventories.warehouses[]} and a {@code String} on an
 *       {@link InventoryAdjustment}. Neither is coerced.</li>
 * </ul>
 */
public final class ItemsDto {

    private ItemsDto() {}

    /** A pointer to another record. On an item: custom_form, manager, group. */
    public static final class Reference extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
    }

    /**
     * Whoever created or last updated a record.
     *
     * <p><b>Narrower than the account family's User.</b> On an item it carries
     * {@code {object, name}} and nothing else in any of the 961 measured blocks.
     * The rest are declared so a richer body still reads.
     */
    public static final class User extends Dto {
        public String object;
        public String name;
        public String id;
        public String email;
        public String href;
    }

    /** The custom form an item was created under. Present on all 665 items. */
    public static final class CustomForm extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
    }

    /**
     * One entry of {@code codes}. Measured 1,330 times: {@code name} always a
     * string, {@code value} a string on 614 and null on 716 - an empty slot is
     * normal, not an error.
     */
    public static final class ItemCode extends Dto {
        public String name;
        public String value;
    }

    /** One entry of {@code currencies}. Measured 463 times, all four flags boolean. */
    public static final class ItemCurrency extends Dto {
        public String name;
        public Boolean used_for_sale;
        public Boolean default_for_sale;
        public Boolean used_for_purchase;
        public Boolean default_for_purchase;
        public List<Object> suppliers;
    }

    /**
     * One component of a bundle. Measured 131 times. {@code quantity} is a
     * <b>double</b>, not an int - {@code 1.0} rather than {@code 1}.
     */
    public static final class BundleDetail extends Dto {
        public String id;
        public String name;
        public Double quantity;
        public String uom;
    }

    /** One member of an item family. Measured 94 times - the id and name only. */
    public static final class FamilyDetail extends Dto {
        public String item_id;
        public String item_name;
    }

    /**
     * One variation axis. Measured 258 times across configure_variations and
     * use_variations. The element shape of {@code values} was not established.
     */
    public static final class VariationOption extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
        public List<VariationOptionValue> values;
    }

    /**
     * One package on an item. Measured 131 times. {@code last_updated_by} and
     * {@code last_updated_on} were null on every one.
     */
    public static final class PackageDetail extends Dto {
        public String object;
        public String id;
        public String uuid;
        public String name;
        public String display_name;
        public String status;
        public Long rank;
        public List<PackageDetailItem> items;
        public List<PackageDetailBillingPeriod> billing_periods;
        public List<Object> custom_attributes;
        public String created_on;
        public User created_by;
        public Object last_updated_on;
        public Object last_updated_by;
    }

    /**
     * Whether a price is entered inclusive of tax.
     *
     * <p><b>The key differs by side</b>: the sale block carries
     * {@code sale_price_entered_inclusive_of_tax} and the purchase block
     * {@code purchase_price_entered_inclusive_of_tax}. Both are declared so one
     * class serves both; whichever the body did not send reads null.
     */
    public static final class TaxConfiguration extends Dto {
        public Boolean sale_price_entered_inclusive_of_tax;
        public Boolean purchase_price_entered_inclusive_of_tax;
        public String sale_price_based_on;
        public Reference tax_code;
    }

    /**
     * The pricing block on a sale or purchase. Measured 26 times.
     * {@code latest_used_pricing_version} is the field the nested {@code filter}
     * expression addresses.
     */
    public static final class Pricing extends Dto {
        public String type;
        public Long version;
        public Long latest_used_pricing_version;
        public List<PricingPricingModule> pricing_module;
    }

    /** The charge block on a sale. Measured 14 times. */
    public static final class SaleCharge extends Dto {
        public String type;
        public String price_period;
        public List<SaleChargeProperty> properties;
    }

    /**
     * An item's sale configuration. Measured 14 times. Seven fields were null on
     * all 14 and are declared {@code Object}. {@code default_sale_price} is a
     * double on 13 and null on 1.
     */
    public static final class ItemSale extends Dto {
        public Boolean enabled;
        public Boolean enable_back_order;
        public String invoice_note;
        public Double default_sale_price;
        public String pricing_method;
        public String sale_price_variant;
        public Boolean use_on_sale_price;
        public Boolean tax_exempt_when_sold;
        public TaxConfiguration tax_configuration;
        public SaleCharge charge;
        public Pricing pricing;
        public List<ItemSalePricingLevel> pricing_levels;
        public List<Object> pricing_schedules;
        public List<ItemSalePaymentProperty> payment_properties;
        public Object accounting_code;
        public Object discount_profile;
        public Object pricing_profile;
        public Object shipping_profile;
        public Object sale_price;
        public Object start_date;
        public Object end_date;
        public ItemSaleHeight height;
        public ItemSaleLength length;
        public ItemSaleWeight weight;
        public ItemSaleWidth width;
    }

    /**
     * An item's purchase configuration. Measured 12 times. {@code accounting_code}
     * and {@code pricing_profile} were null on all 12.
     */
    public static final class ItemPurchase extends Dto {
        public Boolean enabled;
        public Boolean supplier_management_enabled;
        public String purchase_order_note;
        public Double default_purchase_price;
        public Boolean tax_exempt_when_purchase;
        public TaxConfiguration tax_configuration;
        public Pricing pricing;
        public List<ItemPurchasePurchaseProperty> purchase_properties;
        public Object accounting_code;
        public Object pricing_profile;
        public List<ItemPurchaseSupplier> suppliers;
    }

    /**
     * The inventory settings block. Measured 12 times. Note
     * {@code quantity_available_for_sale} is a <b>String</b>.
     */
    public static final class InventoryProperties extends Dto {
        public Boolean enabled;
        public Boolean enable_batch_tracking;
        public Boolean enable_serialization;
        public Boolean enable_inventory_costing;
        public Boolean enable_low_stock_notification;
        public Boolean enable_preorder;
        public Boolean enable_reordering;
        public String low_stock_threshold_based_on;
        public String reordering_threshold_based_on;
        public String quantity_available_for_sale;
        public List<Object> quantity_available_for_sale_determination;
        public Boolean use_atp_value_as_quantity_available_for_webhook;
        public Object default_cost_per_unit;
        public List<InventoryPropertiesPreorderConfiguration> preorder_configuration;
        public String preorder_period;
    }

    /**
     * One warehouse's stock line. Measured 10 times.
     *
     * <p><b>The quantities are integers here</b> and each has a {@code _value} twin
     * that is a <b>String</b> - {@code quantity_on_hand: 79999} beside
     * {@code quantity_on_hand_value: "0.00"}. The same names on an
     * {@link InventoryAdjustment} are strings instead. Nothing is coerced either
     * way. {@code warehouse} and {@code uom} were null on all 10.
     */
    public static final class WarehouseInventory extends Dto {
        public Long quantity_on_hand;
        public String quantity_on_hand_value;
        public Double quantity_available;
        public String quantity_available_value;
        public Long quantity_on_order;
        public Double quantity_promised;
        public Long quantity_on_return;
        public Long quantity_on_purchase_return;
        public Reference warehouse;
        public String uom;
    }

    /** An item's inventory block. Measured 12 times. */
    public static final class ItemInventories extends Dto {
        public Boolean enabled;
        public Boolean enable_warehouse_management;
        public String default_warehouse;
        public InventoryProperties inventory_properties;
        public List<WarehouseInventory> warehouses;
    }

    /** One file on a note. The element shape was not established in the sample. */
    public static final class NoteAttachment extends Dto {
        public String object;
        public String id;
        public String name;
        public String href;
        public String content_type;
        public String filename;
        public Long size;
        public String uuid;
    }

    /**
     * One note on an item. Measured 7 times. {@code group}, {@code user},
     * {@code last_updated_by} and {@code last_updated_on} were null on all 7.
     */
    public static class Note extends Dto {
        @Override
        protected String envelopeKey() {
            return "note";
        }

        public String object;
        public String id;
        public String content;
        public Long version;
        public String href;
        public String created_on;
        public User created_by;
        public Reference item;
        public List<NoteAttachment> attachments;
        public List<NoteWatcher> watchers;
        public Object group;
        public Object user;
        public String last_updated_on;
        public NoteLastUpdatedBy last_updated_by;
    }

    /**
     * One item, as {@code getItem} and the listing return it. Measured 668 records.
     *
     * <p>{@code id} is the operator-assigned code - {@code "ITEM-0001"} - and is
     * what every path takes. Declared {@code String} on the strength of 665 of 668;
     * the three exceptions are the sale, purchase and inventories stubs, which have
     * their own types precisely so this one keeps the check.
     *
     * <p>{@code base_uom} (560 records) and {@code image_url} (665) were null on
     * every record. {@code uoms}, {@code custom_attributes} and
     * {@code activity_logs} were present but <b>empty on every record</b>, so their
     * element types are unmeasured.
     *
     * <p>The sub-resource fields arrive only on the endpoint that asks for them. On
     * a plain listing they are absent, not null.
     */
    public static final class Item extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public String object;
        public String id;
        public String name;
        public String display_name;
        public String description;
        public String type;
        public String status;
        public String origin;
        public String invoice_note;
        public Long version;
        public String href;

        public Boolean sale_enabled;
        public Boolean purchase_enabled;
        public Boolean inventory_enabled;
        public Boolean variations_enabled;

        /** The <b>read</b> spelling. Written as {@code is_parent} / {@code is_child}. */
        public Boolean parent;
        public Boolean child;
        public String parent_item_id;

        public CustomForm custom_form;
        public Reference manager;
        /** Set on 2 of 665 - nearly always null. */
        public Reference group;

        public String created_on;
        public User created_by;
        /** Both null on 369 of 665 - an item never edited. */
        public String last_updated_on;
        public User last_updated_by;

        public List<ItemCode> codes;
        public List<ItemCurrency> currencies;

        public ItemSale sale;
        public ItemPurchase purchase;
        public ItemInventories inventories;
        public List<BundleDetail> bundle_details;
        public List<FamilyDetail> family_details;
        public List<PackageDetail> package_details;
        public List<VariationOption> configure_variations;
        public List<VariationOption> use_variations;
        public List<Object> custom_objects;

        /** Present but empty on every record measured. */
        public List<Object> uoms;
        public List<ItemCustomAttribute> custom_attributes;
        public List<Object> activity_logs;

        /** Null on every record measured. */
        public Object base_uom;
        public Object image_url;
    }

    /**
     * The listing envelope. Items page by <b>cursor</b>, like accounts: all 51
     * measured pagination blocks carry {@code has_more}, {@code limit},
     * {@code links}, {@code next_cursor} and {@code prev_cursor}. There is no
     * offset shape here.
     */
    public static final class ItemList extends Dto {
        public String object;
        public String url;
        public List<Item> data;
        public PaginationDto pagination;
    }

    /**
     * What {@code getItemSale} answers: a <b>stub</b>, not the item.
     *
     * <p>Three keys - {@code {id, name, sale}} - and {@code id} is a <b>Long</b>,
     * not the string code the path took. No envelope: they sit at the top level.
     * Use {@link Item} for anything that returns a real item.
     */
    public static final class ItemSaleView extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Long id;
        public String name;
        public ItemSale sale;
    }

    /** {@code {id, name, purchase}}, {@code id} a Long. */
    public static final class ItemPurchaseView extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Long id;
        public String name;
        public ItemPurchase purchase;
    }

    /** {@code {id, name, inventories}}, {@code id} a Long. */
    public static final class ItemInventoriesView extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public String id;
        public String name;
        public ItemInventories inventories;
    }

    /**
     * One inventory movement. Measured 20 records.
     *
     * <p><b>Every quantity is a String here</b> - {@code "79999"}, not
     * {@code 79999} - the opposite of {@link WarehouseInventory}, where the same
     * names are Longs. {@code created_by} and {@code last_updated_by} were null on
     * all 20.
     */
    public static final class InventoryAdjustment extends Dto {
        @Override
        protected String envelopeKey() {
            return "inventory_adjustment";
        }

        public String object;
        public String id;
        public String item_id;
        public String item_name;
        public String warehouse_name;
        public String adjustment_amount;
        public String adjustment_type;
        public String reference;
        public String status;
        public String quantity_on_hand;
        public String quantity_available;
        public String quantity_on_order;
        public String quantity_promised;
        public String quantity_on_return;
        public String quantity_on_purchase_return;
        public String quantity_on_reserve;
        public String created_on;
        public String last_updated_on;
        public String href;
        public Object created_by;
        public Object last_updated_by;
    }

    /** The listing envelope for inventory adjustments. */
    public static final class InventoryAdjustmentList extends Dto {
        public String object;
        public String url;
        public List<InventoryAdjustment> data;
        public PaginationDto pagination;
    }

    /** The listing envelope for an item's notes. */
    public static final class NoteList extends Dto {
        public String object;
        public String url;
        public List<Note> data;
        public PaginationDto pagination;
    }

    /**
     * The listing envelope for a note's attachments.
     *
     * <p>Unverified: the one recorded call answered 404, so the element shape is
     * carried over from the account family rather than measured here.
     */
    public static final class NoteFileList extends Dto {
        public String object;
        public String url;
        public List<NoteAttachment> data;
        public PaginationDto pagination;
    }

    // Every write body is wrapped in an "item" envelope, the sale, purchase and
    // inventories writes included: {"item": {"sale": {...}}}, not {"sale": {...}}.
    // The one exception is the image upload, whose single field sits at the top.

    /**
     * The body {@code createItem} takes.
     *
     * <p>Measured over the 9 recorded creates: {@code type} and {@code name} on all
     * 9 and the minimum that works. {@code type} takes {@code "STANDARD"},
     * {@code "BUNDLE"}, {@code "FAMILY"} or {@code "VARIATION_BASED"}.
     *
     * <p><b>The family flags are written {@code is_parent}/{@code is_child} and
     * read back as {@code parent}/{@code child}.</b>
     *
     * <p>{@code group} rejects an unknown value with
     * {@code 400 internal_error "Invalid group"} - 5 of the 9 recorded creates
     * failed exactly that way.
     */
    public static final class ItemCreate extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public String name;
        public String type;
        public String display_name;
        public String description;
        public String origin;
        public String invoice_note;
        public String manager;
        public String custom_form;
        public String group;
        /** The <b>write</b> spelling. Reads back as {@code parent} / {@code child}. */
        public Boolean is_parent;
        public Boolean is_child;
        public String parent_item_id;
        public List<ItemCurrency> currencies;
        public List<Object> uoms;
        public Object sale;
        public Object purchase;
        public Object inventories;
        public List<Object> bundle_details;
    }

    /** The body {@code updateItemInformation} takes. Partial: send only what changes. */
    public static final class ItemInformationUpdate extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public String name;
        public String display_name;
        public String description;
        public String origin;
        public String invoice_note;
        public String manager;
        public String custom_form;
    }

    /**
     * The body the sale create and update take:
     * {@code {"item": {"sale": {...}, "currencies": [...]}}} - the sale block sits
     * <em>inside</em> the item envelope, and {@code currencies} sits beside it
     * rather than within it.
     *
     * <p>{@code sale} is {@code Object}: the recorded updates sent
     * {@code {"default_sale_price": 75}} and
     * {@code {"charge": {"type": "RECURRING"}}}, too thin a sample to declare a
     * shape.
     */
    public static final class ItemSaleWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Object sale;
        public List<ItemCurrency> currencies;
    }

    /** {@code {"item": {"purchase": {...}, "currencies": [...]}}}. */
    public static final class ItemPurchaseWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Object purchase;
        public List<ItemCurrency> currencies;
    }

    /**
     * {@code {"item": {"inventories": {...}}}}.
     *
     * <p>Turning inventory <b>off</b> is refused with
     * {@code 400 "Unable to disable Inventory Management"} once stock exists. The
     * write spells warehouse management {@code warehouse_enabled} where the read
     * returns {@code enable_warehouse_management}.
     */
    public static final class ItemInventoriesWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Object inventories;
    }

    /**
     * {@code {"item": {"inventories": {"warehouses": [{name, quantity,
     * accounting_code}]}}}} - note {@code name}, where the read side calls the same
     * thing {@code warehouse}.
     */
    public static final class InventoryAdd extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Object inventories;
    }

    /**
     * The body {@code updateItemVariations} takes, in one of two measured forms: a
     * parent declares {@code configure_variations}; a child sets
     * {@code parent_item_id} and {@code use_variations}. Both recorded calls
     * answered 400.
     */
    public static final class ItemVariationsWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public List<Object> configure_variations;
        public List<Object> use_variations;
        public String parent_item_id;
    }

    /** {@code {"item": {"bundle_details": [{id, quantity}]}}} - a <b>bare list</b>. */
    public static final class ItemBundleDetailsWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public List<Object> bundle_details;
    }

    /**
     * {@code {"item": {"family_details": {"add": ["ITEM-0002"]}}}} - an <b>object
     * with an {@code add} list</b>, the opposite of how {@code bundle_details} is
     * written. The recorded call answered 405.
     */
    public static final class ItemFamilyDetailsWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Object family_details;
    }

    /** {@code {"item": {"package_details": {...}}}}. The recorded call answered 405. */
    public static final class ItemPackageDetailsWrite extends Dto {
        @Override
        protected String envelopeKey() {
            return "item";
        }

        public Object package_details;
    }

    /**
     * Base64 in a JSON body, as on an account - <b>not</b> a multipart upload, and
     * the one write in this family with no {@code item} envelope. The recorded call
     * answered 500.
     */
    public static final class ItemImageUpload extends Dto {
        public String image_base64;
    }

    /** The single-note envelope: {@code {"note": {...}}}. */
    public static final class NoteView extends Note {
        public NoteViewLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public List<NoteViewWatcher> watchers;
    }

    /** What {@code get_item_note_file} answers, under {@code file}. */
    public static final class ItemNoteFile extends Dto {
        @Override
        protected String envelopeKey() {
            return "file";
        }

        public String content_type;
        public String filename;
        public String href;
        public Long size;
        public String uuid;
    }

    /** One entry of a {@link Item}'s {@code custom_attributes}. */
    public static final class ItemCustomAttribute extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
        public String type;
        public String value;
    }

    /** One entry of a {@link NoteView}'s {@code watchers}. */
    public static final class NoteViewWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link NoteView}'s {@code last_updated_by}. */
    public static final class NoteViewLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link ItemSale}'s {@code pricing_levels}. */
    public static final class ItemSalePricingLevel extends Dto {
        public String name;
        public String uuid;
    }

    /** One entry of a {@link ItemSale}'s {@code payment_properties}. */
    public static final class ItemSalePaymentProperty extends Dto {
        public String name;
        public String value;
    }

    /** A {@link ItemSale}'s {@code width}. */
    public static final class ItemSaleWidth extends Dto {
        public String uom;
        public Double value;
    }

    /** A {@link ItemSale}'s {@code height}. */
    public static final class ItemSaleHeight extends Dto {
        public String uom;
        public Double value;
    }

    /** A {@link ItemSale}'s {@code length}. */
    public static final class ItemSaleLength extends Dto {
        public String uom;
        public Double value;
    }

    /** A {@link ItemSale}'s {@code weight}. */
    public static final class ItemSaleWeight extends Dto {
        public String uom;
        public Double value;
    }

    /** One entry of a {@link ItemPurchase}'s {@code purchase_properties}. */
    public static final class ItemPurchasePurchaseProperty extends Dto {
        public String name;
        public String value;
    }

    /** One entry of a {@link ItemPurchase}'s {@code suppliers}. */
    public static final class ItemPurchaseSupplier extends Dto {
        /** Null in every record measured, so untyped. */
        public Object accounting_code;
        public Double default_purchase_price;
        /** Null in every record measured, so untyped. */
        public Object id;
        public String name;
        public Pricing pricing;
        /** Null in every record measured, so untyped. */
        public Object purchase_order_note;
        public ItemPurchaseSupplierTaxConfiguration tax_configuration;
        public Boolean tax_exempt_when_purchase;
    }

    /** One entry of a {@link VariationOption}'s {@code values}. */
    public static final class VariationOptionValue extends Dto {
        public String name;
        public String uuid;
    }

    /** One entry of a {@link PackageDetail}'s {@code items}. */
    public static final class PackageDetailItem extends Dto {
        public String item_id;
        public String item_name;
        public Double quantity;
        /** Null in every record measured, so untyped. */
        public Object uom;
    }

    /** One entry of a {@link PackageDetail}'s {@code billing_periods}. */
    public static final class PackageDetailBillingPeriod extends Dto {
        public String name;
        public Long rank;
    }

    /** One entry of a {@link Note}'s {@code watchers}. */
    public static final class NoteWatcher extends Dto {
        public String email;
        public String name;
        public String type;
    }

    /** A {@link Note}'s {@code last_updated_by}. */
    public static final class NoteLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link SaleCharge}'s {@code properties}. */
    public static final class SaleChargeProperty extends Dto {
        public String name;
        public String value;
    }

    /** One entry of a {@link Pricing}'s {@code pricing_module}. */
    public static final class PricingPricingModule extends Dto {
        /** Null in every record measured, so untyped. */
        public Object average_purchase_price;
        public String currency;
        /** Null in every record measured, so untyped. */
        public Object last_purchase_price;
        public Double price;
        public String price_type;
        public String pricing_level;
        public String unit;
    }

    /** A {@link ItemPurchaseSupplier}'s {@code tax_configuration}. */
    public static final class ItemPurchaseSupplierTaxConfiguration extends Dto {
        public Boolean purchase_price_entered_inclusive_of_tax;
        public Reference tax_code;
    }

    /** One entry of a {@link InventoryProperties}'s {@code preorder_configuration}. */
    public static final class InventoryPropertiesPreorderConfiguration extends Dto {
        public String end_date;
        public String name;
        public String start_date;
    }
}
