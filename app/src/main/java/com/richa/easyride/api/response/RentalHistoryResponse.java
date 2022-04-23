package com.richa.easyride.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RentalHistoryResponse {
    @SerializedName("rental_history")
    @Expose
    private List<RentalHistory> rentalHistory = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public List<RentalHistory> getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(List<RentalHistory> rentalHistory) {
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




//    @SerializedName("rental_history")
//    @Expose
//    private List<RentalHistory> rentalHistory = null;
//    @SerializedName("error")
//    @Expose
//    private Boolean error;
//    @SerializedName("message")
//    @Expose
//    private String message;
//
//    public List<RentalHistory> getRentalHistory() {
//        return rentalHistory;
//    }
//
//    public void setRentalHistory(List<RentalHistory> rentalHistory) {
//        this.rentalHistory = rentalHistory;
//    }
//
//    public Boolean getError() {
//        return error;
//    }
//
//    public void setError(Boolean error) {
//        this.error = error;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
