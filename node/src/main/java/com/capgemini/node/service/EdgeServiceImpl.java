package com.capgemini.node.service;


import com.capgemini.node.exception.EdgeException;
import com.capgemini.node.model.Edge;
import com.capgemini.node.repository.EdgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EdgeServiceImpl implements EdgeService {

    private EdgeRepository edgeRepository;

    @Autowired
    public EdgeServiceImpl(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    @Override
    public Edge addNode(Edge edge) {
        edgeRepository.save(edge);
        return edge;
    }

    @Override
    public List<Edge> getAllNodes() {
        return edgeRepository.findAll();
    }

    @Override
    public Edge getNodeById(Integer id) {
        Optional<Edge> edge = edgeRepository.findById(id);
        if (edge.isEmpty())
            throw new EdgeException("Invalid Edge ID");
        return edge.get();
    }

    @Override
    public Edge getNodeByName(Character name) {
        Optional<Edge> edge = edgeRepository.findByName(name);
        if(edge.isEmpty())
            throw new EdgeException("Invalid Edge Name");
        return edge.get();
    }

    @Override
    public void deleteNode(Integer id) {
        edgeRepository.deleteById(id);
    }

    @Override
    public Edge updateNode(Integer id, Edge edge) {
        Optional<Edge> edge1= edgeRepository.findById(id);

        if(edge1.isEmpty())
            throw new EdgeException("Invalid Edge ID");
        edge1.get().setName(edge.getName());
        edge1.get().setIsAvailable(edge.getIsAvailable());

        return edge1.get();
    }
}
