package com.hss.rrs.entity.bean;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hss.rrs.entity.Booking;
import com.hss.rrs.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

	public UserDto() {}

	public UserDto(User user) {
		BeanUtils.copyProperties(user, this);
	}

	public UserDto(User user, List<Booking> bookings) {
		BeanUtils.copyProperties(user, this);
		this.bookings = new ArrayList<>();
		bookings.forEach(booking -> {
			BookingDto dto = new BookingDto(booking);
			this.bookings.add(dto);
		});
	}

	private String username, fullName, email;

	private String password, oldPassword, deviceId, fcmToken;

	private Integer status;

	public List<BookingDto> bookings;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public List<BookingDto> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDto> bookings) {
		this.bookings = bookings;
	}
}