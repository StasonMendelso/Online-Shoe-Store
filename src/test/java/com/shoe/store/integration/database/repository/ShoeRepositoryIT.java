package com.shoe.store.integration.database.repository;

import com.shoe.store.database.repository.ShoeRepository;
import com.shoe.store.integration.IntegrationTestBase;
import com.shoe.store.model.file.File;
import com.shoe.store.model.shoe.Shoe;
import com.shoe.store.model.shoe.ShoeFile;
import lombok.AllArgsConstructor;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@AllArgsConstructor
class ShoeRepositoryIT extends IntegrationTestBase {

    private final ShoeRepository shoeRepository;

    @Test
    void shouldReturnTwentyEntities_whenFindAllCalled() {
        final int expectedCount = 20;

        List<Shoe> shoeList = shoeRepository.findAll();

        assertEquals(expectedCount, shoeList.size());
    }

    @Test
    void shouldReturnFiveEntitiesOnSecondPage_whenFiveAsked() {
        final int expectedElementsNumber = 5;
        final int pageNumber = 1;

        Page<Shoe> shoePage = shoeRepository.findAllBy(
                PageRequest.of(pageNumber, expectedElementsNumber));

        assertEquals(pageNumber, shoePage.getPageable().getPageNumber());
        assertEquals(expectedElementsNumber, shoePage.getPageable().getPageSize());
    }


    @Test
    void shouldReturnThreeShoeColor_whenShoeHasThreeColors() {
        final Long id = 1L;
        final int expected = 3;

        List<Shoe> shoeSiblingsById = shoeRepository.findAllShoeColorsById(id);

        assertEquals(expected, shoeSiblingsById.size());
    }

    @Test
    void shouldReturnThreeDistinctShoeId_whenCalledShoeColors() {
        final Long id = 1L;

        List<Shoe> shoeSiblingsById = shoeRepository.findAllShoeColorsById(id);
        List<Long> shoesId = shoeSiblingsById.stream().mapToLong(Shoe::getId).boxed().toList();
        List<Long> distinctShoesId = shoesId.stream().distinct().toList();

        assertEquals(distinctShoesId.size(), shoesId.size());
        assertEquals(distinctShoesId, shoesId);
    }

    @Test
    void shouldContainSameShoeId_whenReturnShoeColors() {
        final Long id = 1L;

        List<Shoe> shoeSiblingsById = shoeRepository.findAllShoeColorsById(id);
        List<Long> shoesId = shoeSiblingsById.stream().mapToLong(Shoe::getId).boxed().toList();

        assertTrue(shoesId.contains(id), "List doesn't contain id, by which sampling was taken: " + shoesId);
    }

    @Test
    void shouldContainSameShoeId_whenShoeHaveOnlyOneColor_andNotHaveSiblings() {
        final Long id = 4L;
        final int expectedSize = 1;

        List<Shoe> shoeSiblingsById = shoeRepository.findAllShoeColorsById(id);
        List<Long> shoesId = shoeSiblingsById.stream().mapToLong(Shoe::getId).boxed().toList();

        assertTrue(shoesId.contains(id), "List doesn't contain id, by which sampling was taken: " + shoesId);
        assertEquals(expectedSize, shoesId.size());
    }

    @Test
    void shouldReturnShoeWithOneFile_whenOneFilePresent() {
        final Long id = 1L;

        Optional<Shoe> optionalShoe = shoeRepository.findById(id);

        assertTrue(optionalShoe.isPresent());

        Shoe shoe = optionalShoe.get();
        ShoeFile shoeFile1 = shoe.getShoeFileList().get(0);
        File file1 = shoeFile1.getFile();
        ShoeFile shoeFile2 = shoe.getShoeFileList().get(1);
        File file2 = shoeFile2.getFile();
        assertAll(() -> {
            assertNotNull(shoe.getShoeFileList());
            assertEquals(2, shoe.getShoeFileList().size());

            assertTrue(shoeFile1.isMainPhoto());
            assertEquals(1, shoeFile1.getSequenceNumber());
            assertEquals(1, file1.getId());
            assertEquals("1_1", file1.getName());
            assertEquals("shoes/1_1.jpg", file1.getRelativePath());
            assertEquals("jpg", file1.getFileExtension().getExtension());

            assertFalse(shoeFile2.isMainPhoto());
            assertEquals(2, shoeFile2.getSequenceNumber());
            assertEquals(2, file2.getId());
            assertEquals("1_2", file2.getName());
            assertEquals("shoes/1_2.jpg", file2.getRelativePath());
            assertEquals("jpg", file2.getFileExtension().getExtension());
        });
    }

    @Test
    void shouldReturnMainFileId_whenMainFilePersisted() {
        final Long id = 1L;
        final Long expected = 1L;

        Optional<Long> actual = shoeRepository.findMainPhotoIdByShoeId(id);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void shouldReturnEmptyMainPhotoId_whenMainFileNotPersisted() {
        final Long id = -1L;

        Optional<Long> actual = shoeRepository.findMainPhotoIdByShoeId(id);

        assertTrue(actual.isEmpty());
    }
}