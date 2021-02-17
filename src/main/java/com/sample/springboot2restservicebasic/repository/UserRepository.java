package com.sample.springboot2restservicebasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.springboot2restservicebasic.model.User;

@Repository
public interface UserRepository extends JpaRepository <User , Long> {
}
