package com.itstep.sound.storages.drivers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import io.minio.MinioClient;
import io.minio.MakeBucketArgs;
import io.minio.GetObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.PutObjectArgs;
import io.minio.errors.ServerException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.XmlParserException;
import io.minio.errors.InternalException;
import io.minio.messages.Bucket;

public class StorageMinioDriver implements StorageDriverInterface{
    private final MinioClient minioClient;
    private final Set<String> existingBuckets;

    public StorageMinioDriver(MinioClient minioClient) {
        this.minioClient = minioClient;
        this.existingBuckets = cacheExistingBuckets();
    }

    private Set<String> cacheExistingBuckets() {
        Set<String> buckets = new HashSet<>();
        try {
            for (Bucket bucket : minioClient.listBuckets()) {
                buckets.add(bucket.name());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error caching existing buckets", e);
        }
        return buckets;
    }

    private void checkAndCreateBucket(String bucketName) {
        if (!existingBuckets.contains(bucketName)) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                existingBuckets.add(bucketName);
            } catch (Exception e) {
                throw new RuntimeException("Error creating bucket: " + bucketName, e);
            }
        } else {
            System.out.println("Bucket '" + bucketName + "' already exists.");
        }
    }

    @Override
    public byte[] getBytes(String bucketName, String path)
            throws
            ServerException, InsufficientDataException,
            ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException,
            InternalException
    {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .build()
        )) {
            return stream.readAllBytes();
        }
    }

    @Override
    public void put(String bucketName, String path, File file)
            throws
            IOException, ServerException,
            InsufficientDataException, ErrorResponseException,
            NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException,
            InternalException
    {
        checkAndCreateBucket(bucketName);
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .filename(file.getAbsolutePath())
                        .build()
        );
    }

    @Override
    public void put(String bucketName, String path, byte[] bytes)
            throws
            IOException, ServerException,
            InsufficientDataException, ErrorResponseException,
            NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException,
            InternalException
    {
        checkAndCreateBucket(bucketName);

        try (ByteArrayInputStream bytesStream = new ByteArrayInputStream(bytes)) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .stream(bytesStream, bytesStream.available(), -1)
                            .build()
            );
        }
    }
}