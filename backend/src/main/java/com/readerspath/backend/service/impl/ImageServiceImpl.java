package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.Image;
import com.readerspath.backend.repository.ImageRepository;
import com.readerspath.backend.service.ImageService;
import com.readerspath.backend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        Image image = Image.builder()
                .imageName(file.getOriginalFilename())
                .imageType(file.getContentType())
                .imageBytes(ImageUtil.compressImage(file.getBytes()))
                .build();

        return imageRepository.save(image);
    }

    @Override
    public void editImage(Image coverImage, MultipartFile file) throws IOException {
        Image image = imageRepository.findById(coverImage.getId()).orElse(null);
        if (image == null || file == null) {
            return;
        }
        image.setImageBytes(ImageUtil.compressImage(file.getBytes()));
        image.setImageName(file.getOriginalFilename());
        image.setImageType(file.getContentType());
        imageRepository.save(image);
    }

    @Override
    public void deleteImage(Image coverImage) {
        imageRepository.delete(coverImage);
    }


}
