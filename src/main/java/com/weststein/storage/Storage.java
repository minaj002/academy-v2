package com.weststein.storage;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.weststein.infrastructure.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class Storage {

    @Value("${aws.access}")
    private String access;
    @Value("${aws.secret}")
    private String secret;


    public String store(Long businessId, MultipartFile is) {

        AWSCredentials creds = new BasicAWSCredentials(access, secret);

        AmazonS3 s3 = new AmazonS3Client(creds);
        Region usWest2 = Region.getRegion(Regions.EU_CENTRAL_1);
        s3.setRegion(usWest2);

        String key = is.getOriginalFilename();

        try {

            File file = File.createTempFile("aws-java-sdk-", ".txt");
            file.deleteOnExit();
            is.transferTo(file);
            String bucketName = "ws3.uploaded.files." + businessId;
            if (s3.listBuckets().stream().noneMatch(bucket -> bucket.getName().equals(bucketName))) {
                s3.createBucket(bucketName);
            }
            s3.putObject(new PutObjectRequest(bucketName, key, file));
            return bucketName;
        } catch (AmazonServiceException ase) {
            throw new StorageException(ase.getMessage());
        } catch (AmazonClientException ace) {
            throw new StorageException(ace.getMessage());
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

}
