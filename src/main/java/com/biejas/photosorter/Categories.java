package main.java.com.biejas.photosorter;

import java.util.HashMap;
import java.util.Map;

public final class Categories {
    private static final Map<String, Category> registry = new HashMap<>();

    private Categories() {
    }

    public static Category getCategory(String name) {
        Category found = registry.get(name);
        if(found != null) {
            return found;
        }else {
            Category newCategory = new Category(name);
            registry.put(name,newCategory);
            return newCategory;
        }
    }
}
