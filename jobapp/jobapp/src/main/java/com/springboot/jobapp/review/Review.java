 package com.springboot.jobapp.review;

import com.springboot.jobapp.company.Company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String desc;
    private Double rating;


    @ManyToOne
    Company company;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", title=" + title + ", desc=" + desc + ", rating=" + rating + ", company="
                + company + "]";
    }

    public Review(Long id, String title, String desc, Double rating, Company company) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.rating = rating;
        this.company = company;
    }

    
    
    
}