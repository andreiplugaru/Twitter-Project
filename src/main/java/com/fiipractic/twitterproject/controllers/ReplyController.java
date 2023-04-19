package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.dtos.ReplyDto;
import com.fiipractic.twitterproject.services.ReplyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/replies")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody ReplyDto replyDto){

        replyService.add(replyDto);
    }
}
