package com.igormeshalkin.entity;

import com.igormeshalkin.util.DateTimeFormatUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Locale;

@MappedSuperclass
public class RealEstate extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "area")
    private Double area;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @Column(name = "price")
    private Integer price;

    public RealEstate() {
    }

    public RealEstate(Long id, LocalDateTime created, LocalDateTime updated, String street, String houseNumber, Double area, Integer numberOfRooms, Integer price) {
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RealEstate{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", price=" + price +
                '}';
    }

    public String getAddressFormat() {
        String houseNumber = getHouseNumber().replace(" ", "");
        String houseLiter = "";
        if(houseNumber.matches(".*[a-zA-Zа-яА-Я]+.*")) {
            houseLiter = houseNumber.substring(houseNumber.length() - 1).toLowerCase(Locale.ROOT);
            houseNumber = houseNumber.substring(0, houseNumber.length() - 1);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ул."
                + getStreet().trim()
                + " д."
                + houseNumber
                + houseLiter);

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

    public boolean searchAddress(String address) {
        String[] addressArray = address.toLowerCase(Locale.ROOT).split(" ");

        if(this.getStreet().toLowerCase(Locale.ROOT).contains(addressArray[0])) {
            if(addressArray.length == 1) {
                return true;
            } else if (this.getHouseNumber().toLowerCase(Locale.ROOT).contains(addressArray[1])) {
                return true;
            }
        }
        return false;
    }
}
