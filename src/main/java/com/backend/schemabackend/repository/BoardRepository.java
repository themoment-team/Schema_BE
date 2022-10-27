package com.backend.schemabackend.repository;

import com.backend.schemabackend.entity.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Member, Long> {
    Member findByUseridAndPassword(String userid, String password);
    boolean existsByUserid(String userid);
    boolean existsByPassword(String password);

    Optional<Member> findByUserid(String userid);


}
