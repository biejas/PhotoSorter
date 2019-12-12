package main.java.com.biejas.photosorter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorter {
    private final String dirPath;
    private final File dir;
    private final File[] photos;
    private final List<CategorizedFile> categorizedPhotos = new ArrayList<>();
    private Integer threshold;
    private String strategy;
    private final ObservableList<Category> foundCategories = FXCollections.observableArrayList();
    private final List<Category> chosenCategories = new ArrayList<>();


    public Sorter(String dirPath, Integer threshold, String strategy) throws FileNotFoundException {
        this.threshold = threshold;
        this.strategy = strategy;
        this.dirPath = dirPath;
        this.dir = new File(dirPath);

        if(!dir.exists()){
            throw new FileNotFoundException("Directory " + dirPath + " not found");
        }else {
            File[] files = dir.listFiles();
            for (File f : files) {
                if (!f.isFile()) {
                    throw new IllegalArgumentException("Illegal file " + f.getName());
                }
            }
            this.photos = ignoreNonImageFiles(files);
        }
    }

    private File[] ignoreNonImageFiles(File[] files){
        return Arrays.stream(files).filter(f -> isImage(f)).toArray(File[]::new);
    }

    private boolean isImage(File f) {
        boolean result=false;
        try {
            String mime = Files.probeContentType(f.toPath());
            if(mime != null) {
                result = mime.substring(0,5).contains("image");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void sort() throws IOException {
        if(strategy=="other"){
            File otherDir = new File(dirPath.concat("/Other"));
            if(!otherDir.exists()){
                otherDir.mkdir();
            }
        }
        for(Category c : chosenCategories){
            File newDir = new File(dirPath.concat("/"+c.getName()));
            if(!newDir.exists()){
                newDir.mkdir();
            }
        }
        for(CategorizedFile cf : categorizedPhotos){
            String dirNameForPhoto = cf.getTopCategory().getName();
            File dirForPhoto = new File(dirPath.concat("/"+dirNameForPhoto));
            if(!dirForPhoto.exists()){
                switch (strategy){
                    case "other":
                        Files.move(Paths.get(cf.getFile().getCanonicalPath()), Paths.get(dirPath.concat("/Other/"+cf.getFile().getName())));
                    case "inplace":
                        System.out.println("not moving file" + cf.getFile().getName());
                }
            }else{
                Files.move(Paths.get(cf.getFile().getCanonicalPath()), Paths.get(dirForPhoto.getCanonicalPath().concat("/"+cf.getFile().getName())));
            }
        }
    }

    public void findCategories(){
        foundCategories.clear();
        Categories.annotatePhotosInBatch(photos, threshold, categorizedPhotos);
        for(CategorizedFile cf : categorizedPhotos){
            for(Category c : cf.getCategories()){
                addNewFoundCategory(c);
            }
        }
    }

    public ObservableList<Category> getFoundCategories() {
        return foundCategories;
    }

    private void addNewFoundCategory(Category c) {
        if(!foundCategories.contains(c)){
            foundCategories.add(c);
        }
    }

    public void addChosenCategory(Category c){
        chosenCategories.add(c);
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
