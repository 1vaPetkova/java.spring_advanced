package com.example.cloudinary.web;

import com.example.cloudinary.model.binding.PictureBindingModel;
import com.example.cloudinary.model.entities.PictureEntity;
import com.example.cloudinary.services.CloudinaryImage;
import com.example.cloudinary.services.CloudinaryService;
import com.example.cloudinary.services.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Controller
public class PicturesController {

    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;

    public PicturesController(CloudinaryService cloudinaryService, PictureService pictureService) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
    }

    @GetMapping("/pictures/add")
    public String addPicture() {
        return "add";
    }

    @PostMapping("/pictures/add")
    public String addPicture(PictureBindingModel bindingModel) throws IOException {
        PictureEntity picture = createPictureEntity(bindingModel.getPicture(), bindingModel.getTitle());

        this.pictureService.addPicture(picture);
        return "redirect:/pictures/all";
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);
        return new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());
    }


    @GetMapping("/pictures/all")
    public String allPictures(Model model) {
        return "all";
    }
}
