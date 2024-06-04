package com.cyphersoft.osahaneat.payload.request;

public class OrderRequest {

    private int userId;
    private int restaurantId;
    private int [] foodIds;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int[] getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(int[] foodIds) {
        this.foodIds = foodIds;
    }
}
