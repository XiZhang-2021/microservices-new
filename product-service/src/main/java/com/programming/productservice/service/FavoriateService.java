package com.programming.productservice.service;

import com.programming.productservice.dto.FavoriateRequest;
import com.programming.productservice.model.Favoriate;
import com.programming.productservice.repository.FavoriateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriateService {
    @Autowired
    private FavoriateRepository favoriateRepository;

    @Transactional
    public void addFavoriate(FavoriateRequest favoriateRequest) {
        Favoriate f = Favoriate.builder()
                .userid(favoriateRequest.getUserid())
                .username(favoriateRequest.getUsername())
                .product(favoriateRequest.getProduct())
                .productname(favoriateRequest.getProductname())
                .image(favoriateRequest.getImage())
                .author(favoriateRequest.getAuthor())
                .description(favoriateRequest.getDescription())
                .productprice(favoriateRequest.getProductprice())
                .build();
        favoriateRepository.save(f);
    }

    @Transactional
    public List<Favoriate> getFavoriates(int userid) {
        return favoriateRepository.findByUserid(userid);
    }

    @Transactional
    public void deleteFrom(int id) {
        favoriateRepository.deleteById(id);
    }

    @Transactional
    public void deleteByProductId(int productid) {
        favoriateRepository.deleteByProduct_Id(productid);
    }

    @Transactional
    public void deleteByUserid(int uid) {
        favoriateRepository.deleteByUserid(uid);
    }
}
