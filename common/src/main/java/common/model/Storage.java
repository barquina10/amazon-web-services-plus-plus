package common.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum Storage {

    BYTE("BYTE"),
    KILOBYTE("KILOBYTE"),
    MEGABYTE("MEGABYTE"),
    GIGABYTE("GIGABYTE"),
    TERABYTE("TERABYTE"),
    PETABYTE("PETABYTE"),
    EXABYTE("EXABYTE"),
    ZETTABYTE("ZETTABYTE"),
    YOTTABYTE("YOTTABYTE"),
    BRONTOBYTE("BRONTOBYTE");
    private final String storageName;

    Storage(final String storageName) {
            this.storageName = storageName;
        }

    @Override
    public String toString() {
        return storageName;
        }

    public static Storage getStorageByName(String storageName) {

        if (StringUtils.isEmpty(storageName)) {
            return null;
        } else {
            return Arrays.stream(Storage.values())
                    .filter(storage -> storage.storageName.equalsIgnoreCase(storageName))
                    .findAny()
                    .orElse(null);
        }
    }
}
