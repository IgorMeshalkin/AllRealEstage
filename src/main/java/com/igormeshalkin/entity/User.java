package com.igormeshalkin.entity;

import com.igormeshalkin.util.DateTimeFormatUtil;
import com.igormeshalkin.util.UserRatingUtil;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Apartment> apartments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Like> likes;

    public User() {
    }

    public User(String username, String password, String confirmPassword, String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getCreatedFormat() {
        return getCreated().format(DateTimeFormatUtil.dateAndTimeFormatter());
    }

    public String getUpdatedFormat() {
        return getUpdated().format(DateTimeFormatUtil.dateAndTimeFormatter());
    }

    public String getRating() {
        double rating = UserRatingUtil.calculateRating(this);
        if (rating > 0) {
            return String.format("%.2f", rating);
        }
        return "0";
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", created='" + getCreated() + '\'' +
                ", updated='" + getUpdated() + '\'' +
                ", role=" + role +
                ", active=" + active +
                '}';
    }

    public boolean searchByLine(String searchLine) {
        if (getFirstName().toLowerCase(Locale.ROOT).contains(searchLine.toLowerCase(Locale.ROOT)) ||
                getLastName().toLowerCase(Locale.ROOT).contains(searchLine.toLowerCase(Locale.ROOT))) {
            return true;
        }
        String phoneNumberFromSearchLine = searchLine.replaceAll("[^0-9]", "");
        if (phoneNumberFromSearchLine.length() == 0) {
            return false;
        }
        if (phoneNumberFromSearchLine.length() > 10) {
            phoneNumberFromSearchLine = phoneNumberFromSearchLine.substring(1);
        }
        if (getPhoneNumber().replaceAll("[^0-9]", "").contains(phoneNumberFromSearchLine)) {
            return true;
        }
        return false;
    }
}