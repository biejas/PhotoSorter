package main.java.com.biejas.photosorter;

import io.indico.Indico;
import io.indico.api.utils.IndicoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class Sorter {
    private Indico indico;
    private final String dirPath;
    private final File dir;
    private final File[] photos;

    public Sorter(String apiKey, String dirPath) throws FileNotFoundException {
        try {
            this.indico = new Indico(apiKey);
        } catch (IndicoException e){
            e.printStackTrace();
        }
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
}
