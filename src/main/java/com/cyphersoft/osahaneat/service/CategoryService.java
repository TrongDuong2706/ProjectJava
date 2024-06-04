package com.cyphersoft.osahaneat.service;

import com.cyphersoft.osahaneat.dto.CategoryDTO;
import com.cyphersoft.osahaneat.dto.MenuDTO;
import com.cyphersoft.osahaneat.entity.Category;
import com.cyphersoft.osahaneat.entity.Food;
import com.cyphersoft.osahaneat.repository.CategoryRepository;
import com.cyphersoft.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getCategoryHomePage() {

        PageRequest pageRequest =  PageRequest.of(0, 3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> listCategoryDTOS = new ArrayList<>();

        for(Category data: listCategory){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(data.getNameCate());
            List<MenuDTO> menuDTOS = new ArrayList<>();

            //Do khác kiểu dữ liệu nên phải làm v - đoạn này là đang gán giá trị cho List Menus
            for(Food dataFood: data.getListFood()){
                MenuDTO menuDTO = new MenuDTO();
                menuDTO.setTitle(dataFood.getTitle());
                menuDTO.setFreeShip(dataFood.isFreeship());
                menuDTO.setImage(dataFood.getImage());
                menuDTOS.add(menuDTO);
            }
            categoryDTO.setMenus(menuDTOS);
            listCategoryDTOS.add(categoryDTO);
        }

        return listCategoryDTOS;
    }
}
