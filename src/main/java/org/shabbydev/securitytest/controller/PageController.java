package org.shabbydev.securitytest.controller;

import org.shabbydev.securitytest.mapper.dto.PageDTO;
import org.shabbydev.securitytest.service.PageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
@RequestMapping("/api/post/")
@RestController
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping()
    public Page<PageDTO> findByPage(@RequestParam String page) {
        return pageService.findByPage(Integer.parseInt(page));
    }

    @GetMapping("find")
    public Page<PageDTO> findByPageAndTitle(@RequestParam String page, @RequestParam String title) {
        return pageService.findByPageAndTitle(Integer.parseInt(page), "%" + title + "%");
    }

    @GetMapping("post")
    public PageDTO findById(@RequestParam String id) {
        return pageService.findById(Long.valueOf(id));
    }
}
