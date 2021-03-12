package com.idea.absorbent.task001.credits.persistence.repositories;

import com.idea.absorbent.task001.credits.persistence.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CreditsRepository extends JpaRepository<Credit, Integer> {

    @Query(value = "SELECT nextval('credits_id_seq')", nativeQuery = true)
    Integer getNextValMySequence();

    @Override
    List<Credit> findAll();
}

