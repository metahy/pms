package com.pms.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 主要用于计算超大整数超大次幂然后对超大的整数取模。
 * 我在网上查询到这个算法叫做"蒙哥马利算法"。
 */
public class Exponentiation {

    /**
     * 超大整数超大次幂然后对超大的整数取模
     * (base ^ exponent) mod n
     *
     * @param base
     * @param exponent
     * @param n
     * @return
     */
    public BigInteger expMode(BigInteger base, BigInteger exponent, BigInteger n) {
        char[] binaryArray = new StringBuilder(exponent.toString(2)).reverse().toString().toCharArray();
        int r = binaryArray.length;
        List<BigInteger> baseArray = new ArrayList<BigInteger>();

        BigInteger preBase = base;
        baseArray.add(preBase);
        for (int i = 0; i < r - 1; i++) {
            BigInteger nextBase = preBase.multiply(preBase).mod(n);
            baseArray.add(nextBase);
            preBase = nextBase;
        }
        BigInteger a_w_b = this.multi(baseArray.toArray(new BigInteger[baseArray.size()]), binaryArray, n);
        return a_w_b.mod(n);
    }

    private BigInteger multi(BigInteger[] array, char[] bin_array, BigInteger n) {
        BigInteger result = BigInteger.ONE;
        for (int index = 0; index < array.length; index++) {
            BigInteger a = array[index];
            if (bin_array[index] == '0') {
                continue;
            }
            result = result.multiply(a);
            result = result.mod(n);
        }
        return result;
    }
}
