package com.useraccounttranactiontest.myAccount.customer.service.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class transactionsUtils {

    public static double getRandomTransactionAmount(double max){
        double random = ThreadLocalRandom.current().nextDouble(0, max);
        return random;
    }

    public static String generateRandomDescription(){
        String uniqString = generateString(10);
        return "Transaction description sample with"+uniqString;
    }

    private static String generateString(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }
}
