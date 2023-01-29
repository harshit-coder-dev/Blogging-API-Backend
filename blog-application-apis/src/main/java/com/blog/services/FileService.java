package com.blog.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadImage(String path, MultipartFile file) throws IOException; // means iss path pe ye wala file nikalni hai

    InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
