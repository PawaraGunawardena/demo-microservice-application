package com.demo.application.publicationservice.controller;

import com.demo.application.publicationservice.dto.PublicationRequest;
import com.demo.application.publicationservice.dto.PublicationResponse;
import com.demo.application.publicationservice.service.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publication")
@RequiredArgsConstructor
@Slf4j
public class PublicationController {

    private final PublicationService publicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDocument(@RequestBody PublicationRequest publicationRequest) {
        log.info("Publication [{}] request received.", publicationRequest.getName());
        publicationService.createPublication(publicationRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PublicationResponse> getAllPublications() {
        log.info("Get all publications request received.");
        return publicationService.getAllPublications();
    }
}
