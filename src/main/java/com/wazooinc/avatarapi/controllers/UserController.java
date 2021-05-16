package com.wazooinc.avatarapi.controllers;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.wazooinc.avatarapi.models.User;
import com.wazooinc.avatarapi.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
  private final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // GET /api/users
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // POST /api/users
  @PostMapping("/users")
  public User createUser(@RequestBody User newUser) {
    User model = new User();
    model.setUsername(newUser.getUsername());
    model.setPassword(passwordEncoder.encode(newUser.getPassword()));
    model.setRole(newUser.getRole());
    return userRepository.save(model);
  }

  // GET /api/users/:id
  @GetMapping("/users/{id}")
  public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
    Optional<User> model = null;
    model = userRepository.findById(id);
    if (model.isPresent()) {
      return new ResponseEntity<User>(model.get(), HttpStatus.OK);
    }

    return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
  }

  // PUT /api/users/:id
  @PutMapping("/users/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") Long id, @RequestBody User newUser) {
    log.debug("REST request to update User with id - {}", id);
    Optional<User> model = null;
    model = userRepository.findById(id);
    if (model.isPresent()) {
      model.get().setUsername(newUser.getUsername());
      model.get().setRole(newUser.getRole());
      model.get().setDateModified(Instant.now());

      return new ResponseEntity<User>(userRepository.save(model.get()), HttpStatus.OK);
    }

    return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
  }

  // DELETE /api/users/:id
  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable("id") Long id) {
    userRepository.deleteById(id);
  }

}
