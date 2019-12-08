package main.java.com.biejas.photosorter;

import java.io.File;
import java.util.List;

public class CategorizedFile {
    private File file;
    private List<Category> categories;

    public CategorizedFile(File file) {
        this.file = file;
    }

    public CategorizedFile addCategory(Category c){
        categories.add(c);
        return this;
    }
}
