package com.shoe.store.database.repository.custom;

import com.shoe.store.database.converter.ProductStatusConverter;
import com.shoe.store.model.shoe.Color;
import com.shoe.store.model.shoe.Shoe;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
public class CustomShoeRepositoryImpl implements CustomShoeRepository {
    private final EntityManager entityManager;
    private final ProductStatusConverter productStatusConverter;

    static class Query {
        private static final String READ_SHOE_COLORS_BY_ID = "SELECT s.id AS `id`, c.id AS 'color.id', c.name, c.rgb, s.product_status " +
                                                             "FROM shoes_has_siblings " +
                                                             "JOIN shoes s ON s.id = shoes_has_siblings.shoes_sibling_id " +
                                                             "JOIN shoe_colors c ON c.id = s.shoe_color_id " +
                                                             "WHERE shoes_id = :id " +
                                                             "UNION " +
                                                             "SELECT s.id AS `id`, c.id AS 'color.id', c.name, c.rgb, s.product_status " +
                                                             "FROM shoes s " +
                                                             "JOIN shoe_colors c ON c.id = s.shoe_color_id " +
                                                             "WHERE s.id = :id " +
                                                             "ORDER BY id;";
    }


    @Override
    public List<Shoe> findAllShoeColorsById(Long id) {
        List<Shoe> result = new ArrayList<>();
        entityManager.createNativeQuery(Query.READ_SHOE_COLORS_BY_ID)
                .setParameter("id", id)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((tuple, aliases) -> result.add(
                        Shoe.builder()
                                .id((Long) tuple[0])
                                .productStatus(productStatusConverter.convertToEntityAttribute((String)tuple[4]))
                                .color(Color.builder()
                                        .id((Long) tuple[1])
                                        .name((String) tuple[2])
                                        .rgb((String) tuple[3])
                                        .build())
                                .build()
                        ))
                .getResultList();

        return result;
    }
}
