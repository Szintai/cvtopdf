package com.cvtopdf.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.websocket.Decoder;

@Entity
@Table(name="Photos")
@SequenceGenerator(name = "default_gen", sequenceName = "photo_seq", allocationSize = 1)
public class Photo extends BaseEntity {

    private String title;




}
