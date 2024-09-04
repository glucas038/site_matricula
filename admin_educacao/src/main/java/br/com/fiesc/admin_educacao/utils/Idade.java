package br.com.fiesc.admin_educacao.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Idade {

	public static long getDifferenceInYears(Date startDate, Date endDate) {
        LocalDate date1 = LocalDate.parse("1990-01-01");
        
        Date date2 = new Date("Mon Aug 12 00:00:00 BRT 2024");
        
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        long yearsBetween = ChronoUnit.YEARS.between(date1, localDate2);
      
        return yearsBetween;
    }

}
