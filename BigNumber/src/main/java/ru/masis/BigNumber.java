package ru.masis;

import java.math.BigInteger;

public class BigNumber {
    private BigInteger bigInteger = new BigInteger("2");
    public BigInteger raiseToAPower() {
        bigInteger = bigInteger.pow(1000);
        return bigInteger;
    }

    public static void main(String[] args) {
        BigNumber power = new BigNumber();
        System.out.println(power.raiseToAPower());
    }
}
