package main.java.com.biejas.photosorter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    private Sorter sorter;

    @BeforeEach
    void setUp() {
        try {
            Sorter sorter = new Sorter("./main/java/com/biejas/photosorter/testset1", 98);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void shouldReturnTrueAfterSorting() throws IOException {
        sorter.sort();
        assertTrue(new File("./main/java/com/biejas/photosorter/testset1/Dog").exists());
    }

    @Test
    void shouldReturnFalseAfterSorting() throws IOException {
        sorter.sort();
        assertFalse(new File("./main/java/com/biejas/photosorter/testset1/Cat").exists());
    }

}