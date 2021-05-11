package com.wazooinc.avatarapi.repositories;

import com.wazooinc.avatarapi.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
