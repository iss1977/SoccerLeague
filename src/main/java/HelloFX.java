import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class HelloFX extends Application {

    private TableView table = new TableView();
    public static League myLeague;

    public static Team teamHome, teamGuest;
    public static Game gameRead;


    ///////////////////////////////////////  MAIN  ////////////////////////////////////////////////
    public static void main(String[] args) {
        readScoreFromFile();
        launch(args);
    }




    @Override
    public void start(Stage stage) {

        ObservableList<Team> source = FXCollections.observableArrayList(myLeague.getTeamTable());

        // root
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20)); // space between elements and window border
        Scene scene = new Scene(root, 600, 400);



        stage.setTitle("Soccker Team");
        stage.setWidth(800);
        stage.setHeight(500);

        final Label label = new Label("The Teams:");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn teamNameCol = new TableColumn("Team Name");
        TableColumn teamPointsCol = new TableColumn("Points");
        TableColumn goalsShotCol = new TableColumn("Goals Shot");
        TableColumn goalsReceivedCol = new TableColumn("Goals Received");
        TableColumn winsCol = new TableColumn("Wins");
        TableColumn defeatsCol = new TableColumn("Defeats");
        TableColumn drawsCol = new TableColumn("Draws");



        table.getColumns().addAll(teamNameCol, teamPointsCol, goalsShotCol,goalsReceivedCol,winsCol,defeatsCol,drawsCol);

        // --------------------- Autosize ---------------------------------
        teamNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        teamPointsCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        goalsShotCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        goalsReceivedCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        winsCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        defeatsCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        drawsCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        // We bind the prefHeight- and prefWidthProperty to the height and width of the stage.
        table.prefHeightProperty().bind(stage.heightProperty().multiply(0.80) );
        table.prefWidthProperty().bind(stage.widthProperty().multiply(0.95));

        // ---------------- Binding the columns with the values ----------------------------------
        teamNameCol.setMinWidth(100);
        teamNameCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("name"));
        teamPointsCol.setMinWidth(100);
        teamPointsCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("PointsUsingLambda"));
        goalsShotCol.setMinWidth(100);
        goalsShotCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("goalsShot"));
        goalsReceivedCol.setMinWidth(100);
        goalsReceivedCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("goalsReceived"));
        winsCol.setMinWidth(100);
        winsCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("wins"));
        defeatsCol.setMinWidth(100);
        defeatsCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("defeats"));
        drawsCol.setMinWidth(100);
        drawsCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("draws"));


        final VBox vbox = new VBox();
        final HBox hb = new HBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table,hb);

        // define the button with methods
        final Button addButton = new Button("Close");

        addButton.setOnAction( e->Platform.exit());

        hb.getChildren().addAll(addButton);
        hb.setAlignment(Pos.CENTER_RIGHT);
        hb.setSpacing(3);

        root.setCenter(vbox);
        root.setBottom(hb);


        table.setItems(source);
        stage.setScene(scene);
        stage.show();
    }



    // ----------------------    Read the information from csv file ---------------------------------
    public static void readScoreFromFile(){
        String[] res;

        myLeague = new League();
        boolean firstLine = true; //  the first line in our css file contains the header, this will be not read....
        try {
            File myObj = new File("SoccerLeague.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (firstLine){ // skip the first line with headers.
                    firstLine = false;
                    continue;
                }

                res = data.split("[,]", 0);
                teamHome = new Team(res[0].trim());
                teamGuest = new Team (res[1].trim());
                gameRead = new Game (teamHome,teamGuest,Integer.parseInt(res[2]),Integer.parseInt(res[3]));
                System.out.printf("Adding game result via myLeague.addGameResult. Home Team : %s , Guest Team: %s, Score %d - %d %n",teamHome.getName(),teamGuest.getName(),Integer.parseInt(res[2]),Integer.parseInt(res[3]));
                myLeague.addGameResult(gameRead);


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(myLeague.getTeamTable());
        System.out.println(myLeague.listTeamAsTable());

    }// end of read from file



}