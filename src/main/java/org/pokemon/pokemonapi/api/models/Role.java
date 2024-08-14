package org.pokemon.pokemonapi.api.models;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

}
