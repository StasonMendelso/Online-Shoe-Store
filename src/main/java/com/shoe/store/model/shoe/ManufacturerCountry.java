package com.shoe.store.model.shoe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Stanislav Hlova
 */

@Entity
@Table(name = "manufacturer_countries")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ManufacturerCountry {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name", unique = true, nullable = false)
    private String name;

}
