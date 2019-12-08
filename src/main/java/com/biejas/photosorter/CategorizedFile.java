package main.java.com.biejas.photosorter;

import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    public void categorize(Indico indico, Integer threshold){
        System.out.println("Categorizing file " + file.getName() + " ...");
        IndicoResult single = null;
        try {
            System.out.println("Predicting for file " + file.getName() + " ...");
            single = indico.imageRecognition.predict(file);
            Map<String,Double> result = single.getImageRecognition();
            result.forEach((key, value) -> System.out.println(key + ":" + value));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndicoException e) {
            e.printStackTrace();
        }
    }
}
