package com.example.tamarindtreeschool.controller;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

import com.example.tamarindtreeschool.model.Testimonials;
import com.example.tamarindtreeschool.service.TestimonialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/testimonials")
@CrossOrigin(origins = "https://schooltamarindtree.netlify.app/") 
public class TestimonialController {

    private static final String UPLOAD_DIR = "/app/uploads"; // Render-safe


    @Autowired
    private TestimonialService testimonialService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addTestimonial(
            @RequestParam String name,
            @RequestParam String batch,
            @RequestParam String testimonial,
            @RequestParam MultipartFile image) {

        try {
            // Save image
            String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(imageName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Saving to: " + filePath.toAbsolutePath());

            // Create object
            Testimonials newTestimonial = new Testimonials();
            newTestimonial.setName(name);
            newTestimonial.setBatch(batch);
            newTestimonial.setReview(testimonial);
            newTestimonial.setImageFilename(imageName);

            // Save to DB
            testimonialService.addTestimonial(newTestimonial);

            return ResponseEntity.ok("Testimonial submitted successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to upload and save testimonial.");
        }
    }

    @GetMapping
	public List<Testimonials> getTestimonials(){
		return testimonialService.getAll();
	}
	
}
