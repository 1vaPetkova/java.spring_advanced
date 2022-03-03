package com.example.cloudinary.services.impl;

import com.example.cloudinary.model.entities.PictureEntity;
import com.example.cloudinary.repositories.PictureRepository;
import com.example.cloudinary.services.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void addPicture(PictureEntity picture) {
        this.pictureRepository.save(picture);
    }

    @Override
    public List<PictureEntity> findAllPictures() {
        return this.pictureRepository.findAll();
    }

    @Override
    public void deleteAllPicturesByPublicId(String publicId) {
        this.pictureRepository.deleteAllByPublicId(publicId);
    }
}
