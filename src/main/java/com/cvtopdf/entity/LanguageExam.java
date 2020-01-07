package com.cvtopdf.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="LanguageExams")
@SequenceGenerator(name = "default_gen", sequenceName = "le_seq", allocationSize = 1)
public class LanguageExam {

    @Column( nullable = false)
    private String type;

    @Column( nullable = false)
    private String language;

    @Column( nullable = false)
    private String level;

    @Column( nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LanguageExam() {
    }
}
