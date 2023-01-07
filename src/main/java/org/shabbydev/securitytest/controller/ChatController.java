package org.shabbydev.securitytest.controller;


import org.shabbydev.securitytest.jwt.TokenChecker;
import org.shabbydev.securitytest.mapper.dto.ChatDTO;
import org.shabbydev.securitytest.mapper.dto.ChatRequestDTO;
import org.shabbydev.securitytest.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/chat/")
@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("send")
    public ResponseEntity<String> send(@ModelAttribute ChatRequestDTO chatRequestDTO) {
        return chatService.send(chatRequestDTO);
    }

    @PostMapping("remove")
    public ResponseEntity<String> remove(@ModelAttribute ChatDTO chatDTO) {
        return chatService.remove(chatDTO);
    }

    @GetMapping("all")
    public Page<ChatDTO> getAll(@RequestParam String page) {
        return chatService.getAll(page);
    }
}
