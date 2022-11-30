package com.absencemanger.absencemanger.repository;

import com.absencemanger.absencemanger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
