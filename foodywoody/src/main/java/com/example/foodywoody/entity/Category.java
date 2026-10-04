package com.example.foodywoody.entity;

public enum Category {
    PIZZA("Pizza", "🍕"),
    BURGER("Burgers", "🍔"),
    BEVERAGE("Beverages", "🥤"),
    DESSERT("Desserts", "🍰"),
    SALAD("Salads", "🥗");

    private final String label;
    private final String icon;

    Category(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public String getIcon() {
        return icon;
    }
}
