package com.example.tamarindtreeschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tamarindtreeschool.model.Testimonials;

public interface TestimonialRepository extends JpaRepository<Testimonials,Long> {

}
