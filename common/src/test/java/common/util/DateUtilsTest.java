package common.util;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateUtilsTest {

    @Test
    public void getPriorInstantTest() {

        // 18th April 1996 22:00:00:000:000:000
        long initialDateMillis = 829861200000L;

        // TEST NANOS
        // 18th April 1996 21:59:59:999:000:000
        long expectedDateMillis = 829861199999L;

        Instant result = DateUtils.getPriorInstant(
                Instant.ofEpochMilli(initialDateMillis),
                1000000,
                ChronoUnit.NANOS);

        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());

        // TEST MICROS
        // 18th April 1996 21:59:59:998:000:000
        expectedDateMillis = 829861199998L;

        result = DateUtils.getPriorInstant(
                Instant.ofEpochMilli(initialDateMillis),
                2000,
                ChronoUnit.MICROS);

        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());

        // TEST MILLIS
        // 18th April 1996 21:59:59:995
        expectedDateMillis = 829861199995L;

        result = DateUtils.getPriorInstant(
                Instant.ofEpochMilli(initialDateMillis),
                5,
                ChronoUnit.MILLIS);

        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());

        // TEST HALF DAYS
        // 17th April 1996 22:00:00
        expectedDateMillis = 829774800000L;

        result = DateUtils.getPriorInstant(
                Instant.ofEpochMilli(initialDateMillis),
                2,
                ChronoUnit.HALF_DAYS);

        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());

        // TEST DECADES
        // 18th April 1986 22:00:00
        expectedDateMillis = 514242000000L;

        result = DateUtils.getPriorInstant(
                Instant.ofEpochMilli(initialDateMillis),
                1,
                ChronoUnit.DECADES);

        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());

        // TEST CENTURIES
        // 18th April 1896 22:00:00
        expectedDateMillis = -2325808800000L;

        result = DateUtils.getPriorInstant(
                Instant.ofEpochMilli(initialDateMillis),
                1,
                ChronoUnit.CENTURIES);

        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());

        // TEST MILLENNIA
        // 18th April 996 22:00:00
        expectedDateMillis = -30727043925000L;

        result = DateUtils.getPriorInstant(
                Instant.ofEpochMilli(initialDateMillis),
                1,
                ChronoUnit.MILLENNIA);

        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getSecondsPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829861200000L;
        // 18th April 1996 21:59:53
        long expectedDateMillis = 829861193000L;

        Instant result = DateUtils.getSecondsPriorInstant(Instant.ofEpochMilli(initialDateMillis), 7);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getMinutesPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829861200000L;
        // 18th April 1996 21:57:00
        long expectedDateMillis = 829861020000L;

        Instant result = DateUtils.getMinutesPriorInstant(Instant.ofEpochMilli(initialDateMillis), 3);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getHoursPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829857600000L;
        // 18th April 1996 17:00:00
        long expectedDateMillis = 829839600000L;

        Instant result = DateUtils.getHoursPriorInstant(Instant.ofEpochMilli(initialDateMillis), 5);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getDaysPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829857600000L;
        // 17th April 1996 22:00:00
        long expectedDateMillis = 829771200000L;

        Instant result = DateUtils.getDaysPriorInstant(Instant.ofEpochMilli(initialDateMillis), 1);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getWeeksPriorInstantTest() {

        // 18th April 1996 21:00:00
        long initialDateMillis = 829857600000L;
        // 4th April 1996 21:00:00
        long expectedDateMillis = 828648000000L;

        Instant result = DateUtils.getWeeksPriorInstant(Instant.ofEpochMilli(initialDateMillis), 2);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getMonthsPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829857600000L;
        // 18th January 1996 22:00:00
        long expectedDateMillis = 821998800000L;

        Instant result = DateUtils.getMonthsPriorInstant(Instant.ofEpochMilli(initialDateMillis), 3);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getTrimestersPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829857600000L;
        // 18th April 1995 22:00:00
        long expectedDateMillis = 798235200000L;

        Instant result = DateUtils.getTrimestersPriorInstant(Instant.ofEpochMilli(initialDateMillis), 4);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getSemestersPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829857600000L;
        // 18th April 1995 22:00:00
        long expectedDateMillis = 798235200000L;

        Instant result = DateUtils.getSemestersPriorInstant(Instant.ofEpochMilli(initialDateMillis), 2);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getYearsPriorInstantTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829857600000L;
        // 18th April 1993 22:00:00
        long expectedDateMillis = 735163200000L;

        Instant result = DateUtils.getYearsPriorInstant(Instant.ofEpochMilli(initialDateMillis), 3);
        Assert.assertEquals(expectedDateMillis, result.toEpochMilli());
    }

    @Test
    public void getDecadeTest() {

        // 18th April 1996 22:00:00
        long initialDateMillis = 829857600000L;

        int expectedDecadeNumber = 90;

        int result = DateUtils.getDecade(new DateTime(initialDateMillis));
        Assert.assertEquals(expectedDecadeNumber, result);

        result = DateUtils.getDecade(1991);
        Assert.assertEquals(expectedDecadeNumber, result);
    }
}
