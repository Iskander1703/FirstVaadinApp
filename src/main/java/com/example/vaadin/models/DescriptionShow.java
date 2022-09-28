package com.example.vaadin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "descriptions_shows")
@Getter
@Setter
@NoArgsConstructor
public class DescriptionShow {

    @Id
    @Column(name = "description_show_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int DescriptionShowId;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "show_id", referencedColumnName = "show_id")
    @JsonIgnore
    private Show show;

    @Column(name = "text_description_show", length = 9000)
    private String TextDescriptionShow;

    @Column(name = "review_show", length = 4000)
    private String ReviewShow;

    @Column(name = "actor_show", length = 1000)
    private String ActorShow;

    @Column(name = "duration")
    private String Duration;

    @Column(name = "producer_show", length = 1000)
    private String ProducerShow;

    @Column(name = "author_show", length = 1000)
    private String AuthorShow;

    @Column(name = "start_date_time")
    private String StartDateTime;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "descriptionShow", cascade = CascadeType.ALL)
    private List<StartDateTime> startDateTimes=new ArrayList<>();

    @Column(name = "end_date_time")
    private String EndDateTime;
}
