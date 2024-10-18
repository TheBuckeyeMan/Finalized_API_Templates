package com.example.lambdatemplate.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3LoggingService {
    private static final Logger log = LoggerFactory.getLogger(S3LoggingService.class);
    private final S3Service s3Service;

    public S3LoggingService(S3Service s3Service){
        this.s3Service = s3Service;
    }
    //DOwnload Existing S3 File
    public String downloadLogFilesFromS3(String bucketName, String logFileKey){
        try{
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(logFileKey)
                .build();
            ResponseInputStream<GetObjectResponse> s3Object = s3Service.getObject(getObjectRequest);
            return new BufferedReader(new InputStreamReader(s3Object))
                .lines()
                .collect(Collectors.joining("\n"));
        } catch (S3Exception e) {
            log.error("Log file not found, creating a new log file.", e);
            return ""; // Return empty if log file not found
        }
    }
    //Append new log entry
    public String appendLogEntry(String existingContent, String newLogEntry){
        return existingContent + "\n" + newLogEntry;
    }

    //Upload the updated file to the s3 bucket
    public void uploadLogFileToS3(String bucketName, String logFileKey, String updatedContent){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/log-file.txt"))){
            writer.write(updatedContent); //write theupdated content to the local file
            s3Service.uploadFile(bucketName, logFileKey, updatedContent);
            log.info("Log file successfuly uploaded to s3");
        } catch (IOException e) {
            log.error("Error while writing the log file to s3");
        }
    }
    //Log message to s3(This method calls other steps)
    public void logMessageToS3(String message, String logFileKey){
        String bucketName = "logging-event-driven-bucket-1220-16492640";
        try{
            String existingContent = downloadLogFilesFromS3(bucketName, logFileKey); //DOwnload the existing Log File content
            String updatedContent = appendLogEntry(existingContent, message); //Append tge new log message
            uploadLogFileToS3(bucketName, logFileKey, updatedContent);
    } catch (Exception e){
        log.error("Error while logging log messages to S3", e);
        }
    }
}
