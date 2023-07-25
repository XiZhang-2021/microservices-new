package com.programming.productservice.repository;

import com.programming.productservice.model.Favoriate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriateRepository extends JpaRepository<Favoriate, Integer> {

    @Query("select f from Favoriate f where f.userid =:userid")
    List<Favoriate> findByUserid(@Param("userid") int userid);

    void deleteByProduct_Id(int productid);

    @Transactional
    @Modifying
    @Query("delete from Favoriate f where f.userid=:userid")
    void deleteByUserid(@Param("userid") int userid);
}
