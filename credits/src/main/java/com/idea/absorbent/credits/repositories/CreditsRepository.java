package com.idea.absorbent.credits.repositories;

import com.idea.absorbent.credits.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditsRepository extends JpaRepository<Credit, Integer> {

    @Query(value = "SELECT nextval('credits_id_seq')", nativeQuery = true)
    Integer getNextValMySequence();

    @Override
    List<Credit> findAll();
}

