import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateTest {

    public static void main(String[] args) {
            
        Clock clock = Clock.systemDefaultZone();
        long mills = clock.millis();

        Instant instant = clock.instant();
        System.out.println(instant.toString());
        Date legacyDate = Date.from(instant); // legacy java.util.Date
        
        // System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zonel = ZoneId.of ("Europe/Berlin"); 
        ZoneId zone2 = ZoneId.of ("Brazil/East"); 
        System.out.println("zone rules show the offSet " + zonel.getRules()); 

        LocalTime now1 = LocalTime.now(zonel); 
        LocalTime now2 = LocalTime.now(zone2); 
        System.out.println(now1.isBefore(now2)); // false
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2); 
        long minutesbetween = ChronoUnit.MINUTES.between(now1, now2); 
        System.out.println(hoursBetween); // -2 or -3
        System.out.println(minutesbetween);  
        LocalTime late = LocalTime.of (23, 59, 59); 
        System.out.println(late); // 23:59:59 
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale (Locale. GERMAN); 
        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter); 
        System.out.println(leetTime); // 13:37 
        LocalDate today = LocalDate.now(); 
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println("yesterday = (tomorrow - 2) " + yesterday); 
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayofweek = independenceDay.getDayOfWeek(); 
        System.out.println("day of week for Independence Day 2014 " + dayofweek); // FRÍDÁY 
        DateTimeFormatter germanFormatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale. GERMAN); 
        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter2); 
        LocalDateTime sylvester = LocalDateTime. of (2014, Month. DECEMBER, 31, 23, 59, 59);
        
        Month month = sylvester.getMonth(); 
        System.out.println("24.12.2014 month = " + month); // DECEMBER 
        long minuteofday = sylvester.getLong(ChronoField.MINUTE_OF_DAY); 
        System.out.println("24.12.2014 23:59:59 minute of day = " + minuteofday); // 1439 
        System.out.println("xmas day in german format " + xmas); // 2014-12-24 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");
        
        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed); 
        System.out.println("formatted date and time " + string); // Nov 03, 2014 - 07:13
    }
}