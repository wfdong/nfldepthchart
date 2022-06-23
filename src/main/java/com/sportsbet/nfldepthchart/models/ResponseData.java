package com.sportsbet.nfldepthchart.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResponseData implements Serializable{
    private Map<Object, Object> response;

    public Map<Object, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<Object, Object> response) {
        this.response = response;
    }

    public void addAttribute(String key, Object value){
        if(response == null){
            this.response = new HashMap<>();
        }
        this.response.put(key, value);
    }

    public void clear(){
        if(response!=null){
            response.clear();
        }
    }
}
