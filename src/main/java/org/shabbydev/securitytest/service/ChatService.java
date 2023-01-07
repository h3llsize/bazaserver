package org.shabbydev.securitytest.service;

import org.shabbydev.securitytest.entity.ChatEntity;
import org.shabbydev.securitytest.jwt.TokenChecker;
import org.shabbydev.securitytest.mapper.custom.ChatEntityMapper;
import org.shabbydev.securitytest.mapper.dto.ChatDTO;
import org.shabbydev.securitytest.mapper.dto.ChatRequestDTO;
import org.shabbydev.securitytest.repo.ChatEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class ChatService {
    private final TokenChecker tokenChecker;

    private final ChatEntityRepository chatEntityRepository;

    private final ChatEntityMapper chatEntityMapper;

    public ChatService(TokenChecker tokenChecker, ChatEntityRepository chatEntityRepository, ChatEntityMapper chatEntityMapper) {
        this.tokenChecker = tokenChecker;
        this.chatEntityRepository = chatEntityRepository;
        this.chatEntityMapper = chatEntityMapper;
    }

    public ResponseEntity<String> send(ChatRequestDTO chatRequestDTO) {
        if(tokenChecker.getUserByToken(chatRequestDTO.getAccessToken()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setAuthor(tokenChecker.getUserByToken(chatRequestDTO.getAccessToken()));
        chatEntity.setSendTime(Instant.now());
        chatEntity.setMessage(chatRequestDTO.getMessage());

        chatEntityRepository.save(chatEntity);

        return ResponseEntity.ok("Сообщение успешно отправлено");
    }

    public ResponseEntity<String> remove(ChatDTO chatDTO) {
        if(tokenChecker.getUserByToken(chatDTO.getAccessToken()) != null ||
                tokenChecker.getRoleByToken(chatDTO.getAccessToken()) < 1 ||
                    tokenChecker.getUserByToken(chatDTO.getAccessToken()).getId().equals(chatDTO.getId()))

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");

        chatEntityRepository.delete(chatEntityMapper.toEntity(chatDTO));

        return ResponseEntity.ok("Сообщение успешно удалено");
    }

    public Page<ChatDTO> getAll(String page) {
        return chatEntityRepository.findAll(PageRequest.of(Integer.valueOf(page), 30)).map(chatEntityMapper::toDTO);
    }
}
