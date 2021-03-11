package com.idea.absorbent.task001.credits.persistence.repositories;

import com.idea.absorbent.task001.credits.persistence.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditsRepository extends JpaRepository<Credit, Integer> {

    @Query(value = "SELECT nextval('credits_ID_seq')", nativeQuery = true)
    Integer getNextValMySequence();

}
