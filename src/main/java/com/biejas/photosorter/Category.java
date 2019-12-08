package main.java.com.biejas.photosorter;

import java.util.List;

public class Category {
    private String name;
    private List<Category> related;

    public Category(String name) {
        this.name = name;
    }

    public Category addRelated(Category c){
        related.add(c);
        return this;
    }
}
