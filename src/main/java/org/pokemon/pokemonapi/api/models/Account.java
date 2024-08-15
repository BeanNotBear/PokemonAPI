package org.pokemon.pokemonapi.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_role",
            joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    private Set<Role> roles;
}
