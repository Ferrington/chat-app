package com.chatapp.Chat.App.repository;

import com.chatapp.Chat.App.model.auth.ERole;
import com.chatapp.Chat.App.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
