package common.util;

import common.exceptions.InvalidTimeUnitException;
import common.model.Period;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class DateUtils {

    /**
     * Creates an instance of DateUtils with no argument.
     */
    private DateUtils() {}

    /**
     * Returns a prior date Instant object based on the received date, the amount value and the date unit to subtract
     * received by argument.
     * Example: if the Instant received corresponds to '18-04-1996 09:00:00:000', the amount to subtract is 2 and the
     * ChronoUnit is WEEKS, the Instant returned is '04-04-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount value to subtract.
     * @param chronoUnit the date's unit.
     *
     * @return the prior date Instant object.
     */
    public static Instant getPriorInstant(Instant dateToCompare, int amountToSubtract, ChronoUnit chronoUnit) {

        DateTime dateTime = new DateTime(dateToCompare.toEpochMilli());

        switch (chronoUnit) {

            case NANOS:
            case MICROS:
            case MILLIS:
            case SECONDS:
            case MINUTES:
            case HOURS:
            case HALF_DAYS:
            case DAYS:
                return dateToCompare.minus(amountToSubtract, chronoUnit);
            case WEEKS:
                return dateTime.minusWeeks(amountToSubtract).toDate().toInstant();
            case MONTHS:
                return dateTime.minusMonths(amountToSubtract).toDate().toInstant();
            case YEARS:
                return dateTime.minusYears(amountToSubtract).toDate().toInstant();
            case DECADES:
                return dateTime.minusYears(amountToSubtract * 10).toDate().toInstant();
            case CENTURIES:
                return dateTime.minusYears(amountToSubtract * 100).toDate().toInstant();
            case MILLENNIA:
                return dateTime.minusYears(amountToSubtract * 1000).toDate().toInstant();
            default:
                throw new InvalidTimeUnitException();
        }
    }

    /**
     * Returns a prior date Instant object based on the received Date object, the amount value and the date
     * unit to subtract received by argument.
     * Example: if the received Date object value corresponds to '18-04-1996 09:00:00:000', the amount to subtract
     * is 2 and the ChronoUnit is WEEKS, the Instant returned is '04-04-1996 09:00:00:000'.
     *
     * @param dateToCompare the date object that represents the date to compare.
     * @param amountToSubtract the amount value to subtract.
     * @param chronoUnit the date's unit.
     *
     * @return the prior date Instant object.
     */
    public static Instant getPriorInstant(Date dateToCompare, int amountToSubtract, ChronoUnit chronoUnit) {
        return getPriorInstant(dateToCompare.toInstant(), amountToSubtract, chronoUnit);
    }

    /**
     * Returns a prior date Instant object based on the received epoch millis value, the amount value and the date
     * unit to subtract received by argument.
     * Example: if the received epoch millis value corresponds to '18-04-1996 09:00:00:000', the amount to
     * subtract is 2 and the ChronoUnit is WEEKS, the Instant returned is '04-04-1996 09:00:00:000'.
     *
     * @param dateToCompareEpochMilli the epoch millis value that represents the date to compare.
     * @param amountToSubtract the amount value to subtract.
     * @param chronoUnit the date's unit.
     *
     * @return the prior date Instant object.
     */
    public static Instant getPriorInstant(long dateToCompareEpochMilli, int amountToSubtract, ChronoUnit chronoUnit) {
        return getPriorInstant(Instant.ofEpochMilli(dateToCompareEpochMilli), amountToSubtract, chronoUnit);
    }

    /**
     * Returns a seconds prior date Instant object based on the received date and the amount value received by argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1996 08:59:58:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of seconds to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getSecondsPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract, ChronoUnit.SECONDS);
    }

    /**
     * Returns a seconds prior date Instant object based on the current date and the amount value received by argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1996 08:59:58:000'.
     *
     * @param amountToSubtract the amount of seconds to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getSecondsPriorInstant(int amountToSubtract) {
        return getSecondsPriorInstant(Instant.now(), amountToSubtract);
    }
    
    /**
     * Returns a minutes prior date Instant object based on the received date and the amount value received by argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1996 08:58:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of minutes to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getMinutesPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract, ChronoUnit.MINUTES);
    }

    /**
     * Returns a minutes prior date Instant object based on the current date and the amount value received by argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1996 08:58:00:000'.
     *
     * @param amountToSubtract the amount of minutes to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getMinutesPriorInstant(int amountToSubtract) {
        return getMinutesPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns an hours prior date Instant object based on the received date and the amount value received by argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1996 07:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of hours to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getHoursPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract, ChronoUnit.HOURS);
    }

    /**
     * Returns an hours prior date Instant object based on the current date and the amount value received by argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1996 07:00:00:000'.
     *
     * @param amountToSubtract the amount of hours to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getHoursPriorInstant(int amountToSubtract) {
        return getHoursPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns a days prior date Instant object based on the received date and the amount value received by argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '16-04-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of days to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getDaysPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract, ChronoUnit.DAYS);
    }

    /**
     * Returns a days prior date Instant object based on the current date and the amount value received by argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '16-04-1996 09:00:00:000'.
     *
     * @param amountToSubtract the amount of days to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getDaysPriorInstant(int amountToSubtract) {
        return getDaysPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns a weeks prior date Instant object based on the received date and the amount value received by argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '04-04-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of weeks to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getWeeksPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract, ChronoUnit.WEEKS);
    }

    /**
     * Returns a weeks prior date Instant object based on the current date and the amount value received by argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '04-04-1996 09:00:00:000'.
     *
     * @param amountToSubtract the amount of weeks to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getWeeksPriorInstant(int amountToSubtract) {
        return getWeeksPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns a months prior date Instant object based on the received date and the amount value received by argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-02-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of months to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getMonthsPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract, ChronoUnit.MONTHS);
    }

    /**
     * Returns a months prior date Instant object based on the current date and the amount value received by argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-02-1996 09:00:00:000'.
     *
     * @param amountToSubtract the amount of months to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getMonthsPriorInstant(int amountToSubtract) {
        return getMonthsPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns a trimesters prior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-10-1995 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of trimesters to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getTrimestersPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract * 3, ChronoUnit.MONTHS);
    }

    /**
     * Returns a trimesters prior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-10-1995 09:00:00:000'.
     *
     * @param amountToSubtract the amount of trimesters to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getTrimestersPriorInstant(int amountToSubtract) {
        return getTrimestersPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns a semesters prior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1995 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of semesters to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getSemestersPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract * 6, ChronoUnit.MONTHS);
    }

    /**
     * Returns a semesters prior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1995 09:00:00:000'.
     *
     * @param amountToSubtract the amount of semesters to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getSemestersPriorInstant(int amountToSubtract) {
        return getSemestersPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns a years prior date Instant object based on the received date and the amount value received by argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1994 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToSubtract the amount of years to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getYearsPriorInstant(Instant dateToCompare, int amountToSubtract) {
        return getPriorInstant(dateToCompare, amountToSubtract, ChronoUnit.YEARS);
    }

    /**
     * Returns a years prior date Instant object based on the current date and the amount value received by argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to subtract is 2, the
     * Instant returned is '18-04-1994 09:00:00:000'.
     *
     * @param amountToSubtract the amount of years to subtract.
     *
     * @return the prior date Instant object.
     */
    public static Instant getYearsPriorInstant(int amountToSubtract) {
        return getYearsPriorInstant(Instant.now(), amountToSubtract);
    }

    /**
     * Returns a posterior date Instant object based on the received date, the amount value and the date unit to add
     * received by argument.
     * Example: if the Instant received corresponds to '18-04-1996 09:00:00:000', the amount to add is 2 and the
     * ChronoUnit is WEEKS, the Instant returned is '02-05-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount value to add.
     * @param chronoUnit the date's unit.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getPosteriorInstant(Instant dateToCompare, int amountToAdd, ChronoUnit chronoUnit) {

        DateTime dateTime = new DateTime(dateToCompare.toEpochMilli());

        switch (chronoUnit) {

            case NANOS:
            case MICROS:
            case MILLIS:
            case SECONDS:
            case MINUTES:
            case HOURS:
            case HALF_DAYS:
            case DAYS:
                return dateToCompare.plus(amountToAdd, chronoUnit);
            case WEEKS:
                return dateTime.plusWeeks(amountToAdd).toDate().toInstant();
            case MONTHS:
                return dateTime.plusMonths(amountToAdd).toDate().toInstant();
            case YEARS:
                return dateTime.plusYears(amountToAdd).toDate().toInstant();
            case DECADES:
                return dateTime.plusYears(amountToAdd * 10).toDate().toInstant();
            case CENTURIES:
                return dateTime.plusYears(amountToAdd * 100).toDate().toInstant();
            case MILLENNIA:
                return dateTime.plusYears(amountToAdd * 1000).toDate().toInstant();
            default:
                throw new InvalidTimeUnitException();
        }
    }

    /**
     * Returns a posterior date Instant object based on the received date object, the amount value and the date
     * unit to add received by argument.
     * Example: if the Date received corresponds to '18-04-1996 09:00:00:000', the amount to add is 2 and the
     * ChronoUnit is WEEKS, the Instant returned is '02-05-1996 09:00:00:000'.
     *
     * @param dateToCompare the Date object that represents the date to compare.
     * @param amountToAdd the amount value to add.
     * @param chronoUnit the date's unit.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getPosteriorInstant(Date dateToCompare, int amountToAdd, ChronoUnit chronoUnit) {
        return getPosteriorInstant(dateToCompare.toInstant(), amountToAdd, chronoUnit);
    }

    /**
     * Returns a posterior date Instant object based on the received epoch millis, the amount value and the date
     * unit to add received by argument.
     * Example: if the Date received corresponds to '18-04-1996 09:00:00:000', the amount to add is 2 and the
     * ChronoUnit is WEEKS, the Instant returned is '02-05-1996 09:00:00:000'.
     *
     * @param dateToCompareEpochMillis the epoch millis value that represents the date to compare.
     * @param amountToAdd the amount value to add.
     * @param chronoUnit the date's unit.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getPosteriorInstant(long dateToCompareEpochMillis, int amountToAdd, ChronoUnit chronoUnit) {
        return getPosteriorInstant(Instant.ofEpochMilli(dateToCompareEpochMillis), amountToAdd, chronoUnit);
    }

    /**
     * Returns a seconds posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 09:00:02:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of seconds to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getSecondsPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd, ChronoUnit.SECONDS);
    }

    /**
     * Returns a seconds posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 09:00:02:000'.
     *
     * @param amountToAdd the amount of seconds to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getSecondsPosteriorInstant(int amountToAdd) {
        return getSecondsPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a minutes posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 09:02:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of minutes to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getMinutesPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd, ChronoUnit.MINUTES);
    }

    /**
     * Returns a minutes posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 09:02:00:000'.
     *
     * @param amountToAdd the amount of minutes to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getMinutesPosteriorInstant(int amountToAdd) {
        return getMinutesPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns an hours posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 11:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of hours to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getHoursPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd, ChronoUnit.HOURS);
    }

    /**
     * Returns an hours posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 11:00:00:000'.
     *
     * @param amountToAdd the amount of hours to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getHoursPosteriorInstant(int amountToAdd) {
        return getHoursPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a days posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '20-04-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of days to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getDaysPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd, ChronoUnit.DAYS);
    }

    /**
     * Returns a days posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '20-04-1996 09:00:00:000'.
     *
     * @param amountToAdd the amount of days to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getDaysPosteriorInstant(int amountToAdd) {
        return getDaysPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a weeks posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of weeks to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getWeeksPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd, ChronoUnit.WEEKS);
    }

    /**
     * Returns a weeks posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1996 09:00:00:000'.
     *
     * @param amountToAdd the amount of weeks to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getWeeksPosteriorInstant(int amountToAdd) {
        return getWeeksPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a months posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-06-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of months to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getMonthsPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd, ChronoUnit.MONTHS);
    }

    /**
     * Returns a months posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-06-1996 09:00:00:000'.
     *
     * @param amountToAdd the amount of months to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getMonthsPosteriorInstant(int amountToAdd) {
        return getMonthsPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a trimester posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-10-1996 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of trimesters to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getTrimestersPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd * 3, ChronoUnit.MONTHS);
    }

    /**
     * Returns a trimester posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-10-1996 09:00:00:000'.
     *
     * @param amountToAdd the amount of trimesters to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getTrimestersPosteriorInstant(int amountToAdd) {
        return getTrimestersPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a semesters posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1997 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of semesters to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getSemestersPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd * 6, ChronoUnit.MONTHS);
    }

    /**
     * Returns a semesters posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1997 09:00:00:000'.
     *
     * @param amountToAdd the amount of semesters to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getSemestersPosteriorInstant(int amountToAdd) {
        return getSemestersPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a years posterior date Instant object based on the received date and the amount value received by
     * argument.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1998 09:00:00:000'.
     *
     * @param dateToCompare the instant object that represents the date to compare.
     * @param amountToAdd the amount of seconds to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getYearsPosteriorInstant(Instant dateToCompare, int amountToAdd) {
        return getPosteriorInstant(dateToCompare, amountToAdd, ChronoUnit.YEARS);
    }

    /**
     * Returns a years posterior date Instant object based on the current date and the amount value received by
     * argument.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:000' and the amount to add is 2, the
     * Instant returned is '18-04-1998 09:00:00:000'.
     *
     * @param amountToAdd the amount of seconds to add.
     *
     * @return the posterior date Instant object.
     */
    public static Instant getYearsPosteriorInstant(int amountToAdd) {
        return getYearsPosteriorInstant(Instant.now(), amountToAdd);
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past second initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1996 08:59:59' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past second initial value.
     */
    public static Period getPastSecondPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 1, ChronoUnit.SECONDS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past second initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1996 08:59:59' and '18-04-1996 09:00:00'.
     *
     * @return the period between the current date and its past second initial value.
     */
    public static Period getPastSecondPeriod() {
        return getPastSecondPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past minute initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1996 08:59:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past minute initial value.
     */
    public static Period getPastMinutePeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 1, ChronoUnit.MINUTES), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past minute initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1996 08:59:00' and '18-04-1996 09:00:00'.
     **
     * @return the period between the current date and its past minute initial value.
     */
    public static Period getPastMinutePeriod() {
        return getPastMinutePeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past hour initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1996 08:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past hour initial value.
     */
    public static Period getPastHourPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 1, ChronoUnit.HOURS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past hour initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1996 08:00:00' and '18-04-1996 09:00:00'.
     **
     * @return the period between the current date and its past hour initial value.
     */
    public static Period getPastHourPeriod() {
        return getPastHourPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past day initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '17-04-1996 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past day initial value.
     */
    public static Period getPastDayPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 1, ChronoUnit.DAYS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past day initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '17-04-1996 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @return the period between the current date and its past day initial value.
     */
    public static Period getPastDayPeriod() {
        return getPastDayPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past week initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '11-04-1996 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past week initial value.
     */
    public static Period getPastWeekPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 1, ChronoUnit.WEEKS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past week initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '11-04-1996 09:00:00' and '18-04-1996 09:00:00'.
     **
     * @return the period between the current date and its past week initial value.
     */
    public static Period getPastWeekPeriod() {
        return getPastWeekPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past month initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-03-1996 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past month initial value.
     */
    public static Period getPastMonthPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 1, ChronoUnit.MONTHS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past month initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-03-1996 09:00:00' and '18-04-1996 09:00:00'.
     **
     * @return the period between the current date and its past month initial value.
     */
    public static Period getPastMonthPeriod() {
        return getPastMonthPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past trimester initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-01-1996 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past trimester initial value.
     */
    public static Period getPastTrimesterPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 3, ChronoUnit.MONTHS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past trimester initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-01-1996 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @return the period between the current date and its past trimester initial value.
     */
    public static Period getPastTrimesterPeriod() {
        return getPastTrimesterPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past semester initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-10-1995 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past semester initial value.
     */
    public static Period getPastSemesterPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 6, ChronoUnit.MONTHS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past semester initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-10-1995 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @return the period between the current date and its past semester initial value.
     */
    public static Period getPastSemesterPeriod() {
        return getPastSemesterPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the received date and its past year initial
     * value.
     * Example: if the received date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1995 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the received date and its past year initial value.
     */
    public static Period getPastYearPeriod(Instant dateToCompare) {
        return new Period(getPriorInstant(dateToCompare, 1, ChronoUnit.YEARS), dateToCompare);
    }

    /**
     * Returns a Period instance with the date period in between the current date and its past year initial
     * value.
     * Example: if the current date corresponds to '18-04-1996 09:00:00' the returned period object is between
     * '18-04-1995 09:00:00' and '18-04-1996 09:00:00'.
     *
     * @return the period between the current date and its past year initial value.
     */
    public static Period getPastYearPeriod() {
        return getPastYearPeriod(Instant.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last second
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 08:59:59' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last second value compared to the received date.
     */
    public static Period getLastSecondPeriod(DateTime dateToCompare) {

        DateTime startOfLastSecond = new DateTime(dateToCompare.minusSeconds(1)
                .withMillisOfSecond(0));

        DateTime endOfLastSecond = startOfLastSecond.plusSeconds(1);

        return new Period(startOfLastSecond.toDate().toInstant(), endOfLastSecond.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last second
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 08:59:59' and '18-04-1996 09:00:00'.
     *
     * @return the period between the start and the end of the last second value compared to the current date.
     */
    public static Period getLastSecondPeriod() {
        return getLastSecondPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last minute
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 08:59:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last minute value compared to the received date.
     */
    public static Period getLastMinutePeriod(DateTime dateToCompare) {

        DateTime startOfLastMinute = new DateTime(dateToCompare.minusMinutes(1)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0));

        DateTime endOfLastMinute = startOfLastMinute.plusMinutes(1);

        return new Period(startOfLastMinute.toDate().toInstant(), endOfLastMinute.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last minute
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 08:59:00' and '18-04-1996 09:00:00'.
     *
     * @return the period between the start and the end of the last minute value compared to the current date.
     */
    public static Period getLastMinutePeriod() {
        return getLastMinutePeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last hour
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:30:00:000' the returned period object is between
     * '18-04-1996 08:00:00' and '18-04-1996 09:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last hour value compared to the received date.
     */
    public static Period getLastHourPeriod(DateTime dateToCompare) {

        DateTime startOfLastHour = new DateTime(dateToCompare.minusHours(1)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0));

        DateTime endOfLastHour = startOfLastHour.plusHours(1);

        return new Period(startOfLastHour.toDate().toInstant(), endOfLastHour.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last hour
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:30:00:000' the returned period object is between
     * '18-04-1996 08:00:00' and '18-04-1996 09:00:00'.
     *
     * @return the period between the start and the end of the last hour value compared to the current date.
     */
    public static Period getLastHourPeriod() {
        return getLastHourPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last day
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '17-04-1996 00:00:00' and '18-04-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last hour value compared to the received date.
     */
    public static Period getLastDayPeriod(DateTime dateToCompare) {

        DateTime startOfLastDay = new DateTime(dateToCompare.minusDays(1)
                .withTimeAtStartOfDay());

        DateTime endOfLastDay = startOfLastDay.plusDays(1);

        return new Period(startOfLastDay.toDate().toInstant(), endOfLastDay.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last day
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '17-04-1996 00:00:00' and '18-04-1996 00:00:00'.
     *
     * @return the period between the start and the end of the last hour value compared to the current date.
     */
    public static Period getLastDayPeriod() {
        return getLastDayPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last week
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' (Thursday) the returned period object
     * is between '08-04-1996 00:00:00' and '15-04-1996 00:00:00'.
     * It is considered the start and end of a week the 00:00:00 of Monday.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last week value compared to the received date.
     */
    public static Period getLastWeekPeriod(DateTime dateToCompare) {

        DateTime startOfLastWeek = new DateTime(dateToCompare.minusWeeks(1)
                .withDayOfWeek(DateTimeConstants.MONDAY)
                .withTimeAtStartOfDay());

        DateTime endOfLastWeek = startOfLastWeek.plusDays(7);

        return new Period(startOfLastWeek.toDate().toInstant(), endOfLastWeek.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last week
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' (Thursday) the returned period object
     * is between '08-04-1996 00:00:00' and '15-04-1996 00:00:00'.
     * It is considered the start and end of a week the 00:00:00 of Monday.
     *
     * @return the period between the start and the end of the last week value compared to the current date.
     */
    public static Period getLastWeekPeriod() {
        return getLastWeekPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last month
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-03-1996 00:00:00' and '01-04-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last month value compared to the received date.
     */
    public static Period getLastMonthPeriod(DateTime dateToCompare) {

        DateTime startOfLastMonth = new DateTime(dateToCompare.minusMonths(1)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfLastMonth = startOfLastMonth.plusMonths(1);

        return new Period(startOfLastMonth.toDate().toInstant(), endOfLastMonth.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last month
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-03-1996 00:00:00' and '01-04-1996 00:00:00'.
     *
     * @return the period between the start and the end of the last month value compared to the current date.
     */
    public static Period getLastMonthPeriod() {
        return getLastMonthPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last trimester
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-01-1996 00:00:00' and '01-04-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last trimester value compared to the received date.
     */
    public static Period getLastTrimesterPeriod(DateTime dateToCompare) {

        int differential = (dateToCompare.getMonthOfYear() - 1) % 3;

        DateTime startOfLastTrimester = new DateTime(dateToCompare.minusMonths(3 + differential)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfLastTrimester = startOfLastTrimester.plusMonths(3);

        return new Period(startOfLastTrimester.toDate().toInstant(), endOfLastTrimester.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last trimester
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-01-1996 00:00:00' and '01-04-1996 00:00:00'.
     *
     * @return the period between the start and the end of the last trimester value compared to the current date.
     */
    public static Period getLastTrimesterPeriod() {
        return getLastTrimesterPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last semester
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-06-1995 00:00:00' and '01-01-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last semester value compared to the received date.
     */
    public static Period getLastSemesterPeriod(DateTime dateToCompare) {

        int differential = (dateToCompare.getMonthOfYear() - 1) % 6;

        DateTime startOfLastMonth = new DateTime(dateToCompare.minusMonths(6 + differential)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfLastMonth = startOfLastMonth.plusMonths(6);

        return new Period(startOfLastMonth.toDate().toInstant(), endOfLastMonth.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last semester
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-06-1995 00:00:00' and '01-01-1996 00:00:00'.
     *
     * @return the period between the start and the end of the last semester value compared to the current date.
     */
    public static Period getLastSemesterPeriod() {
        return getLastSemesterPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last year
     * value compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-01-1995 00:00:00' and '01-01-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the last year value compared to the received date.
     */
    public static Period getLastYearPeriod(DateTime dateToCompare) {

        DateTime startOfLastYear = new DateTime(dateToCompare.minusYears(1)
                .withMonthOfYear(1)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfLastYear = startOfLastYear.plusYears(1);

        return new Period(startOfLastYear.toDate().toInstant(), endOfLastYear.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the last year
     * value compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-01-1995 00:00:00' and '01-01-1996 00:00:00'.
     *
     * @return the period between the start and the end of the last year value compared to the current date.
     */
    public static Period getLastYearPeriod() {
        return getLastYearPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next second value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 09:00:01' and '18-04-1996 09:00:02'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next second value compared to the received date.
     */
    public static Period getNextSecondPeriod(DateTime dateToCompare) {

        DateTime startOfNextSecond = new DateTime(dateToCompare.plusSeconds(1)
                .withMillisOfSecond(0));

        DateTime endOfNextSecond = startOfNextSecond.plusSeconds(1);

        return new Period(startOfNextSecond.toDate().toInstant(), endOfNextSecond.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next second value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 09:00:01' and '18-04-1996 09:00:02'.
     *
     * @return the period between the start and the end of the next second value compared to the current date.
     */
    public static Period getNextSecondPeriod() {
        return getNextSecondPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next minute value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 09:01:00' and '18-04-1996 09:02:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next minute value compared to the received date.
     */
    public static Period getNextMinutePeriod(DateTime dateToCompare) {

        DateTime startOfNextMinute = new DateTime(dateToCompare.plusMinutes(1)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0));

        DateTime endOfNextMinute = startOfNextMinute.plusMinutes(1);

        return new Period(startOfNextMinute.toDate().toInstant(), endOfNextMinute.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next minute value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 09:01:00' and '18-04-1996 09:02:00'.
     *
     * @return the period between the start and the end of the next minute value compared to the current date.
     */
    public static Period getNextMinutePeriod() {
        return getNextMinutePeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next hour value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 10:00:00' and '18-04-1996 11:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next hour value compared to the received date.
     */
    public static Period getNextHourPeriod(DateTime dateToCompare) {

        DateTime startOfNextHour = new DateTime(dateToCompare.plusHours(1)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0));

        DateTime endOfNextHour = startOfNextHour.plusHours(1);

        return new Period(startOfNextHour.toDate().toInstant(), endOfNextHour.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next hour value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '18-04-1996 10:00:00' and '18-04-1996 11:00:00'.
     *
     * @return the period between the start and the end of the next hour value compared to the current date.
     */
    public static Period getNextHourPeriod() {
        return getNextHourPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next day value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '19-04-1996 00:00:00' and '20-04-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next day value compared to the received date.
     */
    public static Period getNextDayPeriod(DateTime dateToCompare) {

        DateTime startOfNextDay = new DateTime(dateToCompare.plusDays(1)
                .withTimeAtStartOfDay());

        DateTime endOfNextDay = startOfNextDay.plusDays(1);

        return new Period(startOfNextDay.toDate().toInstant(), endOfNextDay.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next day value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '19-04-1996 00:00:00' and '20-04-1996 00:00:00'.
     *
     * @return the period between the start and the end of the next day value compared to the current date.
     */
    public static Period getNextDayPeriod() {
        return getNextDayPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next week value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' (Thursday) the returned period object
     * is between '22-04-1996 00:00:00' and '29-04-1996 00:00:00'.
     * It is considered the start and end of a week the 00:00:00 of Monday.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next week value compared to the received date.
     */
    public static Period getNextWeekPeriod(DateTime dateToCompare) {

        DateTime startOfNextWeek = new DateTime(dateToCompare.plusWeeks(1)
                .withDayOfWeek(DateTimeConstants.MONDAY)
                .withTimeAtStartOfDay());

        DateTime endOfNextWeek = startOfNextWeek.plusDays(7);

        return new Period(startOfNextWeek.toDate().toInstant(), endOfNextWeek.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next week value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' (Thursday) the returned period object
     * is between '22-04-1996 00:00:00' and '29-04-1996 00:00:00'.
     * It is considered the start and end of a week the 00:00:00 of Monday.
     *
     * @return the period between the start and the end of the next week value compared to the current date.
     */
    public static Period getNextWeekPeriod() {
        return getNextWeekPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next month value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-05-1996 00:00:00' and '01-06-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next month value compared to the received date.
     */
    public static Period getNextMonthPeriod(DateTime dateToCompare) {

        DateTime startOfNextMonth = new DateTime(dateToCompare.plusMonths(1)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfNextMonth = startOfNextMonth.plusMonths(1);

        return new Period(startOfNextMonth.toDate().toInstant(), endOfNextMonth.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next month value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-05-1996 00:00:00' and '01-06-1996 00:00:00'.
     *
     * @return the period between the start and the end of the next month value compared to the current date.
     */
    public static Period getNextMonthPeriod() {
        return getNextMonthPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next trimester value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-07-1996 00:00:00' and '01-10-1996 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next trimester value compared to the received date.
     */
    public static Period getNextTrimesterPeriod(DateTime dateToCompare) {

        int differential = dateToCompare.getMonthOfYear() % 3;

        DateTime startOfNextTrimester = new DateTime(dateToCompare.plusMonths(differential + 1)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfNextTrimester = startOfNextTrimester.plusMonths(3);

        return new Period(startOfNextTrimester.toDate().toInstant(), endOfNextTrimester.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next trimester value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-07-1996 00:00:00' and '01-10-1996 00:00:00'.
     *
     * @return the period between the start and the end of the next trimester value compared to the current date.
     */
    public static Period getNextTrimesterPeriod() {
        return getNextTrimesterPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next semester value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-07-1996 00:00:00' and '01-01-1997 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next semester value compared to the received date.
     */
    public static Period getNextSemesterPeriod(DateTime dateToCompare) {

        int differential = dateToCompare.getMonthOfYear() % 6;

        DateTime startOfNextSemester = new DateTime(dateToCompare.plusMonths(differential + 1)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfNextSemester = startOfNextSemester.plusMonths(6);

        return new Period(startOfNextSemester.toDate().toInstant(), endOfNextSemester.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next semester value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-07-1996 00:00:00' and '01-01-1997 00:00:00'.
     *
     * @return the period between the start and the end of the next semester value compared to the current date.
     */
    public static Period getNextSemesterPeriod() {
        return getNextSemesterPeriod(DateTime.now());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next year value
     * compared to the received date.
     * Example: if the received date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-01-1997 00:00:00' and '01-01-1998 00:00:00'.
     *
     * @param dateToCompare the date to compare.
     *
     * @return the period between the start and the end of the next year value compared to the received date.
     */
    public static Period getNextYearPeriod(DateTime dateToCompare) {

        DateTime startOfNextYear = new DateTime(dateToCompare.plusYears(1)
                .withMonthOfYear(1)
                .withDayOfMonth(1)
                .withTimeAtStartOfDay());

        DateTime endOfNextYear = startOfNextYear.plusYears(1);

        return new Period(startOfNextYear.toDate().toInstant(), endOfNextYear.toDate().toInstant());
    }

    /**
     * Returns a Period instance with the date period in between the start and the end of the next year value
     * compared to the current date.
     * Example: if the current date corresponds to '18-04-1996 09:00:00:500' the returned period object is between
     * '01-01-1997 00:00:00' and '01-01-1998 00:00:00'.
     *
     * @return the period between the start and the end of the next year value compared to the current date.
     */
    public static Period getNextYearPeriod() {
        return getNextYearPeriod(DateTime.now());
    }

    public static int getDecade(int year) {
        int yearOfCentury = year % 100;
        return (yearOfCentury - (yearOfCentury % 10));
    }

    public static int getDecade(DateTime dateToCompare) {
        return getDecade(dateToCompare.getYear());
    }
}
