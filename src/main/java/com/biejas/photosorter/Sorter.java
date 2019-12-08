package main.java.com.biejas.photosorter;

import io.indico.Indico;
import io.indico.api.utils.IndicoException;

public class Sorter {
    private Indico indico;

    public Sorter(String apiKey) {
        try {
            this.indico = new Indico(apiKey);
        } catch (IndicoException e){
            e.printStackTrace();
        }
    }
}
