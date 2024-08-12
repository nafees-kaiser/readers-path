package com.readerspath.backend.service;

import com.readerspath.backend.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image saveImage(MultipartFile file) throws IOException;

    void editImage(Image coverImage, MultipartFile file) throws IOException;

    void deleteImage(Image coverImage);
}
