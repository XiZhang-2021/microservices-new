package com.programming.productservice.controller;

import com.programming.productservice.dto.FavoriateRequest;
import com.programming.productservice.model.Favoriate;
import com.programming.productservice.service.FavoriateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/favoriate")
public class FavoriateController {
    @Autowired
    private FavoriateService favoriateService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFavoriate(@RequestBody FavoriateRequest favoriateRequest){
        favoriateService.addFavoriate(favoriateRequest);
    }

    @GetMapping("/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public List<Favoriate> getFavoriates(@PathVariable int userid){
        return favoriateService.getFavoriates(userid);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFrom(@PathVariable int id){
        favoriateService.deleteFrom(id);
    }


    @DeleteMapping("/delete/user/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavoriate(@PathVariable String userid){
        int uid = Integer.parseInt(userid);
        favoriateService.deleteByUserid(uid);
    }
}
