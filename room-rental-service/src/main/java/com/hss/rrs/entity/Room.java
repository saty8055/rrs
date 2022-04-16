package com.hss.rrs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hss.rrs.config.constants.Library;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room extends BaseEntity{

    public Room() {}

    public Room(Integer roomNumber, String houseName, String houseClass, String suiteName, Boolean isAc, Boolean isHotel, Double price){
        this.roomNumber = roomNumber;
        this.houseClass = houseClass;
        this.houseName = houseName;
        this.suiteName = suiteName;
        this.isAc = isAc;
        this.isHotel = isHotel;
        this.price = price;
    }

    public Room(Integer roomNumber, String houseName, String houseClass, String suiteName, Boolean isAc, Boolean isHotel, Integer price){
        this.roomNumber = roomNumber;
        this.houseClass = houseClass;
        this.houseName = houseName;
        this.suiteName = suiteName;
        this.isAc = isAc;
        this.isHotel = isHotel;
        this.price = (double) price;
    }

    @Id
    @GeneratedValue(generator = Library.UUID)
    @GenericGenerator(name = Library.UUID, strategy = Library.UUID_2)
    private String roomId;

    private String houseName,  houseClass, suiteName;

    private Integer roomNumber;

    private Double price;

    private Boolean isAc, isHotel;

    @OneToMany(mappedBy = Library.ROOM, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({Library.HIBERNATE_LAZY_INITIALIZER, Library.HANDLER })
    @JsonIgnore
    public List<Booking> bookings;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseClass() {
        return houseClass;
    }

    public void setHouseClass(String houseClass) {
        this.houseClass = houseClass;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAc() {
        return isAc;
    }

    public void setAc(Boolean ac) {
        isAc = ac;
    }

    public Boolean getHotel() {
        return isHotel;
    }

    public void setHotel(Boolean hotel) {
        isHotel = hotel;
    }

    public Boolean getIsAc() {
        return isAc;
    }

    public void setIsAc(Boolean isAc) {
        this.isAc = isAc;
    }

    public Boolean getIsHotel() {
        return isHotel;
    }

    public void setIsHotel(Boolean isHotel) {
        this.isHotel = isHotel;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
