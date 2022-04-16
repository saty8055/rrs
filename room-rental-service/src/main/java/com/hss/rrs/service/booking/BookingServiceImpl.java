package com.hss.rrs.service.booking;

import com.hss.rrs.config.exception.DataValidityException;
import com.hss.rrs.config.exception.NoSuchPropertyException;
import com.hss.rrs.entity.Booking;
import com.hss.rrs.entity.Room;
import com.hss.rrs.entity.User;
import com.hss.rrs.entity.bean.BookingDto;
import com.hss.rrs.repo.BookingRepo;
import com.hss.rrs.repo.RoomRepo;
import com.hss.rrs.repo.UserRepo;
import com.hss.rrs.service.common.utils.UtilService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    BookingRepo repo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    UtilService utilService;

    @Override
    public Page<BookingDto> findAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortField) {

        String sortOptions = "bookingId, bookingFrom, bookingTo, createdAt, updatedAt";

        if (!sortOptions.contains(sortField))
            sortField = "createdAt";

        Pageable pageable = PageRequest.of(pageNumber, pageSize, utilService.getSort(sortField, sortDirection));

        Page<Booking> records = repo.findAll(pageable);

        List<BookingDto> bookings = new ArrayList<>();
        records.getContent().forEach(booking -> {
            bookings.add(new BookingDto(booking, booking.getUser(), booking.getRoom()));
        });

        return utilService.getPageDTO(records, bookings);

    }

    @Override
    public Page<BookingDto> findMy(Integer pageNumber, Integer pageSize, String sortDirection, String sortField) {

        String sortOptions = "bookingId, from, to, createdAt, updatedAt";

        if (!sortOptions.contains(sortField))
            sortField = "createdAt";

        Pageable pageable = PageRequest.of(pageNumber, pageSize, utilService.getSort(sortField, sortDirection));

        User user = userRepo.findById(utilService.user().getUserId()).get();
        Page<Booking> records = repo.findByUser(user, pageable);

        List<BookingDto> bookings = new ArrayList<>();
        records.getContent().forEach(booking -> {
            bookings.add(new BookingDto(booking, booking.getUser(), booking.getRoom()));
        });

        return utilService.getPageDTO(records, bookings);

    }

    @Override
    public BookingDto add(BookingDto bookingDto) throws DataValidityException {
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking);
        User user = utilService.user();

        if(bookingDto.getRoom() == null || bookingDto.getRoom().getRoomId() == null)
            throw new DataValidityException("Invalid room referenced!");


        Optional<Room> roomLib = roomRepo.findById(bookingDto.getRoom().getRoomId());

        if(!roomLib.isPresent())
            throw new DataValidityException("Invalid room referenced!");

        booking.setUser(userRepo.findById(user.getUserId()).get());
        booking.setRoom(roomLib.get());
        booking.setCancelled(false);

        return new BookingDto(repo.save(booking));

    }

    @Override
    public BookingDto cancel(String bookingId) throws DataValidityException, NoSuchPropertyException {
        Optional<Booking> bookingLib = repo.findById(bookingId);

        if(!bookingLib.isPresent())
            throw new NoSuchPropertyException("Invalid booking referenced!");

        Booking booking = bookingLib.get();

        booking.setUser(booking.getUser());
        booking.setRoom(booking.getRoom());
        booking.setCancelled(true);

        return new BookingDto(repo.save(booking));

    }


}
