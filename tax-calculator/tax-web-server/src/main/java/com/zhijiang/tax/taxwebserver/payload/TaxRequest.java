package com.zhijiang.tax.taxwebserver.payload;

public class TaxRequest {
    private double income;
    private double deduction;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }
}
