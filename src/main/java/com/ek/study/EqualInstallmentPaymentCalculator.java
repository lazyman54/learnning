package com.ek.study;

import java.math.BigDecimal;

public class EqualInstallmentPaymentCalculator {

    public static void main(String[] args) {
        // 贷款本金（以元为单位）
//        BigDecimal totalPrincipal = new BigDecimal("1584358.6");
        BigDecimal totalPrincipal = new BigDecimal("2650000");
        // 贷款期限（以月为单位）
        int loanTerm = 360;
        // 贷款月利率
        BigDecimal interestRate = new BigDecimal("0.042");

        calculateMonthlyPayments(totalPrincipal, interestRate, loanTerm);
    }


    public static void calculateMonthlyPayments(BigDecimal totalPrincipal, BigDecimal interestRate, int loanTerm) {

        BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal("12"));

        for (int i = 1; i <= loanTerm; i++) {

            // 计算每月月供
            BigDecimal monthlyPayment = calculateMonthlyPayment(totalPrincipal, monthlyInterestRate, loanTerm);

            // 计算每月应还利息
            BigDecimal monthlyInterest = calculateMonthlyInterest(totalPrincipal, monthlyInterestRate, loanTerm, i);

            // 计算每月应还本金
            BigDecimal checkMonthPrincipal = calculateMonthlyPrincipalCheck(totalPrincipal, monthlyInterestRate, loanTerm, i);
            System.out.println(String.format("第%d个月的月供是：%1.2f,本金：%1.2f，利息：%1.2f", i, monthlyPayment.doubleValue(), checkMonthPrincipal.doubleValue(), monthlyInterest.doubleValue()));
        }
    }

    /*每月月供额=〔贷款本金×月利率×(1+月利率)^还款月数〕÷〔(1+月利率)^还款月数-1〕；*/
    public static BigDecimal calculateMonthlyPayment(BigDecimal totalPrincipal, BigDecimal monthlyInterestRate, int loanTerm) {

        // 计算每月还款额
        double a = BigDecimal.valueOf(1).add(monthlyInterestRate).doubleValue();
        BigDecimal monthlyRepayment = totalPrincipal.multiply(monthlyInterestRate)
                .multiply(new BigDecimal(Math.pow(a, loanTerm)))
                .divide(new BigDecimal(Math.pow(a, loanTerm) - 1), 2, BigDecimal.ROUND_DOWN);

        return monthlyRepayment;
    }

    /*每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕；*/
    private static BigDecimal calculateMonthlyInterest(BigDecimal totalPrincipal, BigDecimal monthlyInterestRate, int loanTerm, int currentTerm) {
        // 计算月利率
        double a = BigDecimal.valueOf(1).add(monthlyInterestRate).doubleValue();
        // 计算每月应还利息
        BigDecimal monthlyInterest = totalPrincipal.multiply(monthlyInterestRate)
                .multiply((new BigDecimal(Math.pow(a, loanTerm) - Math.pow(a, currentTerm - 1))))
                .divide(new BigDecimal(Math.pow(a, loanTerm) - 1), 2, BigDecimal.ROUND_DOWN);

        return monthlyInterest;
    }

    /*每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕；*/
    public static BigDecimal calculateMonthlyPrincipalCheck(BigDecimal totalPrincipal, BigDecimal monthlyInterestRate, int loanTerm, int currentTerm) {
        // 计算月利率
        double a = BigDecimal.valueOf(1).add(monthlyInterestRate).doubleValue();

        return totalPrincipal.multiply(monthlyInterestRate)
                .multiply(new BigDecimal(Math.pow(a, currentTerm - 1)))
                .divide(new BigDecimal(Math.pow(a, loanTerm) - 1), 2, BigDecimal.ROUND_UP);
    }
}
