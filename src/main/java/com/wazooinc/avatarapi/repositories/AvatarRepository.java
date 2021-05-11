package com.wazooinc.avatarapi.repositories;

import java.util.List;
import java.util.Optional;

import com.wazooinc.avatarapi.models.Avatar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    List<Avatar> findByUserId(Long userId);
    Optional<Avatar> findByIdAndUserId(Long id, Long userId);
}
