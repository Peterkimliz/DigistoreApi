package com.example.digishopapi.services;

import com.example.digishopapi.dtos.ShopDto;
import com.example.digishopapi.dtos.UpdateShopDto;
import com.example.digishopapi.exceptions.FoundException;
import com.example.digishopapi.exceptions.NotFoundException;
import com.example.digishopapi.models.Shop;
import com.example.digishopapi.models.User;
import com.example.digishopapi.repository.ShopRepository;
import com.example.digishopapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;

    public Shop createShop(String userId, ShopDto shopDto) {
        Optional<Shop> shopName = shopRepository.findByName(shopDto.getName());
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("user with id not found");
        }
        if (shopName.isPresent()) {
            throw new FoundException("shop with the provided name already exists");
        } else {
            Shop shopModel = new Shop();
            shopModel.setName(shopDto.getName());
            shopModel.setDescription(shopDto.getDescription());
            shopModel.setLatitude(Double.parseDouble(shopDto.getLatitude()));
            shopModel.setLongitude(Double.parseDouble(shopDto.getLongitude()));
            shopModel.setCreatedAt(new Date(System.currentTimeMillis()));
            shopModel.setUserId(userId);
            shopModel.setUpdatedAt(new Date(System.currentTimeMillis()));
            shopModel.setPhone(shopDto.getPhone());
            return shopRepository.save(shopModel);
        }


    }

    public Shop getShopById(String id) {
        Optional<Shop> shopName = shopRepository.findById(id);
        if (shopName.isEmpty()) {
            throw new NotFoundException("shop with the provided id doesn't exist");
        } else {
            if (shopName.get().getUpgraded()) {
                checkShopSubscription(id);
            }
            return shopName.get();
        }

    }
    public void deleteShopById(String id) {
        Optional<Shop> shopName = shopRepository.findById(id);
        if (shopName.isEmpty()) {
            throw new NotFoundException("shop with the provided id doesn't exist");
        } else {
            shopRepository.deleteById(id);
        }

    }

    public Shop updateShopById(String id, UpdateShopDto updateShopDto) {
        Optional<Shop> shopName = shopRepository.findById(id);

        if (shopName.isEmpty()) {
            throw new NotFoundException("shop with the provided id doesn't exist");
        } else {
            Shop shopModel = shopName.get();
            shopModel.setName(updateShopDto.getName() == null ? shopModel.getName() : updateShopDto.getName());
            shopModel.setPhone(updateShopDto.getPhone() == null ? shopModel.getPhone() : updateShopDto.getPhone());
            shopModel.setDescription(updateShopDto.getDescription() == null ? shopModel.getDescription() : updateShopDto.getDescription());
            shopModel.setLongitude(updateShopDto.getLongitude() == null ? shopModel.getLongitude() : Double.parseDouble(updateShopDto.getLongitude()));
            shopModel.setLatitude(updateShopDto.getLatitude() == null ? shopModel.getLatitude() : Double.parseDouble(updateShopDto.getLatitude()));
            return shopRepository.save(shopModel);
        }

    }

    public Shop updateShopSubscription(String id) {
        Optional<Shop> shopName = shopRepository.findById(id);

        if (shopName.isEmpty()) {
            throw new NotFoundException("shop with the provided id doesn't exist");
        } else {
            Shop shopModel = shopName.get();
            shopModel.setUpdatedAt(new Date(System.currentTimeMillis()));
            shopModel.setSubscriptionStartDate(new Date(System.currentTimeMillis()));
            shopModel.setSubscriptionEndDate(new Date(System.currentTimeMillis()));
            return shopRepository.save(shopModel);
        }

    }

    public void checkShopSubscription(String id) {
        Optional<Shop> shopName = shopRepository.findById(id);

        if (shopName.isEmpty()) {
            throw new NotFoundException("shop with the provided id doesn't exist");
        } else {
            Shop shopModel = shopName.get();
            Date date = new Date(System.currentTimeMillis());
            Date date1 = shopModel.getSubscriptionEndDate();
            if (date.equals(date1) || date.after(date1)) {
                shopModel.setUpgraded(false);
                shopModel.setUpdatedAt(new Date(System.currentTimeMillis()));
                shopRepository.save(shopModel);
            }

        }

    }

    public List<Shop> getAllShops(String pageNumber) {
        if (pageNumber == null) {
            return getShops();
        } else {
            return getPageableShops(Integer.parseInt(pageNumber));
        }
    }

    public List<Shop> getShops() {
        List<Shop> shopModels = shopRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (shopModels.size() == 0) {
            return new ArrayList<>();
        } else {
            return shopModels;
        }
    }

    public List<Shop> getPageableShops(int pageSize) {
        Page<Shop> shops = shopRepository.findAll(PageRequest.of(pageSize, 20).withSort(Sort.by(Sort.Direction.DESC, "createdAt")));
        return shops.toList();
    }

}
