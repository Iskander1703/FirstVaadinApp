package com.example.vaadin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@NoArgsConstructor
public class Building {

    @Id
    @Column(name = "building_id")
    private int IdBuilding;


    @ManyToMany(mappedBy = "buildings", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Show> shows=new HashSet<>();
}
