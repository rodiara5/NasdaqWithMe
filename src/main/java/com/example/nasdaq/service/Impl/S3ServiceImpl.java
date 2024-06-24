package com.example.nasdaq.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.example.nasdaq.service.S3Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3ServiceImpl implements S3Service{

    @Autowired AmazonS3 s3Client;

    @Override
    public String getFileFromS3(String bucket_name, String file_name) {
        StringBuilder contentBuilder = new StringBuilder();
        BufferedReader reader = null;

        try {
            InputStream inputStream = s3Client.getObject(new GetObjectRequest(bucket_name, file_name)).getObjectContent();
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info(contentBuilder.toString());
        return contentBuilder.toString();
    }
}

