package com.exsited.resources.shipping_packages.shipping_packages_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the shipping package bodies. */
public final class ShippingPackagesDto {

    private ShippingPackagesDto() {}

    /** The settings shipping package body, under {@code shipping_package}. */
    public static final class SettingsShippingPackage extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/shipping_packages",
                "GET /api/{api_version}/settings/shipping_packages/{shipping_package_id}",
                "POST /api/{api_version}/settings/shipping_packages",
                "PATCH /api/{api_version}/settings/shipping_packages/{shipping_package_id}");

        @Override
        protected String envelopeKey() {
            return "shipping_package";
        }

        public Object created_by;
        public String created_on;
        public Object cubic;
        public Object cubic_uom;
        public String description;
        public String display_name;
        public Double height;
        public Object height_uom;
        public String href;
        public String id;
        public Object last_updated_by;
        public String last_updated_on;
        public Double length;
        public Object length_uom;
        public String name;
        public String object;
        public String status;
        public Double weight;
        public Object weight_uom;
        public Double width;
        public Object width_uom;
    }

    /** One page of {@code SettingsShippingPackage} records, under {@code data}. */
    public static final class SettingsShippingPackageList extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/shipping_packages");

        public List<SettingsShippingPackage> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
