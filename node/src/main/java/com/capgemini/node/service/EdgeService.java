package com.capgemini.node.service;

import com.capgemini.node.model.Edge;

import java.util.List;

public interface EdgeService {
    public Edge addNode(Edge edge);
    public List<Edge> getAllNodes();
    public Edge getNodeById(Integer id);
    public Edge getNodeByName(Character name);
    public void deleteNode(Integer id);
    public Edge updateNode(Integer id, Edge edge);
}
