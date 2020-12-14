package com.carsonlius.springcloudusercoreprovider.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Fib {
    private static Map<Integer, Long> helperMap = new HashMap<>();

    public static void main(String[] args) {
        Fib fib = new Fib();
        int n = 50;

        Long start1 = (new Date()).getTime();
        long result = fib.violenceHelper(n);
        Long end1 = (new Date()).getTime();

        System.out.println("备忘录模式 N: " + n + " 消耗时间毫秒" + (end1-start1) + " 结果:" + result);

        Long start = (new Date()).getTime();
        result = fib.violence(n);
        Long end = (new Date()).getTime();

        System.out.println("暴力算法N: " + n + " 消耗时间豪秒" + (end-start) + " 结果:" + result);

        System.out.println("相差时间（毫秒）:" +((end - start)- (end1 -start1)) );
    }

    private long violenceHelper(int n){
        if (n < 1) {
            return 0;
        }

     return helper(n);
    }

    private long helper(int n)
    {
        if (n == 1 || n ==2) {
            return 1;
        }

        if (helperMap.containsKey(n)) {
            return helperMap.get(n);
        }

        long result = helper(n-1) + helper(n-2);
        helperMap.put(n, result);
        return result;
    }

    /**
     * 暴力破解
     * @param n
     * 常规递归暴力破解
     * */
    private long violence(int n)
    {
        if (n ==0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return violence(n-1) + violence(n-2);
    }
}
