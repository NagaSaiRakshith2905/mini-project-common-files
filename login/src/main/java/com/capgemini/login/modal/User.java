package com.capgemini.login.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class User {
    @Id
    @SequenceGenerator(name = "node_sequence", sequenceName = "node_sequence")
    @GeneratedValue(strategy = SEQUENCE, generator = "node_sequence")
    private Integer id;
    private String userName;
    private String email;
    private String password;
}