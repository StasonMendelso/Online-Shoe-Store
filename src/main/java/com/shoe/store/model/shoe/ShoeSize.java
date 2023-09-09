package com.shoe.store.model.shoe;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ShoeSize {
    @Column(name = "size")
    private Integer size;
    @Column(name = "quantity")
    private Integer quantity;
}
