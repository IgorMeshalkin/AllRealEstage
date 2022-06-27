package com.igormeshalkin.entity;

import com.igormeshalkin.util.DateTimeFormatUtil;
import com.igormeshalkin.util.SecurityUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@MappedSuperclass
public class RealEstate extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "area")
    private double area;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @Column(name = "price")
    private int price;

    public RealEstate() {
    }

    public RealEstate(Long id, LocalDateTime created, LocalDateTime updated, String street, String houseNumber, double area, int numberOfRooms, int price) {
        super(id, created, updated);
        this.street = street;
        this.houseNumber = houseNumber;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddressFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ул."
                + getStreet().trim()
                + " д."
                + getHouseNumber().trim());

        return stringBuilder.toString();
    }

    public String getPriceFormat() {
        String price = new StringBuilder(String.valueOf(getPrice())).reverse().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("₽");

        do {
            stringBuilder.append(" " + price.substring(0, 3));
            price = price.substring(3);
        } while (price.length() > 3);

        stringBuilder.append(" " + price);

        return stringBuilder.reverse().toString();
    }

    public String getCreatedFormat() {
        return getCreated().format(DateTimeFormatUtil.onlyDateFormatter());
    }
}
