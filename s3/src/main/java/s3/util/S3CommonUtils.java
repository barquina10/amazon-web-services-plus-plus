package s3.util;

import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;
import java.util.stream.Collectors;

public final class S3CommonUtils {

    private S3CommonUtils() {}

    public static List<ObjectIdentifier> convertKeysToObjectIdentifiers(List<String> keys) {

        return keys
                .stream()
                .map(key -> ObjectIdentifier.builder().key(key).build())
                .collect(Collectors.toList());
    }

    public static List<ObjectIdentifier> convertS3ObjectsToObjectIdentifiers(List<S3Object> s3Objects) {

        return s3Objects
                .stream()
                .map(s3Object -> ObjectIdentifier.builder().key(s3Object.key()).build())
                .collect(Collectors.toList());
    }

    public static boolean isDirectory(String key) {
        return key.endsWith(Constants.SLASH_CHAR);
    }

    public static boolean isFile(String key) {
        return !key.endsWith(Constants.SLASH_CHAR);
    }
}
