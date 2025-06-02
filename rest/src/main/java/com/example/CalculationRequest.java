package com.example;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class CalculationRequest  {
    private String requestId;
    private String operationType;
    private BigDecimal a;
    private BigDecimal b;

    // default constructor
    public CalculationRequest() {
    }

    // constructor
    public CalculationRequest(String requestId, String operationType, BigDecimal a, BigDecimal b) {
        this.requestId = requestId;
        this.operationType = operationType;
        this.a = a;
        this.b = b;
    }

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

}
