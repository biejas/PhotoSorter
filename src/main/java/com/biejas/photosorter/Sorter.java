package main.java.com.biejas.photosorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorter {
    private final String dirPath;
    private final File dir;
    private final File[] photos;
    private final List<CategorizedFile> categorizedPhotos = new ArrayList<>();
    private final Integer threshold;

    public Sorter(String dirPath, Integer threshold) throws FileNotFoundException {
        this.threshold = threshold;
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

    public void sort(){
        for (File photo : photos){
            System.out.println("Sorting started...");
            CategorizedFile categorized = new CategorizedFile(photo);
            categorized.categorize(threshold);
            categorizedPhotos.add(categorized);
        }
    }
}
