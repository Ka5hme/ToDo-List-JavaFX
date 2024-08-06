import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import com.assignment.Queue;
import com.assignment.Completed;

// may have to implement A List to Display completed
// ask prof about inheritance


public class javaFX extends Application {

    private Queue taskQueue = new Queue(10); // capacity
    private Label currentQueueLabel;
    private Label currentQueueLabel2;
    private TextField taskInputField;
    private Label nextToBeCompleted;
    private ListView<String> completedTasksListView;
    private ObservableList<String> completedTasksObservableList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To-Do List");

        // create labels
        currentQueueLabel = new Label("Current List: ");
        currentQueueLabel2 = new Label("");
        taskInputField = new TextField();
        taskInputField.setPromptText("Enter a new task");
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> handleButtonClick());

        Button finishButton = new Button("Finish Task");
        finishButton.setOnAction(e -> handleFinishButtonClick());

        nextToBeCompleted = new Label("");

        // listView for displaying completed tasks
        completedTasksObservableList = FXCollections.observableArrayList(Completed.getCompletedList());
        completedTasksListView = new ListView<>(completedTasksObservableList);

        // vBox for current tasks
        VBox currentTasksBox = new VBox(10, currentQueueLabel,currentQueueLabel2, nextToBeCompleted);
        currentTasksBox.setAlignment(Pos.CENTER);
        currentTasksBox.setPadding(new Insets(10));

        // vBox for completed tasks
        VBox completedTasksBox = new VBox(10, new Label("Completed Items:"), completedTasksListView);
        completedTasksBox.setAlignment(Pos.CENTER);
        completedTasksBox.setPadding(new Insets(10));

        // hBox to enclose current and completed tasks
        HBox tasksBox = new HBox(20, currentTasksBox, completedTasksBox);
        tasksBox.setAlignment(Pos.CENTER);

        // vBox for controls
        VBox inputBox = new VBox(10, new Label("Enter the new task:"), taskInputField, addButton, finishButton);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));

        // main vBox to enclose everything
        VBox mainBox = new VBox(20, tasksBox, inputBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(20));

        Scene scene = new Scene(mainBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick() {      // handles "add task" button
        String taskDescription = taskInputField.getText();
        taskQueue.enqueue(taskDescription);
        updateUI();
        taskInputField.clear();
    }

    private void handleFinishButtonClick() {    // handles "finish task" button
        taskQueue.dequeue();
        updateUI();
    }

    private void updateUI() {
        currentQueueLabel2.setText(taskQueue.toString());
        String nextInQueue = taskQueue.peek();
        if (nextInQueue != null) {
            nextToBeCompleted.setText("Next to be Completed: " + nextInQueue);
        } else {
            nextToBeCompleted.setText("Nothing left to be completed");
        }
        completedTasksObservableList.setAll(Completed.getCompletedList());  //shows completed list
    }
}
