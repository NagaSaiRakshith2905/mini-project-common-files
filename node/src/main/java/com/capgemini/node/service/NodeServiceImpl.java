package com.capgemini.node.service;

import com.capgemini.node.exception.NodeException;
import com.capgemini.node.model.Node;
import com.capgemini.node.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NodeServiceImpl implements NodeService{

    private NodeRepository nodeRepository;

    @Autowired
    public NodeServiceImpl(NodeRepository nodeRepository){
        this.nodeRepository= nodeRepository;
    }

    @Override
    public Node addNode(Node node) {
        nodeRepository.save(node);
        return node;
    }

    @Override
    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    @Override
    public Node getNodeById(Integer id) throws NodeException {
        Optional<Node> node=nodeRepository.findById(id);
        if(node.isEmpty())
            throw new NodeException("Invalid Node ID");
        return node.get();
    }

    @Override
    public Node getNodeByName(String name) throws NodeException {
        Optional<Node> node=nodeRepository.findByNameIgnoreCase(name);
        if(node.isEmpty())
            throw new NodeException("Invalid Node Name");
        return node.get();
    }

    @Override
    public void deleteNode(Integer id) {
        nodeRepository.deleteById(id);
    }

    @Override
    public Node updateNode(Integer id, Node node) throws NodeException {
        Optional<Node> node1=nodeRepository.findById(id);
        if(node1.isEmpty())
            throw new NodeException("Invalid Node ID");
        node1.get().setName(node.getName());
        node1.get().setIp(node.getIp());
        node1.get().setPassword(node.getPassword());
        node1.get().setType(node.getType());
        node1.get().setXPosition(node.getXPosition());
        node1.get().setYPosition(node.getYPosition());
        return node1.get();
    }
}
