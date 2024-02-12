package common.util;

import common.exceptions.SameStorageUnitException;
import common.exceptions.UnexpectedStorageConversionException;
import common.model.Storage;

public final class StorageUtils {

    /**
     * Creates an instance of StorageUtils with no arguments.
     */
    private StorageUtils() {}

    /**
     * Converts the value received on the old storage unit to the new one.
     *
     * @param oldStorageUnit the old storage unit.
     * @param storageValue the old storage value.
     * @param newStorageUnit the new storage unit.
     *
     * @return the value on the new storage unit.
     */
    public static double convertStorageUnit(
            Storage oldStorageUnit,
            double storageValue,
            Storage newStorageUnit) {

        if ((oldStorageUnit == newStorageUnit) ||
            (oldStorageUnit.toString().equalsIgnoreCase(newStorageUnit.toString()))) {
            throw new SameStorageUnitException();
        }

        int differential = oldStorageUnit.ordinal() - newStorageUnit.ordinal();

        if (differential > 0) {
            return storageValue * (1024 * differential);
        } else if (differential < 0) {
            return storageValue / (1024 * Math.abs(differential));
        } else {
            throw new UnexpectedStorageConversionException();
        }
    }

    /**
     * Converts the received value from bytes to kilobytes.
     *
     * @param bytesValue the value in bytes.
     *
     * @return the value in kilobytes.
     */
    public static double convertBytesToKilobytes(double bytesValue) {
        return convertStorageUnit(Storage.BYTE, bytesValue, Storage.KILOBYTE);
    }

    /**
     * Converts the received value from bytes to megabytes.
     *
     * @param bytesValue the value in bytes.
     *
     * @return the value in megabytes.
     */
    public static double convertBytesToMegabytes(double bytesValue) {
        return convertStorageUnit(Storage.BYTE, bytesValue, Storage.MEGABYTE);
    }

    /**
     * Converts the received value from bytes to gigabytes.
     *
     * @param bytesValue the value in bytes.
     *
     * @return the value in gigabytes.
     */
    public static double convertBytesToGigabytes(double bytesValue) {
        return convertStorageUnit(Storage.BYTE, bytesValue, Storage.GIGABYTE);
    }

    /**
     * Converts the received value from bytes to terabytes.
     *
     * @param bytesValue the value in bytes.
     *
     * @return the value in terabytes.
     */
    public static double convertBytesToTerabytes(double bytesValue) {
        return convertStorageUnit(Storage.BYTE, bytesValue, Storage.TERABYTE);
    }

    /**
     * Converts the received value from kilobytes to bytes.
     *
     * @param kilobytesValue the value in kilobytes.
     *
     * @return the value in bytes.
     */
    public static double convertKilobytesToBytes(double kilobytesValue) {
        return convertStorageUnit(Storage.KILOBYTE, kilobytesValue, Storage.BYTE);
    }


    /**
     * Converts the received value from kilobytes to megabytes.
     *
     * @param kilobytesValue the value in kilobytes.
     *
     * @return the value in megabytes.
     */
    public static double convertKilobytesToMegabytes(double kilobytesValue) {
        return convertStorageUnit(Storage.KILOBYTE, kilobytesValue, Storage.MEGABYTE);
    }

    /**
     * Converts the received value from kilobytes to gigabytes.
     *
     * @param kilobytesValue the value in kilobytes.
     *
     * @return the value in gigabytes.
     */
    public static double convertKilobytesToGigabytes(double kilobytesValue) {
        return convertStorageUnit(Storage.KILOBYTE, kilobytesValue, Storage.GIGABYTE);
    }

    /**
     * Converts the received value from kilobytes to terabytes.
     *
     * @param kilobytesValue the value in kilobytes.
     *
     * @return the value in terabytes.
     */
    public static double convertKilobytesToTerabytes(double kilobytesValue) {
        return convertStorageUnit(Storage.KILOBYTE, kilobytesValue, Storage.TERABYTE);
    }

