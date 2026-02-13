package com.springboot.jobapp.review;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService( ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReview() throws Exception {
      try {
            return this.reviewRepository.findAll();
      } catch (Exception e) {
        throw new Exception(e);
      }
    }

    public void createReview(Review review) throws Exception {
       try {
            this.reviewRepository.save(review);
       } catch (Exception e) {
            throw new Exception(e);
       }
    }

    public Review getReview(Long id) throws Exception {
       try {
            Optional<Review> reOptional =  this.reviewRepository.findById(id);
            return reOptional.get();
       } catch (Exception e) {
            throw new Exception(e);
       }
    }

    public void updateReview(Long id, Review review) throws Exception{
      try {
            Optional<Review> optional = this.reviewRepository.findById(id);
            if (optional.isPresent()) {
                Review review2 = optional.get();

                review2.setCompany(review.getCompany());
                review2.setRating(review.getRating());
                review2.setReview(review.getReview());

                this.reviewRepository.save(review2);
            }
      } catch (Exception e) {
         throw new Exception(e);
      }
    }

    public void deleteReview(Long id) throws Exception {

        try {
            this.reviewRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


}
