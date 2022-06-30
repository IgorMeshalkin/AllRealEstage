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
    private Boolean balconyAvailability;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "total_floors")
    private Integer totalFloors;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Like> likes;

    public Apartment() {
    }

    public Apartment(String street, String houseNumber,Integer numberOfRooms, Double area, Integer floor, Integer totalFloors, Boolean balconyAvailability, Integer price) {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getBalconyAvailability() {
        return balconyAvailability;
    }

    public void setBalconyAvailability(Boolean balconyAvailability) {
        this.balconyAvailability = balconyAvailability;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(Integer totalFloors) {
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
