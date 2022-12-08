package com.capgemini.node.repository;

import com.capgemini.node.modal.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node,Integer> {
}
