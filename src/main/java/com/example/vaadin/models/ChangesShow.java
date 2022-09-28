package com.example.vaadin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "changes_show")
@Getter
@Setter
@NoArgsConstructor
public class ChangesShow {

    @Id
    @Column(name = "changes_show_id")
    private int IdShow;

    @Column(name = "changes_time")
    private String ChangesTime;
}
