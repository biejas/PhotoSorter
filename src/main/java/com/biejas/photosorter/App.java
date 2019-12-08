package main.java.com.biejas.photosorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Sorter sorter;
        try {
            sorter = new Sorter(new Scanner(new File("apikey.txt")).useDelimiter("\\Z").next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
