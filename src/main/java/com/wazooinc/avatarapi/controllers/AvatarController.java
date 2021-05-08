package com.wazooinc.avatarapi.controllers;

import java.util.List;
import java.util.Optional;

import com.wazooinc.avatarapi.models.Avatar;
import com.wazooinc.avatarapi.repositories.AvatarRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        log.debug("REST request to get all Avatars");
        return avatarRepository.findAll();
    }

    // POST /api/avatars
    @PostMapping("/avatars")
    @ResponseStatus(HttpStatus.CREATED)
    public Avatar create(@RequestBody Avatar newAvatar) {
        log.debug("REST request to create an Avatar");
        Avatar model = new Avatar();
        model.setName(newAvatar.getName());
        model.setType(newAvatar.getType());
        log.debug("{}", model);
        return avatarRepository.save(model);
    }

    // GET /api/avatars/:id
    @GetMapping("/avatars/{id}")
    public Avatar getOne(@PathVariable("id") Long id) {
        Optional<Avatar> model = null;
        model = avatarRepository.findById(id);
        return model.get();
    }
    
}
