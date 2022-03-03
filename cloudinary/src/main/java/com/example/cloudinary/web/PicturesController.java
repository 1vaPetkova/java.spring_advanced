package com.example.cloudinary.web;

import com.example.cloudinary.model.binding.PictureBindingModel;
import com.example.cloudinary.model.entities.PictureEntity;
import com.example.cloudinary.model.view.PictureViewModel;
import com.example.cloudinary.services.CloudinaryImage;
import com.example.cloudinary.services.CloudinaryService;
import com.example.cloudinary.services.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    @DeleteMapping("/pictures/delete")
    public String delete(@RequestParam("public_id") String publicId) {
        if (this.cloudinaryService.delete(publicId)) {
            this.pictureService.deleteAllPicturesByPublicId(publicId);
        }
        return "redirect:/pictures/all";
    }

    @GetMapping("/pictures/all")
    public String allPictures(Model model) {
        List<PictureViewModel> pictures = this.pictureService
                .findAllPictures()
                .stream()
                .map(this::asViewModel)
                .collect(Collectors.toList());
        model.addAttribute("pictures", pictures);
        return "all";
    }


    private PictureViewModel asViewModel(PictureEntity picture) {
        return new PictureViewModel()
                .setPublicId(picture.getPublicId())
                .setTitle(picture.getTitle())
                .setUrl(picture.getUrl());
    }

}
