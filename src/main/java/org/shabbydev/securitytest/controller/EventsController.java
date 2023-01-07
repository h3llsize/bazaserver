package org.shabbydev.securitytest.controller;

import org.shabbydev.securitytest.mapper.dto.EventsDTO;
import org.shabbydev.securitytest.mapper.dto.PageDTO;
import org.shabbydev.securitytest.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

@RequestMapping("/api/events/")
@RestController
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping()
    public Page<EventsDTO> findByPage(@RequestParam String page) {
        return eventsService.findByPage(Integer.parseInt(page));
    }

    @GetMapping("find")
    public Page<EventsDTO> findByPageAndTitle(@RequestParam String page, @RequestParam String title) {
        return eventsService.findByPageAndTitle(Integer.parseInt(page), "%" + title + "%");
    }

    @GetMapping("{id}")
    public EventsDTO findById(@PathVariable String id) {
        return eventsService.findById(Long.valueOf(id));
    }

}
