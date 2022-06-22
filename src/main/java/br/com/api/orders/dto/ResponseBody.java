package br.com.api.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody {
    private Object principal;
    public Object getPrincipal() {
        return principal;
    }
    public void setPrincipal(Object principal) {
        this.principal = principal;
    }
    
}
