package com.wazooinc.avatarapi.repositories;

import com.wazooinc.avatarapi.models.Avatar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    
}
