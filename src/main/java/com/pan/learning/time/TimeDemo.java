package com.pan.learning.time;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeDemo{

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static void main(String[] args) {

        Instant instant = Instant.now();
        System.out.printf("Instant now : %s \n", instant);
        Calendar cal = Calendar.getInstance();
        Date d = new Date();
        Clock clock = Clock.systemUTC();
        System.out.printf("Current time millis : %s \n", System.currentTimeMillis() );
        System.out.printf("Current time millis : %s \n", cal.getTimeInMillis() );
        System.out.printf("Current time millis : %s \n", d.getTime() );
        System.out.printf("Current time millis : %s \n", clock.millis() );
        
        LocalDateTime  ldt = LocalDateTime.now();
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        System.out.printf("Current date time: %s \n", ldt.toString());
        System.out.printf("Current date time's instant: %s \n", ldt.toInstant(offset));
        System.out.printf("Current date time's nano: %s \n", ldt.getNano());
        

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);          //This class is immutable and thread-safe.@since 1.8        
        System.out.printf("Current date time: %s \n", dateTimeFormatter.format(ldt));                                           
        
       
        Date date = Date.from(ldt.toInstant(offset));
        System.out.printf("Current date time: %s \n", new SimpleDateFormat(TIME_FORMAT).format(date));                                         
        
    }
}