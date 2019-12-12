package main.java.com.biejas.photosorter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CategorizedFile {
    private File file;
    private Category topCategory;
    private List<Category> categories = new ArrayList<>();

    public CategorizedFile(File file) {
        this.file = file;
    }

    public CategorizedFile addCategory(Category c){
        categories.add(c);
        return this;
    }

    public void setTopCategory(){
        topCategory = categories.get(0);
    }

    public File getFile() {
        return file;
    }

    public Category getTopCategory() {
        return topCategory;
    }

    public List<Category> getCategories() {
        return categories;
    }
}

