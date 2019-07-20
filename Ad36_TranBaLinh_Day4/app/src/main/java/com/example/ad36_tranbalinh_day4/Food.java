package com.example.ad36_tranbalinh_day4;

import java.io.Serializable;

public class Food implements Serializable {
    private String foodName;
    private float foodCost;
    private int foodCount;
    private int foodIcon;

    public Food(String foodName, float foodCost, int foodCount, int foodIcon) {
        this.foodName = foodName;
        this.foodCost = foodCost;
        this.foodCount = foodCount;
        this.foodIcon = foodIcon;
    }

    public Food() {
        this.foodName = null;
        this.foodCost = 0;
        this.foodCount = 0;
        this.foodIcon = 0;
    }
    public double getPrince(){
        return foodCount*foodCost;
    }
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(float foodCost) {
        this.foodCost = foodCost;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getFoodIcon() {
        return foodIcon;
    }

    public void setFoodIcon(int foodIcon) {
        this.foodIcon = foodIcon;
    }
}
