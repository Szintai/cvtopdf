package com.cvtopdf.service;

import com.cvtopdf.entity.Photo;
import com.cvtopdf.repository.PhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImp implements PhotoService {


    private final PhotoRepository photoRepository;

    public PhotoServiceImp(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }
}
