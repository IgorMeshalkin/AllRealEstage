package com.igormeshalkin.entity;

import javax.persistence.*;

@Entity
@Table(name = "houses")
public class House extends RealEstate{

    @Column(name = "land_area")
    private double landArea;

    @Column(name = "number_of_floors")
    private int numberOfFloors;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getLandArea() {
        return landArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
}
