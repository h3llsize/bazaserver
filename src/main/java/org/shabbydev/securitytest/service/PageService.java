package org.shabbydev.securitytest.service;

import org.shabbydev.securitytest.mapper.custom.PageMapper;
import org.shabbydev.securitytest.mapper.dto.PageDTO;
import org.shabbydev.securitytest.repo.PageEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    private final PageEntityRepository pageEntityRepository;

    private final PageMapper pageMapper;
    private static final int PAGE_SIZE = 6;

    public PageService(PageEntityRepository pageEntityRepository, PageMapper pageMapper) {
        this.pageEntityRepository = pageEntityRepository;
        this.pageMapper = pageMapper;
    }


    public Page<PageDTO> findByPage(int page) {
        return pageEntityRepository.findAll(PageRequest.of(page, PAGE_SIZE)).map(pageMapper::toDTO);
    }

    public Page<PageDTO> findByPageAndTitle(int page, String title) {
        return pageEntityRepository.findAll(PageRequest.of(page, PAGE_SIZE)).map(pageMapper::toDTO);
    }

    public PageDTO findById(Long id) {
        return pageEntityRepository.findById(id).map(pageMapper::toDTO).orElse(null);
    }
}
