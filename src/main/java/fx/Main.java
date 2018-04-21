package fx;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private VBox todoColumn = new VBox();
    private VBox doingColumn = new VBox();
    private VBox doneColumn = new VBox();
    private final List<VBox> columns = Arrays.asList(todoColumn, doingColumn, doneColumn);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        //root.setStyle("-fx-background-color: aquamarine");
        ScrollPane scrollPane = new ScrollPane(createColumns());
        scrollPane.setFitToHeight(true);
        root.setCenter(scrollPane);
        root.setRight(createEditor());
        root.setBottom(createActions());

        initTasks();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Node createColumns() {
        String cornsilk = "-fx-background-color: cornsilk";
        Insets smallPadding = new Insets(10);
        layoutColumn(todoColumn, cornsilk, smallPadding);
        layoutColumn(doingColumn, cornsilk, smallPadding);
        layoutColumn(doneColumn, cornsilk, smallPadding);

        HBox columns = new HBox(20);
        columns.setPadding(new Insets(20));
        columns.getChildren().add(createColumn("Todo", todoColumn));
        columns.getChildren().add(createColumn("Doing", doingColumn));
        columns.getChildren().add(createColumn("Done", doneColumn));

        return columns;
    }

    private void layoutColumn(VBox column, String cornsilk, Insets smallPadding) {
        column.setStyle(cornsilk);
        column.setSpacing(10);
        column.setPadding(smallPadding);
    }

    private BorderPane createColumn(String label, Pane contentColumn) {
        BorderPane column = new BorderPane();
        column.setTop(new Label(label));

        addOnDragOver(contentColumn);
        addOnDragEntered(contentColumn);
        addOnDragExited(contentColumn);
        addOnDragDropped(contentColumn);

        ScrollPane scroll = new ScrollPane(contentColumn);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        column.setCenter(scroll);
        return column;
    }

    private void addOnDragOver(Pane contentColumn) {
        contentColumn.setOnDragOver(event -> {
            /* data is dragged over the target */
            /* accept it only if it is not dragged from the same node
             * and if it has a string data */
            if (!isCardInColumn(event.getGestureSource(), contentColumn) && event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.ANY);
            }

            event.consume();
        });
    }

    private boolean isCardInColumn(Object card, Pane column) {
        return column.getChildren().contains(card);
    }

    private void addOnDragEntered(Pane contentColumn) {
        contentColumn.setOnDragEntered(event -> {
            /* the drag-and-drop gesture entered the target */
            /* show to the user that it is an actual gesture target */
            if (!isCardInColumn(event.getGestureSource(), contentColumn) &&
                    event.getDragboard().hasString()) {
                String green = "-fx-background-color: green";
                contentColumn.setStyle(green);
            }

            event.consume();
        });
    }

    private void addOnDragExited(Pane contentColumn) {
        contentColumn.setOnDragExited(event -> {
            String cornsilk = "-fx-background-color: cornsilk";
            contentColumn.setStyle(cornsilk);
        });
    }

    private void addOnDragDropped(Pane contentColumn) {
        contentColumn.setOnDragDropped(event -> {
            /* data dropped */
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();

            boolean success = false;
            if (db.hasString()) {
                Pane columnFromDragStart = getColumnFromDragStart(((Pane) event.getGestureSource()).getParent());
                columnFromDragStart.getChildren().remove(event.getGestureSource());

                String[] split = db.getString().split("\\.");
                contentColumn.getChildren().add(createTask(Integer.parseInt(split[0]), split[1]));
                success = true;
            }

            /* let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private Pane getColumnFromDragStart(Parent columnFromCard) {
        return columns.stream().filter(column -> column.equals(columnFromCard)).findFirst().get();
    }

    private void initTasks() {
        todoColumn.getChildren().add(createTask(0, "Hello"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "Very long world task for testing"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        todoColumn.getChildren().add(createTask(1, "World"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doingColumn.getChildren().add(createTask(2, "Eric"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));
        doneColumn.getChildren().add(createTask(3, "Private appointment"));

    }

    private HBox createTask(int id, String title) {
        HBox task = new HBox();
        task.getChildren().addAll(new Label(String.valueOf(id)), new Label(title));
        task.setStyle("-fx-border-color: black");
        task.setPadding(new Insets(20));

        addOnDragEvent(id, title, task);

        return task;
    }

    private void addOnDragEvent(int id, String title, HBox task) {
        task.setOnDragDetected(event -> {
            /* drag was detected, start a drag-and-drop gesture*/
            /* allow any transfer mode */
            Dragboard db = task.startDragAndDrop(TransferMode.ANY);

            /* Put a string on a dragboard */
            ClipboardContent content = new ClipboardContent();

            content.putString(String.format("%s.%s", String.valueOf(id), title));
            db.setContent(content);

            event.consume();
        });
    }

    private Node createEditor() {
        TextField textId = new TextField();
        TextField textTitle = new TextField();
        TextArea textAreaDescription = new TextArea();
        DatePicker datePicker = new DatePicker(LocalDate.now());
        ComboBox<String> comboTaskState = new ComboBox<>();
        Button saveButton = new Button("Save");
        Button deleteButton = new Button("Delete");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(20);
        grid.setVgap(10);
        Label labelDescription = new Label("Description");
        GridPane.setValignment(labelDescription, VPos.TOP);

        int row = 0;
        addFormElement("ID", textId, row, grid);
        row++;
        addFormElement("Title", textTitle, row, grid);
        row++;
        addFormElement(labelDescription, textAreaDescription, row, grid);
        row++;
        addFormElement("Date", datePicker, row, grid);
        row++;
        addFormElement("State", comboTaskState, row, grid);
        row++;

        HBox buttonGroup = new HBox();
        buttonGroup.getChildren().addAll(saveButton, deleteButton);
        buttonGroup.setSpacing(10);
        grid.add(buttonGroup, 0, row, 2, 1);

        return grid;
    }

    private void addFormElement(String label, Node element, int row, GridPane grid) {
        grid.add(new Label(label), 0, row);
        grid.add(element, 1, row);
    }

    private void addFormElement(Label label, Node element, int row, GridPane grid) {
        grid.add(label, 0, row);
        grid.add(element, 1, row);
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
}
