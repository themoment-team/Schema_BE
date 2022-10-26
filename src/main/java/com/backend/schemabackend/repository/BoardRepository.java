package com.backend.schemabackend.repository;

import com.backend.schemabackend.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;

public interface BoardRepository extends CrudRepository<Member, Long> {
}
