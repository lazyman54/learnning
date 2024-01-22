package com.ek.study;

public class HouseLoan {

    private float currentLoanAmount;
    private float loanRate;

    private int repayYear;

    private int repayPeriods;

    public HouseLoan(float currentLoanAmount, float loanRate, int repayYear, int repayPeriods) {
        this.currentLoanAmount = currentLoanAmount;
        this.loanRate = loanRate;
        this.repayYear = repayYear;
        this.repayPeriods = repayPeriods;
    }

    private void Calc() {
        float currentInterest = currentLoanAmount * loanRate / 12;
        float currentPrincipal = currentLoanAmount / repayYear / 12;
        float cp = currentLoanAmount/(repayYear*12-repayPeriods);
        System.out.println(currentInterest);
        System.out.println(currentPrincipal);
        System.out.println(cp);
    }

    public static void main(String[] args) {
        new HouseLoan(200000f, 0.033f, 3, 1).Calc();
        float a =200000f;
        float b = 0.033f;
        float c = b/12;

        double ef = a * c * Math.pow(1+c, 36)/(Math.pow(1+c, 36)-1);
        System.out.println(ef);

    }
}
