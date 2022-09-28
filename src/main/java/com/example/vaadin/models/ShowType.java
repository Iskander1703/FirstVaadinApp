package com.example.vaadin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "show_types")
@Getter
@Setter
@NoArgsConstructor
public class ShowType {

    @Id
    @Column(name = "show_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showTypeId;

    @Column(name = "name")
    private String name;

    @ManyToMany( mappedBy = "showTypes", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Show> shows=new HashSet<>();


}
