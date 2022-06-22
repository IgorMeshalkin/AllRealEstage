package com.igormeshalkin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "apartments")
public class Apartment extends RealEstate{

    @Column(name = "balcony_availability")
    private boolean balconyAvailability;

    @Column(name = "floor")
    private int floor;

    @Column(name = "total_floors")
    private int totalFloors;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBalconyAvailability() {
        return balconyAvailability;
    }

    public void setBalconyAvailability(boolean balconyAvailability) {
        this.balconyAvailability = balconyAvailability;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public String getCreatedFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getCreated().format(dateTimeFormatter);
    }
}
