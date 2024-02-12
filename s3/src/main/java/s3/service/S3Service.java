package s3.service;

import common.util.DateUtils;
import common.util.StorageUtils;

import s3.exception.InvalidDirectoryPathException;
import s3.externalapi.S3Manager;
import s3.externalapi.S3ManagerImpl;
import s3.util.Messages;
import s3.util.S3CommonUtils;

import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.utils.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class S3Service {

    private final S3Manager s3Manager;

    public S3Service() {
        this.s3Manager = new S3ManagerImpl();
    }

    public S3Service(S3Manager s3Manager) {
        this.s3Manager = s3Manager;
    }

    public DeleteObjectsResponse deleteObjects(String bucketName, List<String> keys) {
        return s3Manager.deleteObjects(bucketName, S3CommonUtils.convertKeysToObjectIdentifiers(keys));
    }

    public DeleteBucketResponse deleteBucket(String bucketName, boolean forceDeletion) {

        if (forceDeletion) {
            emptyBucket(bucketName);
        }
        return s3Manager.deleteBucket(bucketName);
    }

    public List<DeleteBucketResponse> deleteBuckets(
            Map<String, Boolean> bucketsDeletionConfiguration,
            boolean continueOnFailure) {

        return bucketsDeletionConfiguration
                .entrySet()
                .stream()
                .map(entry -> {
                    try {
                        return deleteBucket(entry.getKey(), entry.getValue());
                    } catch (Exception exc) {
                        if (!continueOnFailure) {
                            throw exc;
                        }
                        // TODO: insert log here.
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    public DeleteObjectsResponse deleteDirectoryObjects(
            String bucketName,
            String directoryRelativePath)
            throws InvalidDirectoryPathException {

        if (!S3CommonUtils.isDirectory(directoryRelativePath)) {
            throw new InvalidDirectoryPathException(Messages.INVALID_DIRECTORY_PATH);
        }
        return deleteSpecificPrefixObjects(bucketName, directoryRelativePath);
    }

    public DeleteObjectsResponse deleteSpecificPrefixObjects(
            String bucketName,
            String prefix) {

        List<S3Object> s3Objects = listSpecificPrefixObjects(bucketName, prefix);
        return s3Manager.deleteObjects(bucketName, S3CommonUtils.convertS3ObjectsToObjectIdentifiers(s3Objects));
    }

    public DeleteObjectsResponse deletePosteriorDateObjects(
            String bucketName,
            Instant instant) {

        List<S3Object> s3Objects = listPosteriorDateObjects(bucketName, instant);
        return s3Manager.deleteObjects(bucketName, S3CommonUtils.convertS3ObjectsToObjectIdentifiers(s3Objects));
    }

    public DeleteObjectsResponse deletePosteriorDateObjects(
            String bucketName,
            Timestamp timestamp) {

        return deletePosteriorDateObjects(bucketName, timestamp.toInstant());
    }

    public DeleteObjectsResponse deletePosteriorDateObjects(
            String bucketName,
            Long millis) {

        return deletePosteriorDateObjects(bucketName, Instant.ofEpochMilli(millis));
    }

    public DeleteObjectsResponse deletePriorDateObjects(
            String bucketName,
            Instant instant) {

        List<S3Object> s3Objects = listPriorDateObjects(bucketName, instant);
        return s3Manager.deleteObjects(bucketName, S3CommonUtils.convertS3ObjectsToObjectIdentifiers(s3Objects));
    }

    public DeleteObjectsResponse deletePriorDateObjects(
            String bucketName,
            Timestamp timestamp) {

        return deletePriorDateObjects(bucketName, timestamp.toInstant());
    }

    public DeleteObjectsResponse deletePriorDateObjects(
            String bucketName,
            Long millis) {

        return deletePriorDateObjects(bucketName, Instant.ofEpochMilli(millis));
    }

    public DeleteObjectsResponse emptyBucket(String bucketName) {

        return s3Manager.deleteObjects(
                bucketName,
                S3CommonUtils.convertS3ObjectsToObjectIdentifiers(s3Manager.listObjects(bucketName).contents()));
    }

    public List<S3Object> listSpecificPrefixObjects(String bucketName, String prefix) {
        return s3Manager.listObjects(bucketName, prefix).contents();
    }

    public List<S3Object> listSpecificPatternObjects(String bucketName, String pattern) {

        ListObjectsResponse listObjectsResponse = s3Manager.listObjects(bucketName);

        if ((listObjectsResponse != null) &&
            (!CollectionUtils.isNullOrEmpty(listObjectsResponse.contents()))) {

            return listObjectsResponse.contents()
                    .stream()
                    .filter(s3Object -> s3Object.key().matches(pattern))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<S3Object> listSpecificSuffixObjects(String bucketName, String suffix) {

        ListObjectsResponse listObjectsResponse = s3Manager.listObjects(bucketName);

        if ((listObjectsResponse != null) &&
            (!CollectionUtils.isNullOrEmpty(listObjectsResponse.contents()))) {

            return listObjectsResponse.contents()
                    .stream()
                    .filter(s3Object -> s3Object.key().endsWith(suffix))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<S3Object> listPriorDateObjects(String bucketName, Instant instant) {

        ListObjectsResponse listObjectsResponse = s3Manager.listObjects(bucketName);

        if ((listObjectsResponse != null) &&
            (!CollectionUtils.isNullOrEmpty(listObjectsResponse.contents()))) {

            return listObjectsResponse.contents()
                    .stream()
                    .filter(s3Object -> s3Object.lastModified().isBefore(instant))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<S3Object> listPriorDateObjects(String bucketName, Timestamp timestamp) {
        return listPriorDateObjects(bucketName, timestamp.toInstant());
    }

    public List<S3Object> listPriorDateObjects(String bucketName, Long millis) {
        return listPriorDateObjects(bucketName, Instant.ofEpochMilli(millis));
    }

    public List<S3Object> listWeeksPriorObjects(String bucketName, int numberWeeks) {
        return listPriorDateObjects(bucketName, DateUtils.getWeeksPriorInstant(numberWeeks));
    }

    public List<S3Object> listMonthsPriorObjects(String bucketName, int numberMonths) {
        return listPriorDateObjects(bucketName, DateUtils.getMonthsPriorInstant(numberMonths));
    }

    public List<S3Object> listYearsPriorObjects(String bucketName, int numberYears) {
        return listPriorDateObjects(bucketName, DateUtils.getYearsPriorInstant(numberYears));
    }

    public List<S3Object> listPosteriorDateObjects(String bucketName, Instant instant) {

        ListObjectsResponse listObjectsResponse = s3Manager.listObjects(bucketName);

        if ((listObjectsResponse != null) &&
            (listObjectsResponse.contents() != null) &&
            (!listObjectsResponse.contents().isEmpty())) {

            return listObjectsResponse.contents()
                    .stream()
                    .filter(s3Object -> s3Object.lastModified().isAfter(instant))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<S3Object> listPosteriorDateObjects(String bucketName, Timestamp timestamp) {
        return listPosteriorDateObjects(bucketName, timestamp.toInstant());
    }

    public List<S3Object> listPosteriorDateObjects(String bucketName, Long millis) {
        return listPosteriorDateObjects(bucketName, Instant.ofEpochMilli(millis));
    }

    public List<Bucket> listPriorDateBuckets(Instant instant) {

        ListBucketsResponse listObjectsResponse = s3Manager.listBuckets();

        if ((listObjectsResponse != null) &&
            (!CollectionUtils.isNullOrEmpty(listObjectsResponse.buckets()))) {

            return listObjectsResponse.buckets()
                    .stream()
                    .filter(bucket -> bucket.creationDate().isBefore(instant))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<Bucket> listPriorDateBuckets(Timestamp timestamp) {
        return listPriorDateBuckets(timestamp.toInstant());
    }

    public List<Bucket> listPriorDateBuckets(Long millis) {
        return listPriorDateBuckets(Instant.ofEpochMilli(millis));
    }

    public List<Bucket> listPosteriorDateBuckets(Instant instant) {

        ListBucketsResponse listObjectsResponse = s3Manager.listBuckets();

        if ((listObjectsResponse != null) &&
            (!CollectionUtils.isNullOrEmpty(listObjectsResponse.buckets()))) {

            return listObjectsResponse.buckets()
                    .stream()
                    .filter(bucket -> bucket.creationDate().isAfter(instant))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<Bucket> listPosteriorDateBuckets(Timestamp timestamp) {
        return listPosteriorDateBuckets(timestamp.toInstant());
    }

    public List<Bucket> listPosteriorDateBuckets(Long millis) {
        return listPosteriorDateBuckets(Instant.ofEpochMilli(millis));
    }

    public Long getDirectoryStorageSizeInBytes(
            String bucketName,
            String directoryRelativePath)
            throws InvalidDirectoryPathException {

        if (!S3CommonUtils.isDirectory(directoryRelativePath)) {
            throw new InvalidDirectoryPathException(Messages.INVALID_DIRECTORY_PATH);
        }

        ListObjectsResponse listObjectsResponse = s3Manager.listObjects(bucketName, directoryRelativePath);

        Long totalSize = 0L;
        for (S3Object s3Object: listObjectsResponse.contents()) {
            totalSize += s3Object.size();
        }
        return totalSize;
    }

    public double getDirectoryStorageSizeInKilobytes(
            String bucketName,
            String directoryRelativePath)
            throws InvalidDirectoryPathException {

        return StorageUtils.convertBytesToKilobytes(getDirectoryStorageSizeInBytes(
                bucketName,
                directoryRelativePath));
    }

    public double getDirectoryStorageSizeInMegabytes(
            String bucketName,
            String directoryRelativePath)
            throws InvalidDirectoryPathException {

        return StorageUtils.convertBytesToMegabytes(getDirectoryStorageSizeInBytes(
                bucketName,
                directoryRelativePath));
    }

    public double getDirectoryStorageSizeInGigabytes(
            String bucketName,
            String directoryRelativePath)
            throws InvalidDirectoryPathException {

        return StorageUtils.convertBytesToGigabytes(getDirectoryStorageSizeInBytes(
                bucketName,
                directoryRelativePath));
    }

    public double getDirectoryStorageSizeInTerabytes(
            String bucketName,
            String directoryRelativePath)
            throws InvalidDirectoryPathException {

        return StorageUtils.convertBytesToTerabytes(getDirectoryStorageSizeInBytes(
                bucketName,
                directoryRelativePath));
    }

    public Long getBucketStorageSizeInBytes(String bucketName) {

        ListObjectsResponse listObjectsResponse = s3Manager.listObjects(bucketName);

        Long totalSize = 0L;
        for (S3Object s3Object: listObjectsResponse.contents()) {
            totalSize += s3Object.size();
        }
        return totalSize;
    }

    public double getBucketStorageSizeInKilobytes(String bucketName) {
        return StorageUtils.convertBytesToKilobytes(getBucketStorageSizeInBytes(bucketName));
    }

    public double getBucketStorageSizeInMegabytes(String bucketName) {
        return StorageUtils.convertBytesToMegabytes(getBucketStorageSizeInBytes(bucketName));
    }

    public double getBucketStorageSizeInGigabytes(String bucketName) {
        return StorageUtils.convertBytesToGigabytes(getBucketStorageSizeInBytes(bucketName));
    }

    public double getBucketStorageSizeInTerabytes(String bucketName) {
        return StorageUtils.convertBytesToTerabytes(getBucketStorageSizeInBytes(bucketName));
    }
}