    /**
     * Converts the received value from megabytes to bytes.
     *
     * @param megabytesValue the value in megabytes.
     *
     * @return the value in bytes.
     */
    public static double convertMegabytesToBytes(double megabytesValue) {
        return convertStorageUnit(Storage.MEGABYTE, megabytesValue, Storage.BYTE);
    }

    /**
     * Converts the received value from megabytes to kilobytes.
     *
     * @param megabytesValue the value in megabytes.
     *
     * @return the value in kilobytes.
     */
    public static double convertMegabytesToKilobytes(double megabytesValue) {
        return convertStorageUnit(Storage.MEGABYTE, megabytesValue, Storage.KILOBYTE);
    }

    /**
     * Converts the received value from megabytes to gigabytes.
     *
     * @param megabytesValue the value in megabytes.
     *
     * @return the value in gigabytes.
     */
    public static double convertMegabytesToGigabytes(double megabytesValue) {
        return convertStorageUnit(Storage.MEGABYTE, megabytesValue, Storage.GIGABYTE);
    }

    /**
     * Converts the received value from megabytes to terabytes.
     *
     * @param megabytesValue the value in megabytes.
     *
     * @return the value in terabytes.
     */
    public static double convertMegabytesToTerabytes(double megabytesValue) {
        return convertStorageUnit(Storage.MEGABYTE, megabytesValue, Storage.TERABYTE);
    }

    /**
     * Converts the received value from gigabytes to bytes.
     *
     * @param gigabytesValue the value in gigabytes.
     *
     * @return the value in bytes.
     */
    public static double convertGigabytesToBytes(double gigabytesValue) {
        return convertStorageUnit(Storage.GIGABYTE, gigabytesValue, Storage.BYTE);
    }

    /**
     * Converts the received value from gigabytes to kilobytes.
     *
     * @param gigabytesValue the value in gigabytes.
     *
     * @return the value in kilobytes.
     */
    public static double convertGigabytesToKilobytes(double gigabytesValue) {
        return convertStorageUnit(Storage.GIGABYTE, gigabytesValue, Storage.KILOBYTE);
    }

    /**
     * Converts the received value from gigabytes to megabytes.
     *
     * @param gigabytesValue the value in gigabytes.
     *
     * @return the value in megabytes.
     */
    public static double convertGigabytesToMegabytes(double gigabytesValue) {
        return convertStorageUnit(Storage.GIGABYTE, gigabytesValue, Storage.MEGABYTE);
    }

    /**
     * Converts the received value from gigabytes to terabytes.
     *
     * @param gigabytesValue the value in gigabytes.
     *
     * @return the value in terabytes.
     */
    public static double convertGigabytesToTerabytes(double gigabytesValue) {
        return convertStorageUnit(Storage.GIGABYTE, gigabytesValue, Storage.TERABYTE);
    }

    /**
     * Converts the received value from terabytes to bytes.
     *
     * @param terabytesValue the value in terabytes.
     *
     * @return the value in bytes.
     */
    public static double convertTerabytesToBytes(double terabytesValue) {
        return convertStorageUnit(Storage.TERABYTE, terabytesValue, Storage.BYTE);
    }

    /**
     * Converts the received value from terabytes to kilobytes.
     *
     * @param terabytesValue the value in terabytes.
     *
     * @return the value in kilobytes.
     */
    public static double convertTerabytesToKilobytes(double terabytesValue) {
        return convertStorageUnit(Storage.TERABYTE, terabytesValue, Storage.KILOBYTE);
    }

    /**
     * Converts the received value from terabytes to megabytes.
     *
     * @param terabytesValue the value in terabytes.
     *
     * @return the value in megabytes.
     */
    public static double convertTerabytesToMegabytes(double terabytesValue) {
        return convertStorageUnit(Storage.TERABYTE, terabytesValue, Storage.MEGABYTE);
    }

    /**
     * Converts the received value from terabytes to gigabytes.
     *
     * @param terabytesValue the value in terabytes.
     *
     * @return the value in gigabytes.
     */
    public static double convertTerabytesToGigabytes(double terabytesValue) {
        return convertStorageUnit(Storage.TERABYTE, terabytesValue, Storage.GIGABYTE);
    }
}
