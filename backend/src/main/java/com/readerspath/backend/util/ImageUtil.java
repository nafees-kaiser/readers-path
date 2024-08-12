package com.readerspath.backend.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtil {
    private final static int BITE_SIZE = 4 * 1024;

    public static byte[] compressImage(byte[] image) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(image);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] tmp = new byte[BITE_SIZE];

        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] image) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(image);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] tmp = new byte[BITE_SIZE];

        while (!inflater.finished()) {
            int count = inflater.inflate(tmp);
            outputStream.write(tmp, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }
}
