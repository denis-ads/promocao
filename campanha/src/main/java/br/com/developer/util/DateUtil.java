package br.com.developer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 *
 */
public class DateUtil {

    private DateUtil() {}
    
    public static Date toDate(String date) {
        return toDate(date, "00:00.00.000");
    }

    public static Date toDate(String date, String time) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date adicionarDias(Date fimVigencia, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fimVigencia);
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();
    }
}
