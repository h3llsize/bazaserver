package org.shabbydev.securitytest.service;

import org.shabbydev.securitytest.mapper.custom.EventsMapper;
import org.shabbydev.securitytest.mapper.dto.EventsDTO;
import org.shabbydev.securitytest.repo.EventsEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EventsService {

    private static final int PAGE_SIZE = 6;

    private final EventsEntityRepository eventsEntityRepository;

    private final EventsMapper eventsMapper;

    public EventsService(EventsEntityRepository eventsEntityRepository, EventsMapper eventsMapper) {
        this.eventsEntityRepository = eventsEntityRepository;
        this.eventsMapper = eventsMapper;
    }

    public Page<EventsDTO> findByPage(int page) {
        return eventsEntityRepository.findAll(PageRequest.of(page, PAGE_SIZE)).map(eventsMapper::toDto);
    }

    public Page<EventsDTO> findByPageAndTitle(int page, String title) {
        return eventsEntityRepository.findAll(PageRequest.of(page, PAGE_SIZE)).map(eventsMapper::toDto);
    }

    public EventsDTO findById(Long id) {
        return eventsEntityRepository.findById(id).map(eventsMapper::toDto).orElse(null);
    }

}
