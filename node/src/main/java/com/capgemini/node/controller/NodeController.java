package com.capgemini.node.controller;

import com.capgemini.node.model.Node;
import com.capgemini.node.repository.NodeRepository;
import com.capgemini.node.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/node")
public class NodeController {

    private NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService){
        this.nodeService = nodeService;
    }


    @GetMapping("/get-node")
    public ResponseEntity<List<Node>> getAllNodes(){
        return ResponseEntity.ok().body(nodeService.getAllNodes());
    }
    @GetMapping("/get-node-by-id/{id}")
    public Node getNodeById(@PathVariable Integer id){
        return nodeService.getNodeById(id);
    }
    @GetMapping("/get-node-by-name/{name}")
    public Node getNodeByName(@PathVariable String name){
        return nodeService.getNodeByName(name);
    }
    @PostMapping("/add-node")
    public Node addNode(@RequestBody Node node){
        return nodeService.addNode(node);
    }
    @PutMapping("/update-node")
    public Node updateNode(@RequestParam Integer id,
                           @RequestBody Node node){
        return nodeService.updateNode(id, node);
    }
    @DeleteMapping("/delete-node")
    public void deleteNode(@RequestParam Integer id){
        nodeService.deleteNode(id);
    }
}
