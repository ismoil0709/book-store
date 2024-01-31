package uz.pdp.bookstoreproject.util;

import java.util.Random;

public class GenerateCode {
    private static Long code;
    private static void generateNumber(){
        code = (new Random().nextLong() % 5) + 1;
    }
    public static Long getCode(){
        Long c = code;
        generateNumber();
        return c;
    }
}
