package com.igormeshalkin.entity;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Like{" +
                "user=" + user.getUsername() +
                ", apartment=" + apartment.getId() +
                '}';
    }
}
