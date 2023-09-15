package com.shoe.store.database.repository;

import com.shoe.store.IntegrationTestBase;
import com.shoe.store.model.file.File;
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
import com.shoe.store.model.shoe.ShoeFile;
import com.shoe.store.model.shoe.SockType;
import com.shoe.store.model.shoe.SoleMaterial;
import com.shoe.store.model.shoe.SoleType;
import com.shoe.store.model.shoe.TopMaterial;
import lombok.AllArgsConstructor;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void shouldReturnAllSex_whenPersisted() {
        final List<Sex> expected = List.of(new Sex(1L, "Жіноча"), new Sex(3L, "Унісекс"), new Sex(2L, "Чоловіча"));

        List<Sex> actual = shoeRepository.findAllSex();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllBrand_whenPersisted() {
        final List<Brand> expected = List.of(new Brand(1L, "Dual"), new Brand(3L, "Bashilii"), new Brand(2L, "Springer"));

        List<Brand> actual = shoeRepository.findAllBrand();

        assertThat(actual).hasSameElementsAs(expected);

    }

    @Test
    void shouldReturnAllManufacturerCountry_whenPersisted() {
        final List<ManufacturerCountry> expected = List.of(new ManufacturerCountry(1L, "Україна"), new ManufacturerCountry(3L, "Турція"), new ManufacturerCountry(2L, "Китай"));

        List<ManufacturerCountry> actual = shoeRepository.findAllManufacturerCountry();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllSoleMaterial_whenPersisted() {
        final List<SoleMaterial> expected = List.of(new SoleMaterial(1L, "EVA пінка"), new SoleMaterial(3L, "Полівінілхлорид"), new SoleMaterial(2L, "Пінка"), new SoleMaterial(4L, "Поліуретан"));

        List<SoleMaterial> actual = shoeRepository.findAllSoleMaterial();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllTopMaterial_whenPersisted() {
        final List<TopMaterial> expected = List.of(new TopMaterial(1L, "Велюр"), new TopMaterial(3L, "Текстиль"), new TopMaterial(2L, "Натуральна шкіра"));

        List<TopMaterial> actual = shoeRepository.findAllTopMaterial();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllInsoleMaterial_whenPersisted() {
        final List<InsoleMaterial> expected = List.of(new InsoleMaterial(1L, "Текстиль"),
                new InsoleMaterial(2L, "Екошкіра"));

        List<InsoleMaterial> actual = shoeRepository.findAllInsoleMaterial();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllProductMaterial_whenPersisted() {
        final List<ProductMaterial> expected = List.of(new ProductMaterial(1L, "Полівінілхлорид"),
                new ProductMaterial(3L, "EVA пінка"), new ProductMaterial(2L, "Поліуретан"),
                new ProductMaterial(4L, "Пінка"));

        List<ProductMaterial> actual = shoeRepository.findAllProductMaterial();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllSoleType_whenPersisted() {
        final List<SoleType> expected = List.of(new SoleType(1L, "Без каблука"),
                new SoleType(2L, "Каблук"));

        List<SoleType> actual = shoeRepository.findAllSoleType();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllHeelHeight_whenPersisted() {
        final List<HeelHeight> expected = List.of(new HeelHeight(1L, "Міні (менше 3см)"), new HeelHeight(3L, "Високий (більше 7см)"), new HeelHeight(2L, "Середній (4-7см)"));

        List<HeelHeight> actual = shoeRepository.findAllHeelHeight();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllFastenerType_whenPersisted() {
        final List<FastenerType> expected = List.of(new FastenerType(1L, "Резинка"),
                new FastenerType(3L, "Шнурівка"), new FastenerType(2L, "Липучка"),
                new FastenerType(4L, "Немає"));

        List<FastenerType> actual = shoeRepository.findAllFastenerType();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllSockType_whenPersisted() {
        final List<SockType> expected = List.of(new SockType(1L, "Закритий"), new SockType(2L, "Відкритий"));

        List<SockType> actual = shoeRepository.findAllSockType();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllShoeCategory_whenPersisted() {
        final List<ShoeCategory> expected = List.of(new ShoeCategory(1L, "Чоботи"),
                new ShoeCategory(2L, "Черевики"), new ShoeCategory(3L, "Кросівки"),
                new ShoeCategory(4L, "Ботильйони"), new ShoeCategory(5L, "Мокасини"),
                new ShoeCategory(6L, "Босоніжки"), new ShoeCategory(7L, "Шльопанці"),
                new ShoeCategory(8L, "Балетки"), new ShoeCategory(9L, "Сандалі"),
                new ShoeCategory(10L, "Кеди"), new ShoeCategory(11L, "Зимові уггі"),
                new ShoeCategory(12L, "Сабо, мюлі"), new ShoeCategory(13L, "Сліпони"),
                new ShoeCategory(14L, "Получеревики"), new ShoeCategory(15L, "Туфлі"),
                new ShoeCategory(16L, "Лофери"), new ShoeCategory(17L, "Кріпери"),
                new ShoeCategory(18L, "Снікерси"), new ShoeCategory(19L, "В'єтнамки"),
                new ShoeCategory(20L, "Ботфорти"));

        List<ShoeCategory> actual = shoeRepository.findAllCategory();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllColor_whenPersisted() {
        final List<Color> expected = List.of(new Color(1L, "Чорний", "#000000"),
                new Color(2L, "Білий", "#FFFFFF"), new Color(3L, "Бежевий", "#F5F5DC"));

        List<Color> actual = shoeRepository.findAllColor();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    void shouldReturnAllSeasonality_whenPersisted() {
        final List<Seasonality> expected = List.of(new Seasonality(1L, "Весна"),
                new Seasonality(2L, "Літо"), new Seasonality(3L, "Осінь"),
                new Seasonality(4L, "Зима"), new Seasonality(5L, "Всесезон"));

        List<Seasonality> actual = shoeRepository.findAllSeasonality();

        assertThat(actual).hasSameElementsAs(expected);
    }
}