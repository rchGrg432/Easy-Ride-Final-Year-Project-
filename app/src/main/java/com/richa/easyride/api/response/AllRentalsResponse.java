package com.richa.easyride.api.response;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllRentalsResponse implements Serializable {

        @SerializedName("rental_id")
        @Expose
        private Integer rentalId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("cycle_name")
        @Expose
        private String cycleName;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("pickup_date")
        @Expose
        private String pickupDate;
        @SerializedName("pickup_time")
        @Expose
        private String pickupTime;
        @SerializedName("dropoff_time")
        @Expose
        private String dropoffTime;
        @SerializedName("pay_status")
        @Expose
        private String payStatus;
        @SerializedName("payment_refrence")
        @Expose
        private String paymentRefrence;

        public Integer getRentalId() {
            return rentalId;
        }

        public void setRentalId(Integer rentalId) {
            this.rentalId = rentalId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCycleName() {
            return cycleName;
        }

        public void setCycleName(String cycleName) {
            this.cycleName = cycleName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPickupDate() {
            return pickupDate;
        }

        public void setPickupDate(String pickupDate) {
            this.pickupDate = pickupDate;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public String getDropoffTime() {
            return dropoffTime;
        }

        public void setDropoffTime(String dropoffTime) {
            this.dropoffTime = dropoffTime;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getPaymentRefrence() {
            return paymentRefrence;
        }

        public void setPaymentRefrence(String paymentRefrence) {
            this.paymentRefrence = paymentRefrence;
        }


}

