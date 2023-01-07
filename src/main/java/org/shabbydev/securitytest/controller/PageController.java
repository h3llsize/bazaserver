package org.shabbydev.securitytest.controller;

import org.shabbydev.securitytest.jwt.TokenChecker;
import org.shabbydev.securitytest.mapper.dto.PageDTO;
import org.shabbydev.securitytest.mapper.dto.PageRequestDTO;
import org.shabbydev.securitytest.service.PageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@RequestMapping("/api/post/")
@RestController
public class PageController {

    private final PageService pageService;

    private final TokenChecker tokenChecker;

    public PageController(PageService pageService, TokenChecker tokenChecker) {
        this.pageService = pageService;
        this.tokenChecker = tokenChecker;
    }

    @GetMapping()
    public Page<PageDTO> findByPage(@RequestParam String page) {
        return pageService.findByPage(Integer.parseInt(page));
    }

    @GetMapping("find")
    public Page<PageDTO> findByPageAndTitle(@RequestParam String page, @RequestParam String query) {
        return pageService.findByPageAndTitle(Integer.parseInt(page), "%" + query.toLowerCase() + "%");
    }

    @GetMapping("moderate")
    public Page<PageDTO> findForModerate(@RequestParam String page, @RequestParam String accessToken) {
        if(tokenChecker.getRoleByToken(accessToken) < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role");

        return pageService.findForModerate(Integer.parseInt(page));
    }

    @GetMapping("{id}")
    public PageDTO findById(@PathVariable String id) {
        return pageService.findById(Long.valueOf(id));
    }

    @PostMapping("add")
    public ResponseEntity<String> addPost(@ModelAttribute PageRequestDTO page, HttpServletRequest request) throws IOException {
        String uploadsDir = "/uploads/";
        String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);

        return pageService.addPost(page, realPathToUploads);
    }

    @GetMapping("check")
    public ResponseEntity<String> checkPost(@RequestParam String id, @RequestParam String accessToken) {


        if(tokenChecker.getRoleByToken(accessToken) < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role");

        return pageService.checkPost(Long.parseLong(id));
    }

    @GetMapping("decline")
    public ResponseEntity<String> declinePost(@RequestParam String id, @RequestParam String accessToken) {

        if(tokenChecker.getRoleByToken(accessToken) < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role");

        return pageService.declinePost(Long.parseLong(id));
    }
}
