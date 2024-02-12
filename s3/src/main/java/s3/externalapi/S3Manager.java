package s3.externalapi;

import software.amazon.awssdk.services.s3.model.*;

import java.util.List;

public interface S3Manager {

    DeleteBucketResponse deleteBucket(String bucketName);

    DeleteObjectsResponse deleteObjects(String bucketName, List<ObjectIdentifier> s3Objects);

    ListBucketsResponse listBuckets();

    ListObjectsResponse listObjects(String bucketName);

    ListObjectsResponse listObjects(String bucketName, String prefix);

    RestoreObjectResponse restoreObject(String bucketName, String key);
}
