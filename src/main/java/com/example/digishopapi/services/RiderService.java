package com.example.digishopapi.services;

import com.example.digishopapi.dtos.RiderDto;
import com.example.digishopapi.exceptions.FoundException;
import com.example.digishopapi.exceptions.NotFoundException;
import com.example.digishopapi.models.Rider;
import com.example.digishopapi.models.Shop;
import com.example.digishopapi.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RiderService {

    @Autowired
    RiderRepository riderRepository;
    @Autowired
    ShopService shopService;

    public Rider createRider(String shopId, RiderDto riderDto) {
        Shop shop = shopService.getShopById(shopId);

        if (shop == null) {
            throw new NotFoundException("shop with id not found");
        } else if (getRiderByEmail(riderDto.getEmail())) {
            throw new FoundException("rider with email address already exists");
        } else {
            Rider rider = new Rider();
            rider.setCreatedAt(new Date(System.currentTimeMillis()));
            rider.setUpdatedAt(new Date(System.currentTimeMillis()));
            rider.setName(riderDto.getName());
            rider.setPhone(riderDto.getPhone());
            rider.setEmail(riderDto.getEmail());
            rider.setShopId(shopId);
            return riderRepository.save(rider);
        }
    }

    public Rider getRiderById(String riderId) {
        Optional<Rider> rider = riderRepository.findById(riderId);
        if (rider.isEmpty()) {
            throw new NotFoundException("rider with id not found");
        } else {
            return rider.get();
        }

    }

    public Rider updateRiderById(String riderId, Rider rider) {
        Optional<Rider> foundRider = riderRepository.findById(riderId);
        if (foundRider.isEmpty()) {
            throw new NotFoundException("rider with id not found");
        } else {
            if (rider.getEmail() != null && getRiderByEmail(rider.getEmail())) {
                throw new FoundException("rider with the email address already exists ");
            }
            Rider savedRider = foundRider.get();
            savedRider.setUpdatedAt(new Date(System.currentTimeMillis()));
            savedRider.setName(rider.getName() == null ? savedRider.getName() : rider.getName());
            savedRider.setEmail(rider.getEmail() == null ? savedRider.getEmail() : rider.getEmail());
            savedRider.setPhone(rider.getPhone() == null ? savedRider.getPhone() : rider.getPhone());

            return riderRepository.save(savedRider);
        }

    }

    public void deleteRiderById(String riderId) {
        Optional<Rider> rider = riderRepository.findById(riderId);
        if (rider.isEmpty()) {
            throw new NotFoundException("rider with id not found");
        } else {
            riderRepository.deleteById(riderId);
        }

    }

    private Boolean getRiderByEmail(String email) {
        Optional<Rider> foundRider = riderRepository.findByEmail(email);
        return foundRider.isPresent();
    }

    public List<Rider> getAllRiders(String pageNumber) {
        if (pageNumber == null) {
            return getRiders();
        } else {
            return getPaginatedRiders(Integer.parseInt(pageNumber));
        }

    }

    private List<Rider> getRiders() {
        List<Rider> riders = riderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (riders.size() == 0) {
            return new ArrayList<>();
        } else {
            return riders;
        }
    } 
    public List<Rider> getRidersByShopId(String shopId) {
        Pageable page=PageRequest.of(0,20).withSort(Sort.by(Sort.Direction.DESC,"createdsAt"));
        List<Rider> riders = riderRepository.findByShopId(shopId,page);
        if (riders.size() == 0) {
            return new ArrayList<>();
        } else {
            return riders;
        }
    }

    private List<Rider> getPaginatedRiders(int pageNumber) {
        List<Rider> riders = riderRepository.findAll(PageRequest.of(pageNumber, 20).withSort(Sort.by(Sort.Direction.DESC, "createdAt"))).toList();
        if (riders.size() == 0) {
            return new ArrayList<>();
        } else {
            return riders;
        }
    }


}
