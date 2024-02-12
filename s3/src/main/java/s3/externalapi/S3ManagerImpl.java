package s3.externalapi;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.List;

public class S3ManagerImpl implements S3Manager {

    private S3Client s3Client;

    public S3ManagerImpl() {
        this.s3Client = S3Client.builder().build();
    }

    public S3ManagerImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public S3ManagerImpl(AwsCredentialsProvider awsCredentialsProvider, Region region) {
        this.s3Client = S3Client.builder().credentialsProvider(awsCredentialsProvider).region(region).build();
    }

    public S3ManagerImpl(AwsCredentialsProvider awsCredentialsProvider) {
        this.s3Client = S3Client.builder().credentialsProvider(awsCredentialsProvider).build();
    }

    public S3ManagerImpl(Region region) {
        this.s3Client = S3Client.builder().region(region).build();
    }

    public S3ManagerImpl(String region) {
        new S3ManagerImpl(Region.of(region));
    }

    @Override
    public ListBucketsResponse listBuckets() {
        return s3Client.listBuckets();
    }

    public DeleteBucketResponse deleteBucket(String bucketName) {

        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                .bucket(bucketName)
                .build();

        return s3Client.deleteBucket(deleteBucketRequest);
    }

    @Override
    public DeleteObjectsResponse deleteObjects(String bucketName, List<ObjectIdentifier> s3Objects) {

        Delete delete = Delete.builder()
                .objects(s3Objects)
                .build();

        DeleteObjectsRequest deleteObjectRequest = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(delete)
                .build();

        return s3Client.deleteObjects(deleteObjectRequest);
    }

    @Override
    public ListObjectsResponse listObjects(String bucketName, String prefix) {

        ListObjectsRequest listObjectsRequest = ListObjectsRequest
                .builder()
                .bucket(bucketName)
                .prefix(prefix)
                .build();

        return s3Client.listObjects(listObjectsRequest);
    }

    @Override
    public ListObjectsResponse listObjects(String bucketName) {
        return listObjects(bucketName, null);
    }


    @Override
    public RestoreObjectResponse restoreObject(String bucketName, String key) {

        RestoreObjectRequest restoreObjectRequest = RestoreObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.restoreObject(restoreObjectRequest);
    }
}
