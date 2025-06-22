package com.example.tamarindtreeschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tamarindtreeschool.model.Testimonials;
import com.example.tamarindtreeschool.repository.TestimonialRepository;

@Service
public class TestimonialService {
	
	@Autowired
	private TestimonialRepository testimonialRepo;
	
	public Testimonials addTestimonial(Testimonials testimonial) {
		return testimonialRepo.save(testimonial);
		
	}
	
	public List<Testimonials> getAll(){
		return testimonialRepo.findAll();
	}

}
