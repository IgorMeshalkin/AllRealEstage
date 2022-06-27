package com.igormeshalkin.entity;

import com.igormeshalkin.util.SecurityUtil;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "apartments")
public class Apartment extends RealEstate {

    @Column(name = "balcony_availability")
    private boolean balconyAvailability;

    @Column(name = "floor")
    private int floor;

    @Column(name = "total_floors")
    private int totalFloors;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Like> likes;

    public Apartment() {
    }

    public Apartment(String street, String houseNumber,int numberOfRooms, double area, int floor, int totalFloors, boolean balconyAvailability, int price) {
    }


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

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public boolean isLikedByCurrentUser() {
        List<Like> result = getLikes().stream()
                .filter(like -> like.getUser().getUsername().equals(SecurityUtil.getCurrentUser().getUsername()))
                .collect(Collectors.toList());

        return result.size() > 0;
    }

    public Like getLikeByCurrentUser() {
        Like result = getLikes()
                .stream()
                .filter(like -> like.getUser().getUsername().equals(SecurityUtil.getCurrentUser().getUsername()))
                .findFirst().orElse(null);

        return result;
    }

    public boolean isOwnedByCurrentUser() {
        return this.getUser().getUsername().equals(SecurityUtil.getCurrentUser().getUsername());
    }
}
