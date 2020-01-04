package com.cvtopdf.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Jobs")
@SequenceGenerator(name = "default_gen", sequenceName = "job_seq", allocationSize = 1)
public class Job extends BaseEntity {

    @Column( nullable = false)
    private String name;

    @Column( nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column( nullable = false)
    private String position;

    @Column( nullable = false)
    private String scopeOfDuties;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Job() {
    }

    public Job(String name, LocalDateTime startDate, LocalDateTime endDate, String position, String scopeOfDuties, User user) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.scopeOfDuties = scopeOfDuties;
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

    public String getScopeOfDuties() {
        return scopeOfDuties;
    }

    public void setScopeOfDuties(String scopeOfDuties) {
        this.scopeOfDuties = scopeOfDuties;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
