package com.richa.easyride.api.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetRentalResponse {



    @SerializedName("rental_history")
    @Expose
    private List<AllRentalsResponse> rentalHistory = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public List<AllRentalsResponse> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(List<AllRentalsResponse> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
