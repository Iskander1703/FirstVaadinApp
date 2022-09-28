package com.example.vaadin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "start_date_time")
@Getter
@Setter
@NoArgsConstructor
public class StartDateTime {

    @Id
    @Column(name = "start_date_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StrartDateTimeId;

    @Column(name = "start_date_time")
    private String StartDateTime;

    @ManyToOne
    @JoinColumn(name="description_show_id", referencedColumnName = "description_show_id")
    @JsonIgnore
    private DescriptionShow descriptionShow;
}
