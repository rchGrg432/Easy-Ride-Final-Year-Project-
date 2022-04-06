package com.richa.easyride.api.response;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CycleResponse implements Serializable {

@SerializedName("cycles")
@Expose
private List<Cycle> cycles = null;
@SerializedName("error")
@Expose
private Boolean error;
@SerializedName("message")
@Expose
private String message;

public List<Cycle> getCycles() {
return cycles;
}

public void setCycles(List<Cycle> cycles) {
this.cycles = cycles;
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