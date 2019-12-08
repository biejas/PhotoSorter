package main.java.com.biejas.photosorter;

import io.indico.Indico;
import io.indico.api.utils.IndicoException;

import java.io.File;
import java.io.FileNotFoundException;

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
            photos = dir.listFiles();
            for (File f : photos) {
                if (!f.isFile()) {
                    throw new IllegalArgumentException("Illegal file " + f.getName());
                }
            }
        }
    }
}
