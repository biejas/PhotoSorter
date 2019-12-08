package main.java.com.biejas.photosorter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.io.File;

public class AppGUI extends Application {
    Stage stage;
    Scene scene;

    private @FXML AnchorPane anchorPane;

    private @FXML TextField folderPath;
    private @FXML TextField threshold;

    private @FXML RadioButton others;
    private @FXML RadioButton inPlace;
    private @FXML RadioButton bestFit;

    private @FXML VBox categories;

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

    }

    @FXML
    private void findsCategories(ActionEvent event){

    }
}
