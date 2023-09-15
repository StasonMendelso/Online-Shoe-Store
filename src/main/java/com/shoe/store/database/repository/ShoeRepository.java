package com.shoe.store.database.repository;

import com.shoe.store.database.repository.custom.CustomShoeRepository;
import com.shoe.store.model.shoe.Brand;
import com.shoe.store.model.shoe.Color;
import com.shoe.store.model.shoe.FastenerType;
import com.shoe.store.model.shoe.HeelHeight;
import com.shoe.store.model.shoe.InsoleMaterial;
import com.shoe.store.model.shoe.ManufacturerCountry;
import com.shoe.store.model.shoe.ProductMaterial;
import com.shoe.store.model.shoe.Seasonality;
import com.shoe.store.model.shoe.Sex;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.model.shoe.ShoeCategory;
import com.shoe.store.model.shoe.SockType;
import com.shoe.store.model.shoe.SoleMaterial;
import com.shoe.store.model.shoe.SoleType;
import com.shoe.store.model.shoe.TopMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
public interface ShoeRepository extends PagingAndSortingRepository<Shoe, Long>, JpaRepository<Shoe, Long>, CustomShoeRepository {
    Page<Shoe> findAllBy(Pageable pageable);

    List<Shoe> findAll();

    @Query(value = "SELECT files_id " +
                   "FROM shoe_files " +
                   "WHERE shoes_id = :shoeId AND main_photo = TRUE", nativeQuery = true)
    Optional<Long> findMainPhotoIdByShoeId(Long shoeId);

    @Query(value = "select s from Sex s")
    List<Sex> findAllSex();

    @Query(value = "select b from Brand b")
    List<Brand> findAllBrand();

    @Query(value = "select m from ManufacturerCountry m")
    List<ManufacturerCountry> findAllManufacturerCountry();

    @Query(value = "select s from SoleMaterial s")
    List<SoleMaterial> findAllSoleMaterial();

    @Query(value = "select t from TopMaterial t")
    List<TopMaterial> findAllTopMaterial();

    @Query(value = "select i from InsoleMaterial i")
    List<InsoleMaterial> findAllInsoleMaterial();

    @Query(value = "select p from ProductMaterial p")
    List<ProductMaterial> findAllProductMaterial();

    @Query(value = "select s from SoleType s")
    List<SoleType> findAllSoleType();

    @Query(value = "select h from HeelHeight h")
    List<HeelHeight> findAllHeelHeight();

    @Query(value = "select f from FastenerType f")
    List<FastenerType> findAllFastenerType();

    @Query(value = "select s from SockType s")
    List<SockType> findAllSockType();

    @Query(value = "select s from ShoeCategory s")
    List<ShoeCategory> findAllCategory();

    @Query(value = "select c from Color c")
    List<Color> findAllColor();
    @Query(value = "select s from Seasonality s")
    List<Seasonality> findAllSeasonality();


}
