package main.java.com.biejas.photosorter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.File;
import java.io.IOException;

public class AppGUI extends Application {
    Stage stage;
    Scene scene;

    private @FXML AnchorPane anchorPane;

    private @FXML TextField folderPath;
    private @FXML TextField threshold;

    private @FXML RadioButton others;
    private @FXML RadioButton inPlace;

    private @FXML VBox categories;
    private @FXML CheckComboBox<Category> categoryBox;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/AppGUI.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void getDir(ActionEvent event){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("PhotoSorter");
        chooser.setInitialDirectory(new File(".."));
        File file = chooser.showDialog(stage);

        if (file != null) {
            folderPath.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void startSorting(ActionEvent event){
        try {
            Sorter sorter = new Sorter(folderPath.getText(), Integer.parseInt(threshold.getText()));
            sorter.sort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void findsCategories(ActionEvent event){
        ObservableList<Category> categoryObservableList = FXCollections.observableArrayList(); //podmien na wywolanie funkcji

        categoryBox.getItems().addAll(categoryObservableList);

        //Jak chcesz otrzymac zaznaczone boxy z listy
        //Dwa sposoby i co zwrócą:

        //[Item 1, Item 2]
        System.out.println(categoryBox.getCheckModel().getCheckedItems());

        //[1, 2]
        System.out.println(categoryBox.getCheckModel().getCheckedIndices());

    }
}
