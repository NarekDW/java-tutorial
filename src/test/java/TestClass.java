import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 23.06.2017 by K.N.K
 */
public class TestClass {

    @Test
    public void test() {

        Date date1 = new Date(2017, 5, 24, 12, 0, 0);
        Date date2 = new Date(2017, 5, 24, 13, 0, 0);
        System.out.println(date1 + "\n" + date2);

//        * @param year  the year to represent, from MIN_YEAR to MAX_YEAR
//                * @param month  the month-of-year to represent, from 1 (January) to 12 (December)
//                * @param dayOfMonth  the day-of-month to represent, from 1 to 31
//                * @param hour  the hour-of-day to represent, from 0 to 23
//                * @param minute  the minute-of-hour to represent, from 0 to 59
//                * @param second  the second-of-minute to represent, from 0 to 59
//                * @return the local date-time, not null
//                * @throws DateTimeException if the value of any field is out of range,
//     *  or if the day-of-month is invalid for the month-year

//        LocalDateTime of1 = LocalDateTime.of(2017, 6, 24, 12, 0, 0);
//        LocalDateTime of2 = LocalDateTime.of(2017, 6, 24, 13, 0, 0);
//        System.out.println(of1 + "\t" + of2);
//
//        Date date = new Date(of1.toString());
//        System.out.println(date);



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:MM:ss");
        String format1 = format.format(new Date());
        System.out.println("format1 = "+format1);

    }

}
