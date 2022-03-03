package com.example.cloudinary.services.impl;

import com.cloudinary.Cloudinary;
import com.example.cloudinary.services.CloudinaryImage;
import com.example.cloudinary.services.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";
    private final Cloudinary cloudinary;


    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = this.cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmedia.istockphoto.com%2Fvectors%2Ferror-page-not-found-vector-id962223846%3Fk%3D6%26m%3D962223846%26s%3D612x612%26w%3D0%26h%3DFmE7jhtdf9FHMFR8m336RnNaCFFEGDZo-xqB-v3CN1M%3D&f=1&nofb=1");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");
            return new CloudinaryImage()
                    .setPublicId(publicId)
                    .setUrl(url);
        } finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
