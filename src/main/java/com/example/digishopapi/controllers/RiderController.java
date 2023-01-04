package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.RiderDto;
import com.example.digishopapi.models.Rider;
import com.example.digishopapi.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Rider>> getRidersByShopId(@PathVariable("shopId") String shopId) {
        List<Rider> riders=  riderService.getRidersByShopId(shopId);
        return new ResponseEntity<>(riders,riders.size()==0?HttpStatus.NOT_FOUND: HttpStatus.OK);
    } @GetMapping("/all")
    public ResponseEntity<List<Rider>> getAllRiders(@RequestParam(required = false) String pageNumber) {
        List<Rider> riders= riderService.getAllRiders(pageNumber);
        return new ResponseEntity<>(riders,riders.size()==0?HttpStatus.NOT_FOUND: HttpStatus.OK);
    }
    @PutMapping("/{riderId}")
    public ResponseEntity<Rider> updateRiderById(@PathVariable("riderId") String riderId,@RequestBody Rider rider) {
        return new ResponseEntity<>(riderService.updateRiderById(riderId,rider), HttpStatus.OK);
    }
    @DeleteMapping("/{riderId}")
    public ResponseEntity<?> deleteRiderById(@PathVariable("riderId") String riderId) {
        riderService.deleteRiderById(riderId);
        return new ResponseEntity<>("rider deleted successfull",HttpStatus.OK);
    }


}
