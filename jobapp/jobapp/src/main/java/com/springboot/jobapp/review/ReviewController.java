package com.springboot.jobapp.review;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/company/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) throws Exception {
        try {
            List<Review> reviews = this.reviewService.getAllReviews(companyId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e + " Something went wrong");
        }

    }

    @PostMapping("/review")
    public ResponseEntity<String> addCompanyReview(@RequestBody Review review, @PathVariable Long companyId)
            throws Exception {
        try {
            boolean added = this.reviewService.addCompanyReview(review, companyId);
            if (added) {
                return new ResponseEntity<>("Review added sucessfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("failed to add Review", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            throw new Exception(e + " Something went wrong");
        }
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long id) throws Exception {
        try {
            Review review = this.reviewService.getReviewById(companyId, id);
            if (review != null)
                return new ResponseEntity<Review>(review, HttpStatus.OK);
            else
                return new ResponseEntity<Review>(review, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new Exception(e + " Something went wrong");

        }
    }

    @PutMapping("review/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long id,
            @RequestBody Review review) throws Exception {
        try {
            boolean updated = this.reviewService.updateReview(companyId, id, review);
            if (updated)
                return new ResponseEntity<String>("Review updated sucessfully", HttpStatus.OK);
            else
                return new ResponseEntity<String>("failed to update Review", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new Exception(e + " Something went wrong");
        }
    }

    @DeleteMapping("review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long id) throws Exception {
        try {
            boolean isDeleted = this.reviewService.deleteReview(companyId, id);
            if (isDeleted)
                return new ResponseEntity<String>("Review is Deleted sucessfully", HttpStatus.OK);
            else
                return new ResponseEntity<String>("failed to Delete Review", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new Exception(e + " Something went wrong");
        }
    }
}