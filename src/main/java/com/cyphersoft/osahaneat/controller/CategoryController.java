package com.cyphersoft.osahaneat.controller;

import com.cyphersoft.osahaneat.payload.ResponseData;
import com.cyphersoft.osahaneat.service.imp.CategoryServiceImp;
import com.cyphersoft.osahaneat.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;

    @Autowired
    FileServiceImp fileServiceImp;
    @GetMapping
    public ResponseEntity<?> getHomeCategory(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getCategoryHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }

    //Dùng để cho người dùng tải file của restaurant
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename ){
        Resource resource = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
