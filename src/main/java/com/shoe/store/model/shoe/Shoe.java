package com.shoe.store.model.shoe;

import com.shoe.store.database.converter.ProductStatusConverter;
import com.shoe.store.enums.ProductStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Entity
@Table(name = "shoes")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Shoe {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 300)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_status", nullable = false)
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatus productStatus;

    @Column(name = "article", unique = true, nullable = false, length = 50)
    private String article;

    @Column(name = "model", nullable = false, length = 45)
    private String model;

    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "shoe_brand_id", referencedColumnName = "id")
    @ManyToOne
    private Brand brand;

    @JoinColumn(name = "manufacturer_countries_id", referencedColumnName = "id")
    @ManyToOne
    private ManufacturerCountry manufacturerCountry;

    @JoinColumn(name = "shoe_sole_material_id", referencedColumnName = "id")
    @ManyToOne
    private SoleMaterial soleMaterial;

    @JoinColumn(name = "shoe_top_material_id", referencedColumnName = "id")
    @ManyToOne
    private TopMaterial topMaterial;

    @JoinColumn(name = "shoe_insole_material_id", referencedColumnName = "id")
    @ManyToOne
    private InsoleMaterial insoleMaterial;

    @JoinColumn(name = "shoe_product_material_id", referencedColumnName = "id")
    @ManyToOne
    private ProductMaterial productMaterial;

    @JoinColumn(name = "shoe_heel_height_id", referencedColumnName = "id")
    @ManyToOne
    private HeelHeight heelHeight;

    @JoinColumn(name = "shoe_sole_type_id", referencedColumnName = "id")
    @ManyToOne
    private SoleType soleType;

    @JoinColumn(name = "sex_id", referencedColumnName = "id")
    @ManyToOne
    private Sex sex;

    @JoinColumn(name = "shoe_fastener_type_id", referencedColumnName = "id")
    @ManyToOne
    private FastenerType fastenerType;

    @JoinColumn(name = "shoe_sock_type_id", referencedColumnName = "id")
    @ManyToOne
    private SockType sockType;

    @JoinColumn(name = "shoe_category_id", referencedColumnName = "id")
    @ManyToOne
    private ShoeCategory shoeCategory;

    @JoinColumn(name = "shoe_color_id", referencedColumnName = "id")
    @ManyToOne
    private Color color;

    @ManyToMany
    @JoinTable(name = "shoes_has_seasonalities",
            joinColumns = @JoinColumn(name = "shoes_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seasonalities_id", referencedColumnName = "id"))
    private List<Seasonality> seasonalityList;

    @ElementCollection
    @CollectionTable(name = "shoe_sizes", joinColumns = @JoinColumn(name = "shoes_id"))
    private List<ShoeSize> shoeSizeList;
}
