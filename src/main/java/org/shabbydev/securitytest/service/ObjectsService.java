package org.shabbydev.securitytest.service;

import org.shabbydev.securitytest.mapper.custom.ObjectsMapper;
import org.shabbydev.securitytest.mapper.dto.ObjectsDTO;
import org.shabbydev.securitytest.mapper.dto.PageDTO;
import org.shabbydev.securitytest.repo.ObjectsEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ObjectsService {
    private final ObjectsEntityRepository objectsEntityRepository;

    private final ObjectsMapper objectsMapper;
    private static final int PAGE_SIZE = 6;


    public ObjectsService(ObjectsEntityRepository objectsEntityRepository, ObjectsMapper objectsMapper) {
        this.objectsEntityRepository = objectsEntityRepository;
        this.objectsMapper = objectsMapper;
    }


    public Page<ObjectsDTO> findByPage(int page) {
        return objectsEntityRepository.findAll(PageRequest.of(page, PAGE_SIZE)).map(objectsMapper::toDto);
    }

    public Page<ObjectsDTO> findByPageAndTitle(int page, String title) {
        return objectsEntityRepository.findAll(PageRequest.of(page, PAGE_SIZE)).map(objectsMapper::toDto);
    }

    public ObjectsDTO findById(Long id) {
        return objectsEntityRepository.findById(id).map(objectsMapper::toDto).orElse(null);
    }
}
