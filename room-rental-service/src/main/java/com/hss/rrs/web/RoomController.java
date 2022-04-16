package com.hss.rrs.web;

import com.hss.rrs.config.constants.Url;
import com.hss.rrs.config.exception.NoSuchPropertyException;
import com.hss.rrs.service.common.utils.UtilService;
import com.hss.rrs.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(Url.SECURE_URL+Url.ROOM)
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    UtilService utilService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam String sortBy, @RequestParam String sortDirection, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return ResponseEntity.ok(roomService.findAll(pageNumber, pageSize, sortDirection, sortBy));
    }

    @GetMapping("/query")
    public ResponseEntity<?> list(@RequestParam String from, @RequestParam String to) throws NoSuchPropertyException, ParseException {
        return ResponseEntity.ok(roomService.findAvailable(utilService.getDate(from, true), utilService.getDate(to, true)));
    }

}
