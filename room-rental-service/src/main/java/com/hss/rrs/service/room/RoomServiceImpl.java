package com.hss.rrs.service.room;

import com.google.common.collect.Lists;
import com.hss.rrs.config.exception.NoSuchPropertyException;
import com.hss.rrs.entity.Booking;
import com.hss.rrs.entity.Room;
import com.hss.rrs.entity.bean.RoomDto;
import com.hss.rrs.repo.BookingRepo;
import com.hss.rrs.repo.RoomRepo;
import com.hss.rrs.service.common.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements  RoomService{

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UtilService utilService;

    @PostConstruct
    public void init(){
        if(roomRepo.findAll().isEmpty()){
            this.defaultRooms().forEach(room -> {
                roomRepo.save(room);
            });
        }
    }


    @Override
    public List<RoomDto> findAvailable(Date from, Date to) throws NoSuchPropertyException {

        List<Room> roomsBooked = bookingRepo.findAll().stream().filter(booking -> (booking.getCancelled() || this.isRoomAvailable(from, to, booking.getBookingFrom(), booking.getBookingTo())))
                .collect(Collectors.toList()).stream().map(Booking :: getRoom).collect(Collectors.toList());
        List<Room> rooms = roomRepo.findAll().stream().filter(room -> !roomsBooked.contains(room)).collect(Collectors.toList());
        List<RoomDto> roomDtoList = new ArrayList<>();
        rooms.forEach(room->{
            roomDtoList.add(new RoomDto(room));
        });
        return roomDtoList;
    }

    private boolean isRoomAvailable(Date desiredFrom, Date desiredTo, Date bookedFrom, Date bookedTo){
        boolean flag = false;
        if(utilService.before(bookedFrom, desiredFrom)){
            if(utilService.before(bookedTo, desiredFrom))
                flag = true;
            else flag = false;
        }
        else{
            if(utilService.before(bookedFrom, desiredTo))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    @Override
    public Page<RoomDto> findAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortField) {

        String sortOptions = "roomId, houseName, houseClass, suiteName, isAc, isHotel, createdAt, updatedAt, roomNumber, price";

        if (!sortOptions.contains(sortField))
            sortField = "createdAt";

        Pageable pageable = PageRequest.of(pageNumber, pageSize, utilService.getSort(sortField, sortDirection));

        Page<Room> records = roomRepo.findAll(pageable);

        List<RoomDto> content = new ArrayList<>();
        records.getContent().forEach(room -> {
            RoomDto roomDto = new RoomDto(room, room.getBookings());
            content.add(roomDto);
        });

        return utilService.getPageDTO(records, content);
    }

    private List<Room> defaultRooms(){
        return Lists.newArrayList(
                new Room(101, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(102, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(103, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(104, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(105, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(106, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(107, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(108, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(109, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(110, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(111, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(112, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(113, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(114, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(115, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(116, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(117, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(118, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(119, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(120, "Holiday Inn", "3 Star", "General", true, true, 2000),
                new Room(121,"Holiday Inn", "3 Star", "General", true, true, 2000)
        );
    }

}
