package com.cyphersoft.osahaneat.service;

import com.cyphersoft.osahaneat.dto.CategoryDTO;
import com.cyphersoft.osahaneat.dto.MenuDTO;
import com.cyphersoft.osahaneat.dto.RestaurantDTO;
import com.cyphersoft.osahaneat.entity.Food;
import com.cyphersoft.osahaneat.entity.MenuRestaurant;
import com.cyphersoft.osahaneat.entity.RatingRestaurant;
import com.cyphersoft.osahaneat.entity.Restaurant;
import com.cyphersoft.osahaneat.repository.RestaurantRepository;
import com.cyphersoft.osahaneat.service.imp.FileServiceImp;
import com.cyphersoft.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;
    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean is_freeship, String address, String open_date) {
        boolean isInsertSucess = false;
        try{
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubTitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(is_freeship);
                restaurant.setAddress(address);
                //Biến String thành Date
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm");
                Date openDate = simpleDateFormat.parse(open_date);
                restaurant.setOpenDate(openDate);
                restaurantRepository.save(restaurant);
                isInsertSucess = true;
            }
        }
        catch(Exception e){
            System.out.println("Error insert restaurant "+e.getMessage());
        }

        return isInsertSucess;
    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
        for(Restaurant data : listData){
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubTitle());
            restaurantDTO.setFreeShip(data.getFreeship());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant =  restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if(restaurant.isPresent()){
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            Restaurant data = restaurant.get();
            restaurantDTO.setDescription(data.getDescription());
            restaurantDTO.setId(data.getId());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubTitle());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
            restaurantDTO.setFreeShip(data.getFreeship());
            restaurantDTO.setOpenDate(data.getOpenDate());
            restaurantDTO.setImage(data.getImage());

            //category
            for(MenuRestaurant menuRestaurant:data.getListMenuRestaurant()){
                List<MenuDTO> menuDTOList = new ArrayList<>();
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(menuRestaurant.getCategory().getNameCate());

                //menu
                for(Food food: menuRestaurant.getCategory().getListFood()){
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setId(food.getId());
                    menuDTO.setImage(food.getImage());
                    menuDTO.setFreeShip(food.isFreeship());
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setDescription(food.getDescription());
                    menuDTO.setPrice(food.getPrice());
                    menuDTOList.add(menuDTO);
                }

                categoryDTO.setMenus(menuDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setCategory(categoryDTOList);
        }
        return restaurantDTO;
    }

    private double calculatorRating(Set<RatingRestaurant> listRating){
        double totalPoint = 0;
        for(RatingRestaurant data :listRating){
            totalPoint += data.getRatePoint();
        }
        return totalPoint / listRating.size();

    }
}
