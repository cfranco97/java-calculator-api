package com.example;

import java.math.BigDecimal;

public class CalculationResponse {
    private String requestId;
    private String operationType;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal result;
    private String error;

    // Getters & Setters
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }
    public BigDecimal getA() { return a; }
    public void setA(BigDecimal a) { this.a = a; }
    public BigDecimal getB() { return b; }
    public void setB(BigDecimal b) { this.b = b; }
    public BigDecimal getResult() { return result; }
    public void setResult(BigDecimal result) { this.result = result; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}