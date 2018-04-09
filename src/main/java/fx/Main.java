package fx;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private TextField textId;
    private TextField textTitle;
    private TextArea textAreaDescription;
    private DatePicker datePicker;
    private ComboBox<String> comboTaskState;
    private Button saveButton;
    private Button deleteButton;

    private Label labelId;
    private Label labelTitle;
    private Label labelDescription;
    private Label labelDatePicker;
    private Label labelTaskState;

    private VBox todoColumn = new VBox();
    private VBox doingColumn = new VBox();
    private VBox doneColumn = new VBox();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        //root.setStyle("-fx-background-color: aquamarine");


        ScrollPane scrollPane = new ScrollPane(createColumns());
        root.setCenter(scrollPane);
        root.setRight(createEditor());
        root.setBottom(createActions());

        initTasks();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initTasks() {
        todoColumn.getChildren().add(createTask(0, "Hello"));
        todoColumn.getChildren().add(createTask(1, "World"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));

    }

    private HBox createTask(int id, String title) {
        HBox task = new HBox();
        task.getChildren().addAll(new Label(String.valueOf(id)), new Label(title));
        task.setStyle("-fx-border-color: black");
        task.setPadding(new Insets(20));
        return task;
    }

    private Node createColumns() {
        todoColumn.setStyle("-fx-background-color: cornsilk");
        todoColumn.setSpacing(10);
        todoColumn.setPadding(new Insets(10));

        doingColumn.setStyle("-fx-background-color: cornsilk");
        doingColumn.setSpacing(10);
        doingColumn.setPadding(new Insets(10));

        doneColumn.setStyle("-fx-background-color: cornsilk");
        doneColumn.setSpacing(10);
        doneColumn.setPadding(new Insets(10));

        HBox columns = new HBox(20);
        columns.setPadding(new Insets(20));
        columns.getChildren().add(createColumn("Todo", todoColumn));
        columns.getChildren().add(createColumn("Doing", doingColumn));
        columns.getChildren().add(createColumn("Done", doneColumn));
        return columns;
    }

    private BorderPane createColumn(String label, Node contentColumn) {
        BorderPane column = new BorderPane();
        column.setTop(new Label(label));
        column.setCenter(contentColumn);
        return column;
    }

    private Node createEditor() {
        textId = new TextField();
        textTitle = new TextField();
        textAreaDescription = new TextArea();
        datePicker = new DatePicker(LocalDate.now());
        comboTaskState = new ComboBox<>();
        saveButton = new Button("Save");
        deleteButton = new Button("Delete");

        labelId = new Label("ID");
        labelTitle = new Label("Title");

        int row = 0;
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(20);
        grid.setVgap(10);
        labelDescription = new Label("Description");
        GridPane.setValignment(labelDescription, VPos.TOP);


        grid.add(labelId, 0, row);
        grid.add(textId, 1, row);
        row++;
        grid.add(labelTitle, 0, row);
        grid.add(textTitle, 1, row);
        row++;
        grid.add(labelDescription, 0, row);
        grid.add(textAreaDescription, 1, row);
        row++;
        grid.add(new Label("Date"), 0, row);
        grid.add(datePicker, 1, row);
        row++;
        grid.add(new Label("State"), 0, row);
        grid.add(comboTaskState, 1, row);
        row++;
        HBox buttonGroup = new HBox();
        buttonGroup.getChildren().addAll(saveButton, deleteButton);
        buttonGroup.setSpacing(10);

        grid.add(buttonGroup, 0, row, 2, 1);


        return grid;
    }

    private Node createActions() {
        HBox actionGroup = new HBox();
        Button newButton = new Button("New");
        Button refreshButton = new Button("Refresh");
        actionGroup.getChildren().addAll(newButton, refreshButton);
        actionGroup.setPadding(new Insets(20));
        actionGroup.setSpacing(10);
        return actionGroup;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
