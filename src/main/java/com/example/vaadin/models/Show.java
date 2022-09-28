package com.example.vaadin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Show {

    @Id
    @Column(name = "show_id")
    @XmlTransient
    private int IdShow;

    @Column(name = "age_restriction")
    @XmlElement
    private int AgeRestriction;

    @Column(name = "name")
    private String Name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "show_show_types",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "show_type_id"))
    private Set<ShowType> showTypes=new HashSet<>();

    @Column(name = "city_id")
    private int IdCity;

    @Column(name = "city")
    private String City;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "show_buildings",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id"))
    private Set<Building> buildings=new HashSet<>();

    @Column(name = "image_show")
    private String ImageShow;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "show")
    private DescriptionShow descriptionShow;

    @Column(name = "show_url")
    private String ShowUrl;

    @Column(name = "review_url")
    private String ReviewUrl;

    @Column(name = "buy_url")
    private String BuyUrl;











}
