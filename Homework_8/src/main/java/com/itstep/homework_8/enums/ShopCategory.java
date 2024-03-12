package com.itstep.homework_8.enums;

import lombok.Getter;

@Getter
public enum ShopCategory {
    GROCERY("Grocery"),
    CLOTHING("Clothing"),
    ELECTRONICS("Electronics"),
    BOOKS("Books"),
    FURNITURE("Furniture"),
    SPORTS("Sports"),
    BEAUTY("Beauty"),
    HOME_APPLIANCES("Home Appliances"),
    JEWELRY("Jewelry"),
    TOYS("Toys"),
    AUTOMOTIVE("Automotive"),
    MUSIC("Music"),
    PET_SUPPLIES("Pet Supplies"),
    OUTDOORS("Outdoors"),
    HEALTH("Health"),
    ARTS_CRAFTS("Arts & Crafts"),
    STATIONERY("Stationery");

    private final String displayName;

    ShopCategory(String displayName) {
        this.displayName = displayName;
    }

}
