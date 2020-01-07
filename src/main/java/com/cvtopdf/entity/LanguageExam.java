package com.cvtopdf.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="LanguageExams")
@SequenceGenerator(name = "default_gen", sequenceName = "le_seq", allocationSize = 1)
public class LanguageExam extends BaseEntity {

    @Column( nullable = false)
    private String type;

    @Column( nullable = false)
    private String language;

    @Column( nullable = false)
    private String level;

    @Column( nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LanguageExam() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LanguageExam{" +
                "type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", level='" + level + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
