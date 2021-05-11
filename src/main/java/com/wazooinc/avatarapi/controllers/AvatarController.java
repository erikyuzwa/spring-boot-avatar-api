package com.wazooinc.avatarapi.controllers;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.wazooinc.avatarapi.models.Avatar;
import com.wazooinc.avatarapi.models.User;
import com.wazooinc.avatarapi.repositories.AvatarRepository;
import com.wazooinc.avatarapi.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    private UserRepository userRepository;

    // GET /api/users/:userId/avatars
    @GetMapping("/users/{userId}/avatars")
    @ResponseStatus(HttpStatus.OK)
    public List<Avatar> getAll(@PathVariable("userId") Long userId) {
        log.debug("REST request to get all Avatars with userId - {}", userId);
        return avatarRepository.findByUserId(userId);
    }

    // POST /api/users/:userId/avatars
    @PostMapping("/users/{userId}/avatars")
    @ResponseStatus(HttpStatus.CREATED)
    public Avatar create(@PathVariable("userId") Long userId, @RequestBody Avatar newAvatar) {
        log.debug("REST request to create an Avatar");
        Avatar model = new Avatar();
        Optional<User> user = userRepository.findById(userId);
        model.setUser(user.get());
        model.setName(newAvatar.getName());
        model.setClassType(newAvatar.getClassType());
        model.setRaceType(newAvatar.getRaceType());
        log.debug("{}", model);
        return avatarRepository.save(model);
    }

    // GET /api/users/:userId/avatars/:id
    @GetMapping("/users/{userId}/avatars/{id}")
    public ResponseEntity<?> getOne(@PathVariable("userId") Long userId, @PathVariable("id") Long id) {
        log.debug("REST request to get Avatar with id - {}", id);
        Optional<Avatar> model = null;
        model = avatarRepository.findByIdAndUserId(id, userId);
        if (model.isPresent()) {
            return new ResponseEntity<Avatar>(model.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
    }

    // PUT /api/users/:userId/avatars/:id
    @PutMapping("/users/{userId}/avatars/{id}")
    public ResponseEntity<?> updateOne(@PathVariable("userId") Long userId, @PathVariable("id") Long id, @RequestBody Avatar newAvatar){
        log.debug("REST request to update Avatar with id - {}", id);
        Optional<Avatar> model = null;
        model = avatarRepository.findByIdAndUserId(id, userId);
        if (model.isPresent()) {
            model.get().setName(newAvatar.getName());
            model.get().setClassType(newAvatar.getClassType());
            model.get().setRaceType(newAvatar.getRaceType());
            model.get().setDateModified(Instant.now());

            return new ResponseEntity<Avatar>(avatarRepository.save(model.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
    }

    // DELETE /api/users/:userId/avatars/:id
    @DeleteMapping("/users/{userId}/avatars/{id}")
    public void delete(@PathVariable("userId") Long userId, @PathVariable("id") Long id) {
        Optional<Avatar> model = avatarRepository.findByIdAndUserId(id, userId);
        avatarRepository.delete(model.get());
    }
    
}
