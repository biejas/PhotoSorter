package main.java.com.biejas.photosorter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriesTest {

    private static List<CategorizedFile> cfs = new ArrayList<>();

    @BeforeAll
    static void setUp(){
        File dir = new File("./test-sets/set3");
        File[] files = dir.listFiles();
        Categories.annotatePhotosInBatch(files, 90, cfs);
    }

    @Test
    void shouldBeNotEmpty(){
        assertFalse(cfs.isEmpty());
    }

    @Test
    void shouldBeLandmark(){
        CategorizedFile cf = cfs.get(0);
        assertTrue(cf.getCategories().contains(Categories.getCategory("Landmark")));
    }

    @Test
    void shouldBeBridge(){
        CategorizedFile cf = cfs.get(5);
        assertTrue(cf.getCategories().contains(Categories.getCategory("Bridge")));
    }

    @Test
    void shouldBeTheSameObject() {
        Category c1 = Categories.getCategory("Landmark");
        Category c2 = Categories.getCategory("Landmark");
        assertEquals(c1,c2);
    }
}
