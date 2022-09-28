package com.example.vaadin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowDto {
    private int showId;

    private String name;

    private int AgeRestriction;

    private String City;

    public ShowDto(int showId, String name, int ageRestriction, String city) {
        this.showId = showId;
        this.name = name;
        AgeRestriction = ageRestriction;
        City = city;
    }

    public ShowDto() {
    }

    public String getShowIdText() {
        return String.valueOf(showId);
    }

    public void setShowIdText(String showId) {
        this.showId = Integer.parseInt(showId);
    }



    public String getAgeRestrictionText() {
        return String.valueOf(AgeRestriction);
    }

    public void setAgeRestrictionText(String ageRestriction) {
        AgeRestriction = Integer.parseInt(ageRestriction);
    }
}
