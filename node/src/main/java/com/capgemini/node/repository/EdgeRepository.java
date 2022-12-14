package com.capgemini.node.repository;

import com.capgemini.node.model.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Integer> {
    Optional<Edge> findByName(Character name);

}
