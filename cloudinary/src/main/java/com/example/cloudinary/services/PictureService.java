package com.example.cloudinary.services;

import com.example.cloudinary.model.entities.PictureEntity;

import java.util.List;

public interface PictureService {
    void addPicture(PictureEntity picture);

    List<PictureEntity> findAllPictures();

    void deleteAllPicturesByPublicId(String publicId);
}
