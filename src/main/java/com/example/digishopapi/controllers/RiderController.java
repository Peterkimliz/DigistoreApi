package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.RiderDto;
import com.example.digishopapi.models.Rider;
import com.example.digishopapi.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/riders")
public class RiderController {
    @Autowired
    RiderService riderService;

    @PostMapping("/create/{shopId}")
    public ResponseEntity<Rider> createRider(@PathVariable("shopId") String shopId, @RequestBody @Validated  RiderDto riderDto) {
        return new ResponseEntity<>(riderService.createRider(shopId, riderDto), HttpStatus.CREATED);
    }

    @GetMapping("/{riderId}")
    public ResponseEntity<Rider> getRiderById(@PathVariable("riderId") String riderId) {
        return new ResponseEntity<>(riderService.getRiderById(riderId), HttpStatus.OK);
    }
    @DeleteMapping("/{riderId}")
    public ResponseEntity<?> deleteRiderById(@PathVariable("riderId") String riderId) {
        riderService.deleteRiderById(riderId);
        return new ResponseEntity<>("rider deleted successfull",HttpStatus.OK);
    }

}
