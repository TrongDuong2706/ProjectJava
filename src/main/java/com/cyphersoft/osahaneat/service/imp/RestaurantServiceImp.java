package com.cyphersoft.osahaneat.service.imp;

import com.cyphersoft.osahaneat.dto.RestaurantDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    boolean insertRestaurant( MultipartFile file,
                              String title,
                              String subtitle,
                              String description,
                              boolean is_freeship,
                              String address,
                              String open_date);
    List<RestaurantDTO> getHomePageRestaurant();
    RestaurantDTO getDetailRestaurant(int id);

}
