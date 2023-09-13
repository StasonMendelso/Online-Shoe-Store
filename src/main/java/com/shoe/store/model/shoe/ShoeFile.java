package com.shoe.store.model.shoe;

import com.shoe.store.model.file.File;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ShoeFile {
    @ManyToOne
    @JoinColumn(name = "files_id", referencedColumnName = "id")
    private File file;

    @Column(name = "main_photo", columnDefinition = "TINYINT")
    @Getter(AccessLevel.NONE)
    private Boolean mainPhoto;

    @Column(name = "sequence_number", columnDefinition = "SMALLINT")
    private Integer sequenceNumber;

    public Boolean isMainPhoto() {
        return mainPhoto;
    }
}
