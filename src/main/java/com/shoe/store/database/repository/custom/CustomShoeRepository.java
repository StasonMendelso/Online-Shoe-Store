package com.shoe.store.database.repository.custom;

import com.shoe.store.model.shoe.Shoe;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public interface CustomShoeRepository {
    /**
     * <b>Returns only data about product status, color data.</b>
     * @param id
     * @return data about product status, color data.
     */
    List<Shoe> findAllShoeColorsById(Long id);
}
