package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.math.BigDecimal;

public class CalculationResponse implements Serializable {
    private String requestId;
    private String operationType;
    private BigDecimal a; // first value
    private BigDecimal b; // second value
    private BigDecimal result;
    private String error;

    ObjectMapper om = new ObjectMapper();

    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getOperationType() {
        return operationType;
    }
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    public BigDecimal getA() {
        return a;
    }
    public void setA(BigDecimal a) {
        this.a = a;
    }
    public BigDecimal getB() {
        return b;
    }
    public void setB(BigDecimal b) {
        this.b = b;
    }
    public BigDecimal getResult() {
        return result;
    }
    public void setResult(BigDecimal result) {
        this.result = result;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getError() { 
        return error;
    }

    @Override
    public String toString() {return  "The " + operationType + " between " + a + " " + b + " is: " + result;}


}
