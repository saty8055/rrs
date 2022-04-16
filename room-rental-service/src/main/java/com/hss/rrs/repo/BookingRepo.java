package com.hss.rrs.repo;

import com.hss.rrs.entity.Booking;
import com.hss.rrs.entity.Room;
import com.hss.rrs.entity.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, String> {

    Page<Booking> findAll(Pageable pageable);

    Page<Booking> findByUser(User user, Pageable pageable);

    List<Booking> findByRoom(Room room);



}
