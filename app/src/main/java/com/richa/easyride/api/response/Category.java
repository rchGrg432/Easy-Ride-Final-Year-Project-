package com.richa.easyride.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

@SerializedName("category_id")
@Expose
private Integer categoryId;
@SerializedName("category_name")
@Expose
private String categoryName;
@SerializedName("category_image")
@Expose
private String categoryImage;

public Integer getCategoryId() {
return categoryId;
}

public void setCategoryId(Integer categoryId) {
this.categoryId = categoryId;
}

public String getCategoryName() {
return categoryName;
}

public void setCategoryName(String categoryName) {
this.categoryName = categoryName;
}

public String getCategoryImage() {
return categoryImage;
}

public void setCategoryImage(String categoryImage) {
this.categoryImage = categoryImage;
}

}