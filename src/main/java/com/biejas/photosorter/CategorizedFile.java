package main.java.com.biejas.photosorter;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

