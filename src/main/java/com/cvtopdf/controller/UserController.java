package com.cvtopdf.controller;


import com.cvtopdf.entity.Photo;
import com.cvtopdf.service.PhotoService;
import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.service.UserService;
import com.cvtopdf.entity.User;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class UserController {


    private final UserService userService;

    private final PhotoService photoService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private User authorizedUser;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService userService, PhotoService photoService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.photoService = photoService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/profile")
    public String userDetails(Model model){

        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user= userService.findById(authorizedUser.getId());


        model.addAttribute("user", user);

        return "user/main";
    }


    @GetMapping(value = "/profile/edit")
    public String findById( Model model) {

        model.addAttribute("user", userService.findById(authorizedUser.getId()));

        return "user/edit";
    }

    @PostMapping(value = "/profile/edit")
    public String update( Model model, User user) {

        user.setId(authorizedUser.getId());
        user.setJobs(userService.findById(authorizedUser.getId()).getJobs());
        user.setStudies(userService.findById(authorizedUser.getId()).getStudies());
        String encodedPassword="";
        encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        model.addAttribute("user",  userService.save(user));


        return "redirect:/profile";
    }

    @GetMapping("/profile/cv")
    public String cv(Model model) throws IOException {

        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user= userService.findById(authorizedUser.getId());

   /*     BufferedImage photo=new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);

        if (user.getPhoto() != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(user.getPhoto().getImage());
            photo = ImageIO.read(bis);
        }

*/
        model.addAttribute("photo_id", user.getId());
        model.addAttribute("user", user);


        return "user/cv";
    }



    @PostMapping("/profile/cv/pictureUpload")
    public String prodUpload( @RequestParam("picture") MultipartFile picture) {

        try {
            byte[] bytes = picture.getBytes();

            Photo photo=new Photo();

            photo.setImage(bytes);

            photo.setExtension(Files.getFileExtension(picture.getOriginalFilename()));



            User user= userService.findById(authorizedUser.getId());

            user.setPhoto(photo);


            userService.save(user);
           // photoService.save(photo);


        } catch (IOException e) {

            e.printStackTrace();
        }




        return "redirect:/profile/cv";
    }

    @GetMapping(value = "/photo/{photo_id}"/*, produces = MediaType.IMAGE_JPEG_VALUE*/)
    public ResponseEntity<byte[]> getImage(@PathVariable("photo_id") Long photoId) throws IOException {

        User user= userService.findById(photoId);
        byte[] imageContent =user.getPhoto().getImage();
        final HttpHeaders headers = new HttpHeaders();
    //    headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }


}
