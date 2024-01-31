package uz.pdp.bookstoreproject.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ConfigUtils {
    public static SimpleDateFormat simpleDateFormatter(){
        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    }

    public static DateTimeFormatter dateTimeFormatter(){
        return DateTimeFormatter.ISO_DATE_TIME;
    }
}