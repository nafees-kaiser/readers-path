package com.readerspath.backend.model;

import com.readerspath.backend.util.ImageUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;

import java.io.IOException;
import java.util.Base64;
import java.util.zip.DataFormatException;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Image extends BaseEntity<Long> {
    private String imageName;
    private String imageType;

    @Lob
    @Column(length = 1000)
    private byte[] imageBytes;

    public String getImageEncoded() throws DataFormatException, IOException {
        byte[] imageBytes = this.getImageBytes();
        imageBytes = ImageUtil.decompressImage(imageBytes);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public byte[] getImageDecompressed() throws DataFormatException, IOException {
        byte[] imageBytes = this.getImageBytes();
        return ImageUtil.decompressImage(imageBytes);
    }
}
