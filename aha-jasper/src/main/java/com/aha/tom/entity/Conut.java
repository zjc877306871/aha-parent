package com.aha.tom.entity;

public class Conut {
    private Double amount1;
    private Double amount2;
    private Double amount3;

    public Conut(Double amount1, Double amount2, Double amount3) {
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.amount3 = amount3;
    }

    public Double getAmount1() {
        return amount1;
    }

    public void setAmount1(Double amount1) {
        this.amount1 = amount1;
    }

    public Double getAmount2() {
        return amount2;
    }

    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    public Double getAmount3() {
        return amount3;
    }

    public void setAmount3(Double amount3) {
        this.amount3 = amount3;
    }
}