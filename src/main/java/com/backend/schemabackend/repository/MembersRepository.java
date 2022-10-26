package com.backend.schemabackend.repository;

import com.backend.schemabackend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserid(String userid);
}