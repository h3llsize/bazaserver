package org.shabbydev.securitytest.service;

import org.shabbydev.securitytest.entity.PageEntity;
import org.shabbydev.securitytest.jwt.TokenChecker;
import org.shabbydev.securitytest.mapper.custom.PageMapper;
import org.shabbydev.securitytest.mapper.dto.PageDTO;
import org.shabbydev.securitytest.mapper.dto.PageRequestDTO;
import org.shabbydev.securitytest.repo.PageEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.time.Instant;
import java.util.Optional;

@Service
public class PageService {
    private final PageEntityRepository pageEntityRepository;

    private final PageMapper pageMapper;

    private final TokenChecker tokenChecker;
    private static final int PAGE_SIZE = 6;

    public PageService(PageEntityRepository pageEntityRepository, PageMapper pageMapper, TokenChecker tokenChecker) {
        this.pageEntityRepository = pageEntityRepository;
        this.pageMapper = pageMapper;
        this.tokenChecker = tokenChecker;
    }

    public Page<PageDTO> findForModerate(int page) {
        return pageEntityRepository.findAllForModerate(PageRequest.of(page, PAGE_SIZE)).map(pageMapper::toDTO);
    }


    public Page<PageDTO> findByPage(int page) {
        return pageEntityRepository.findAll(PageRequest.of(page, PAGE_SIZE)).map(pageMapper::toDTO);
    }

    public Page<PageDTO> findByPageAndTitle(int page, String query) {
        return pageEntityRepository.findByQuery(PageRequest.of(page, PAGE_SIZE), query).map(pageMapper::toDTO);
    }

    public PageDTO findById(Long id) {
        return pageEntityRepository.findById(id).map(pageMapper::toDTO).orElse(null);
    }

    public ResponseEntity<String> addPost(PageRequestDTO pageDTO, String realPathToUploads) throws IOException {

        MultipartFile file = pageDTO.getFile();

        new File(realPathToUploads).mkdirs();

        file.transferTo(new File(realPathToUploads + file.getOriginalFilename()));

        PageEntity page = new PageEntity();
        page.setImageName(pageDTO.getImageName());
        page.setChecked(false);
        page.setAuthor(tokenChecker.getUserByToken(pageDTO.getAccessToken()));
        page.setPublishDate(null);
        page.setCreatedDate(Instant.now());
        page.setDescription(pageDTO.getDescription());
        page.setSlug(pageDTO.getSlug());
        page.setTitle(pageDTO.getTitle());

        page.setImageUrl(realPathToUploads + file.getOriginalFilename());

        pageEntityRepository.save(page);
        return ResponseEntity.ok("Ваш пост был отправлен на модерацию");
    }

    public ResponseEntity<String> checkPost(Long postId) {
        Optional<PageEntity> page = pageEntityRepository.findById(postId);
        PageEntity pageEntity = page.orElseThrow(() -> {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid post id");
        });

        pageEntity.setPublishDate(Instant.now());
        pageEntity.setChecked(true);

        pageEntityRepository.save(pageEntity);

        return ResponseEntity.ok("Пост был успешно опубликован");
    }

    public ResponseEntity<String> declinePost(Long postId) {
        Optional<PageEntity> page = pageEntityRepository.findById(postId);
        PageEntity pageEntity = page.orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid post id");
        });

        pageEntityRepository.delete(pageEntity);

        return ResponseEntity.ok("Вы успешно удалили пост");
    }
}
