package com.demo.application.publicationservice.service;

import com.demo.application.publicationservice.dto.PublicationRequest;
import com.demo.application.publicationservice.dto.PublicationResponse;
import com.demo.application.publicationservice.model.Publication;
import com.demo.application.publicationservice.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublicationService {
    private final PublicationRepository publicationRepository;

    public void createPublication(PublicationRequest publicationRequest) {
        Publication publication = Publication.builder()
                .name(publicationRequest.getName())
                .type(publicationRequest.getType())
                .author(publicationRequest.getAuthor())
                .publisher(publicationRequest.getPublisher())
                .price(publicationRequest.getPrice())
                .build();
        publicationRepository.save(publication);
        log.info("Publication [{}] saved successfully.", publication.getId());
    }

    public List<PublicationResponse> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        log.info("Fetched [{}] publications successfully.", publications.size());
        return publications.stream().map(
                this::fromPublicationToPublicationResponse
        ).toList();
    }

    private PublicationResponse fromPublicationToPublicationResponse(Publication publication) {
        return PublicationResponse.builder()
                .id(publication.getId())
                .name(publication.getName())
                .type(publication.getType())
                .author(publication.getAuthor())
                .publisher(publication.getPublisher())
                .price(publication.getPrice())
                .build();
    }
}
