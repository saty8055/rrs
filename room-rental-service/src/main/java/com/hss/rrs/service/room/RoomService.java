package com.hss.rrs.service.room;

import com.hss.rrs.config.exception.NoSuchPropertyException;
import com.hss.rrs.entity.bean.RoomDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface RoomService {
    List<RoomDto> findAvailable(Date from, Date to) throws NoSuchPropertyException;

    Page<RoomDto> findAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortField);
}
