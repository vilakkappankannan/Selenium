package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class GenerateRandomValue {

    public static void main(String[] args) {

        String generatedString = RandomStringUtils.randomAlphanumeric(64);
        System.out.println(generatedString);

    }

//    public String randomValues(String randomGeneratedValues) {
//
//        String generatedString = RandomStringUtils.randomAlphanumeric(64);
//        return (generatedString);
//    }
}
