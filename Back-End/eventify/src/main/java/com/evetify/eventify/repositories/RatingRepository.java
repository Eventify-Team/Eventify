package com.evetify.eventify.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.evetify.eventify.models.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
