package com.igormeshalkin.util;

public class ApartmentFormBean {
    private String address;
    private Integer roomsMin;
    private Integer roomsMax;
    private Double areaMin;
    private Double areaMax;
    private Integer floorMin;
    private Integer floorMax;
    private Integer totalFloorsMin;
    private Integer totalFloorsMax;
    private Integer priceMin;
    private Integer priceMax;
    private Boolean balcony;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRoomsMin() {
        return roomsMin;
    }

    public void setRoomsMin(Integer roomsMin) {
        this.roomsMin = roomsMin;
    }

    public Integer getRoomsMax() {
        return roomsMax;
    }

    public void setRoomsMax(Integer roomsMax) {
        this.roomsMax = roomsMax;
    }

    public Double getAreaMin() {
        return areaMin;
    }

    public void setAreaMin(Double areaMin) {
        this.areaMin = areaMin;
    }

    public Double getAreaMax() {
        return areaMax;
    }

    public void setAreaMax(Double areaMax) {
        this.areaMax = areaMax;
    }

    public Integer getFloorMin() {
        return floorMin;
    }

    public void setFloorMin(Integer floorMin) {
        this.floorMin = floorMin;
    }

    public Integer getFloorMax() {
        return floorMax;
    }

    public void setFloorMax(Integer floorMax) {
        this.floorMax = floorMax;
    }

    public Integer getTotalFloorsMin() {
        return totalFloorsMin;
    }

    public void setTotalFloorsMin(Integer totalFloorsMin) {
        this.totalFloorsMin = totalFloorsMin;
    }

    public Integer getTotalFloorsMax() {
        return totalFloorsMax;
    }

    public void setTotalFloorsMax(Integer totalFloorsMax) {
        this.totalFloorsMax = totalFloorsMax;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }
}
