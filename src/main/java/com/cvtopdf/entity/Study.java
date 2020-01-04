package com.cvtopdf.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Studies")
@SequenceGenerator(name = "default_gen", sequenceName = "study_seq", allocationSize = 1)
public class Study extends BaseEntity {

    @Column( nullable = false)
    private String name;

    @Column( nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String result;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Study() {
    }

    public Study(String name, LocalDateTime startDate, LocalDateTime endDate, String result, User user) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.result = result;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
