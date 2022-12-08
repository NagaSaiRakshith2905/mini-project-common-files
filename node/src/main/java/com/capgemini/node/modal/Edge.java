package com.capgemini.node.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge {
    @Id
    @SequenceGenerator(name = "edge_sequence",sequenceName = "edge_sequence")
    @GeneratedValue(strategy = SEQUENCE,generator = "edge_sequence")
    private Integer id;
    private Character edgeName;
    private Boolean isAvailable;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;
}
