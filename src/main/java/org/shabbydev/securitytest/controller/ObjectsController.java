package org.shabbydev.securitytest.controller;

import org.shabbydev.securitytest.mapper.dto.ObjectsDTO;
import org.shabbydev.securitytest.service.ObjectsService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/objects/")
public class ObjectsController {
    private final ObjectsService objectsService;

    public ObjectsController(ObjectsService pageService) {
        this.objectsService = pageService;
    }


    @GetMapping()
    public Page<ObjectsDTO> findByPage(@RequestParam String page) {
        return objectsService.findByPage(Integer.parseInt(page));
    }

    @GetMapping("find")
    public Page<ObjectsDTO> findByPageAndTitle(@RequestParam String page, @RequestParam String title) {
        return objectsService.findByPageAndTitle(Integer.parseInt(page), "%" + title + "%");
    }

    @GetMapping("{id}")
    public ObjectsDTO findById(@PathVariable String id) {
        return objectsService.findById(Long.valueOf(id));
    }
}
