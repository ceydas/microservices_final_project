package com.ceydog.microservices_final_project.plt.enums;

public enum EnumValidCity {
    LONDON("London"),
    BARCELONA("Barcelona"),
    ANKARA("Ankara"),
    TOKYO("Tokyo"),
    MUMBAI("Mumbai")
    ;

    private String cityName;

    EnumValidCity(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }


    @Override
    public String toString() {
        return cityName;
    }
}
