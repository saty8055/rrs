package com.hss.rrs.service.booking;

import com.hss.rrs.config.exception.DataValidityException;
import com.hss.rrs.config.exception.NoSuchPropertyException;
import com.hss.rrs.entity.bean.BookingDto;
import org.springframework.data.domain.Page;

public interface BookingService {
    Page<BookingDto> findAll(Integer pageNumber, Integer pageSize, String sortDirection, String sortField);

    Page<BookingDto> findMy(Integer pageNumber, Integer pageSize, String sortDirection, String sortField);

    BookingDto add(BookingDto bookingDto) throws DataValidityException;

    BookingDto cancel(String bookingId) throws DataValidityException, NoSuchPropertyException;
}
