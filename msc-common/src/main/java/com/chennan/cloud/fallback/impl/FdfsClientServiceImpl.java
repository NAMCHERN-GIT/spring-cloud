package com.chennan.cloud.fallback.impl;

import com.chennan.cloud.service.FdfsClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public class FdfsClientServiceImpl implements FdfsClientService {
    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public byte[] downloadFileBytes(String fileUrl) {
        return new byte[0];
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(String fileUrl, String fileName, HttpServletRequest request) {
        return null;
    }
}
