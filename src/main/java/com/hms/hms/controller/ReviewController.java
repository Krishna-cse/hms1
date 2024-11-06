package com.hms.hms.controller;

import com.hms.hms.entity.AppUser;
import com.hms.hms.entity.Property;
import com.hms.hms.entity.Review;
import com.hms.hms.repository.PropertyRepository;
import com.hms.hms.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;

    public ReviewController(PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }

    //http:localhost:8080/api/v1/review?propertyId=1
    @PostMapping
    public ResponseEntity<Review> write(@RequestBody Review review, @RequestParam long propertyId, @AuthenticationPrincipal AppUser user){
        Property property = propertyRepository.findById(propertyId).get();
        review.setProperty(property);
        review.setAppUser(user);

        Review savedReview = reviewRepository.save(review);

        return new ResponseEntity<>(savedReview, HttpStatus.OK);

    }
}