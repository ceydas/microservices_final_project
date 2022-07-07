package com.ceydog.microservices_final_project.plt.dto.category;

import com.ceydog.microservices_final_project.plt.enums.EnumPltCategory;

public class PltCategoryUtil {
    public static EnumPltCategory getCategoryForCO(Double co){

        if (co < 0 ){
            throw new RuntimeException("CO Level cannot be negative!");
        }
        else if (co >= 0 && co <= 50 ) {
            //If CO is between 0...50 inclusive it's GOOD.
            return EnumPltCategory.GOOD;
        }
        else if (co >= 51 && co <= 100){
            return EnumPltCategory.SATISFACTORY ;
        }

        else if ( co >= 101 && co <= 150){
            return EnumPltCategory.MODERATE;
        }
        else if (co >= 151 && co <= 200){
            return EnumPltCategory.POOR;
        }
        else if ( co >= 201 && co <= 300){
            return EnumPltCategory.SEVERE;
        }
        else if ( co >= 301){
            return EnumPltCategory.HAZARDOUS;
        }

        return EnumPltCategory.HAZARDOUS;
    }

    public static EnumPltCategory getCategoryForO3(Double o3){

        if ( o3 < 0){
            throw new RuntimeException("O3 Level cannot be negative!");
        }
        else if (o3 >= 0 && o3 <= 50 ) {
            //If CO is between 0...50 inclusive it's GOOD.
            return EnumPltCategory.GOOD;
        }
        else if (o3 >= 51 && o3 <= 100){
            return EnumPltCategory.SATISFACTORY ;
        }

        else if ( o3 >= 101 && o3 <= 168){
            return EnumPltCategory.MODERATE;
        }
        else if (o3 >= 169 && o3 <= 208){
            return EnumPltCategory.POOR;
        }
        else if ( o3 >= 209 && o3 <= 748){
            return EnumPltCategory.SEVERE;
        }
        else if ( o3 > 748){
            return EnumPltCategory.HAZARDOUS;
        }

        return EnumPltCategory.HAZARDOUS;
    }

    public static EnumPltCategory getCategoryForSO2(Double so2){

        if ( so2 < 0){
            throw new RuntimeException("O3 Level cannot be negative!");
        }
        else if (so2 >= 0 && so2 <= 40 ) {
            //If CO is between 0...50 inclusive it's GOOD.
            return EnumPltCategory.GOOD;
        }
        else if (so2 >= 41 && so2 <= 80){
            return EnumPltCategory.SATISFACTORY ;
        }

        else if ( so2 >= 81 && so2 <= 380){
            return EnumPltCategory.MODERATE;
        }
        else if (so2 >= 381 && so2 <= 800){
            return EnumPltCategory.POOR;
        }
        else if ( so2 >= 801 && so2 <= 1600){
            return EnumPltCategory.SEVERE;
        }
        else if ( so2 > 1600){
            return EnumPltCategory.HAZARDOUS;
        }

        return EnumPltCategory.HAZARDOUS;
    }

}
