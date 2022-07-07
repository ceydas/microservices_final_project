package com.ceydog.microservices_final_project.plt.enums;

public enum EnumPltCategory {

    GOOD("Good"),
    SATISFACTORY("Satisfactory"),
    MODERATE("Moderate"),
    POOR("Poor"),
    SEVERE("Severe"),
    HAZARDOUS("Hazardous")
    ;

    private String categoryName;

    EnumPltCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }


    @Override
    public String toString() {
        return categoryName;
    }
}
