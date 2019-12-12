package main.java.com.biejas.photosorter;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    private static Sorter sorter;

    @BeforeAll
    static void setUp() {
        try {
           sorter = new Sorter("./test-sets/set1", 98, "inplace");
           sorter.findCategories();
           sorter.addChosenCategory(Categories.getCategory("Dog"));
           sorter.sort();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
    }


    @Test
    void shouldReturnTrueAfterSorting() throws IOException {
        assertTrue(new File("./test-sets/set1/Dog").exists());
    }

    @Test
    void shouldReturnFalseAfterSorting() throws IOException {
        assertFalse(new File("./test-sets/set1/Cat").exists());
    }

}