package org.example;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


public class Main {
    public static void main(String[] args) {
        String uploadFilename = "test-file-" + LocalDateTime.now() + ".txt";
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("<BUCKET_NAME>")
                .key(uploadFilename)
                .build();
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create("<ACCESS_KEY_ID>", "<SECRET_ACCESS_KEY>");
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);
        Region region = Region.US_EAST_1;
        S3Client s3Client = S3Client.builder().region(region).credentialsProvider(staticCredentialsProvider).build();

        File file = new File("<FILE_PATH>");
        s3Client.putObject(request, Path.of(file.toURI()));
    }
}