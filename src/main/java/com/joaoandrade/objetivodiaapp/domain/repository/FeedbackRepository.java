package com.joaoandrade.objetivodiaapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaoandrade.objetivodiaapp.domain.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
