package com.richa.easyride.api.response;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cycle implements Serializable {

@SerializedName("cycle_id")
@Expose
private Integer cycleId;
@SerializedName("cycle_name")
@Expose
private String cycleName;
@SerializedName("rental_rate")
@Expose
private Integer rentalRate;
@SerializedName("description")
@Expose
private String description;
@SerializedName("quantity")
@Expose
private Integer quantity;
@SerializedName("availability")
@Expose
private String availability;
@SerializedName("images")
@Expose
private List<String> images = null;
@SerializedName("categories")
@Expose
private List<Integer> categories = null;

public Integer getCycleId() {
return cycleId;
}

public void setCycleId(Integer cycleId) {
this.cycleId = cycleId;
}

public String getCycleName() {
return cycleName;
}

public void setCycleName(String cycleName) {
this.cycleName = cycleName;
}

public Integer getRentalRate() {
return rentalRate;
}

public void setRentalRate(Integer rentalRate) {
this.rentalRate = rentalRate;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public Integer getQuantity() {
return quantity;
}

public void setQuantity(Integer quantity) {
this.quantity = quantity;
}

public String getAvailability() {
return availability;
}

public void setAvailability(String availability) {
this.availability = availability;
}

public List<String> getImages() {
return images;
}

public void setImages(List<String> images) {
this.images = images;
}

public List<Integer> getCategories() {
return categories;
}

public void setCategories(List<Integer> categories) {
this.categories = categories;
}

}