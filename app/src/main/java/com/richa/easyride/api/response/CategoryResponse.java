package com.richa.easyride.api.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse {

@SerializedName("categories")
@Expose
private List<Category> categories = null;
@SerializedName("error")
@Expose
private Boolean error;
@SerializedName("message")
@Expose
private String message;

public List<Category> getCategories() {
return categories;
}

public void setCategories(List<Category> categories) {
this.categories = categories;
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