package com.wazooinc.avatarapi.controllers;

import java.util.List;

import com.wazooinc.avatarapi.models.Avatar;
import com.wazooinc.avatarapi.repositories.AvatarRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AvatarController {
    
    private final Logger log = LoggerFactory.getLogger(AvatarController.class);

    @Autowired
    private AvatarRepository avatarRepository;

    // GET /api/avatars
    @GetMapping("/avatars")
    @ResponseStatus(HttpStatus.OK)
    public List<Avatar> getAll() {
        return avatarRepository.findAll();
    }
    
}
