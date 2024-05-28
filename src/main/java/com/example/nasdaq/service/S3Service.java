package com.example.nasdaq.service;

public interface S3Service {
    public String getFileFromS3(String bucket_name, String file_name);
}
