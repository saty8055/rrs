package com.hss.rrs.entity.bean;

import com.hss.rrs.entity.BaseEntity;
import com.hss.rrs.entity.Booking;
import com.hss.rrs.entity.Room;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RoomDto extends BaseEntity {

    private String roomId;

    private String houseName,  houseClass, suiteName;

    private Boolean isAc;

    private Boolean isHotel;

    private Integer roomNumber;

    private Double price;

    public List<BookingDto> bookings;

    public RoomDto(){}

    public RoomDto(Room room){
        BeanUtils.copyProperties(room, this);
    }

    public RoomDto(Room room, List<Booking> bookings){
        BeanUtils.copyProperties(room, this);
        this.bookings = new ArrayList<>();
        bookings.forEach(booking -> {
            BookingDto dto = new BookingDto(booking);
            this.bookings.add(dto);
        });
    }

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

    public List<BookingDto> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDto> bookings) {
        this.bookings = bookings;
    }
}
