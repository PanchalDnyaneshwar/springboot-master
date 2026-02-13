package com.springboot.jobapp.review;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public List<Review> getAllReview() throws Exception {
        try {
            List<Review> reviews = this.reviewService.getAllReview();
            return reviews;
        } catch (Exception e) {
            throw new Exception("Something went wromg", e);
        }
    }

    @PostMapping()
    public ResponseEntity<String> createReview(@RequestBody Review review) throws Exception {
        try {
            this.reviewService.createReview(review);
            return new ResponseEntity<String>("Review added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception("Something went wrom g" + e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) throws Exception {
        try {
            Review review = this.reviewService.getReview(id);
            return new ResponseEntity<Review>(review, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Something went wromg" + e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Review review) throws Exception {
        try {
            this.reviewService.updateReview(id, review);
            return new ResponseEntity<String>("Review updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Something went wromg" + e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) throws Exception {
        try {
            this.reviewService.deleteReview(id);
            return new ResponseEntity<String>("Review deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Something went wromg" + e);
        }

    }

}
