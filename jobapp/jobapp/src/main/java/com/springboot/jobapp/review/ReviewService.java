package com.springboot.jobapp.review;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.springboot.jobapp.company.Company;
import com.springboot.jobapp.company.CompanyService;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewService(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = this.reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    public boolean addCompanyReview(Review review, Long companyId) {
        Company company = this.companyService.getCompany(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = this.getAllReviews(companyId);

        Review review = reviews.stream().filter(r -> r.getId() == (reviewId)).findFirst().orElse(null);
        return review;
    }

    public boolean updateReview(Long companyId, Long id, Review review) {

        Company company = this.companyService.getCompany(companyId);
        if (company == null) {
            return false;
        }

        List<Review> reviews = this.getAllReviews(companyId);

        Review reviewid = reviews.stream()
                .filter(r -> Objects.equals(r.getId(), id))
                .findFirst()
                .orElse(null);

        if (reviewid == null) {
            return false;
        }

        // Do NOT change company unless intentionally required
        reviewid.setDesc(review.getDesc());
        reviewid.setRating(review.getRating());
        reviewid.setTitle(review.getTitle());

        this.reviewRepository.save(reviewid);

        return true;
    }

    public boolean deleteReview(Long companyId, Long reviewId) {

        Company company = this.companyService.getCompany(companyId);
        if (company == null) {
            return false;
        }

        if (!reviewRepository.existsById(reviewId)) {
            return false;
        }

        reviewRepository.deleteById(reviewId);
        return true;
    }

}
