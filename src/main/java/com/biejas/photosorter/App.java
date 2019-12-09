package main.java.com.biejas.photosorter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Sorter sorter;
        try {
            sorter = new Sorter("./test-sets/set1", 98);
            sorter.sort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}