package com.cvtopdf.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="Photos")
@SequenceGenerator(name = "default_gen", sequenceName = "photo_seq", allocationSize = 1)
public class Photo extends BaseEntity {

    @Lob
    private byte[] image;

    private String extension;

    public Photo() {
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }



    @Override
    public String toString() {
        return "Photo{" +
                "image=" + Arrays.toString(image) +
                ", extension='" + extension + '\'' +
                '}';
    }
}
