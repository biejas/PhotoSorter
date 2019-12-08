package main.java.com.biejas.photosorter;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        Sorter sorter;
        try {
            sorter = new Sorter("./test-sets/set1", 40);
            sorter.sort();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}