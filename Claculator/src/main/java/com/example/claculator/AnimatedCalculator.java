// Created by Antoan Stoykov 01.02.2023

package com.example.claculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AnimatedCalculator extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creates a grid to hold the UI elements
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add a label and text field for the first number
        Label num1Label = new Label("First Number:");
        grid.add(num1Label, 0, 1);

        TextField num1Field = new TextField();
        grid.add(num1Field, 1, 1);

        // Add a label and text field for the second number
        Label num2Label = new Label("Second Number:");
        grid.add(num2Label, 0, 2);

        TextField num2Field = new TextField();
        grid.add(num2Field, 1, 2);

        // Add a label and text field for the operator
        Label operatorLabel = new Label("Operator:");
        grid.add(operatorLabel, 0, 3);

        TextField operatorField = new TextField();
        grid.add(operatorField, 1, 3);

        // Add a button for the calculation
        Button btn = new Button("Calculate");
        grid.add(btn, 1, 4);

        // Add a label to show the result
        Label resultLabel = new Label();
        grid.add(resultLabel, 1, 5);

        // Add a list view to show the calculation history
        ListView<String> historyList = new ListView<>();
        ObservableList<String> history = FXCollections.observableArrayList();
        historyList.setItems(history);
        grid.add(historyList, 2, 1, 1, 5);

        // Label to display "Theme:"
        Label themeLabel = new Label("Theme:");
        grid.add(themeLabel, 0, 6);

        // ChoiceBox to allow user to select different themes
        ChoiceBox<String> themeChoice = new ChoiceBox<>(FXCollections.observableArrayList("Light", "Honeydew", "Pink"));
        themeChoice.getSelectionModel().selectFirst();
        grid.add(themeChoice, 1, 6);

        // Label to display "Font Size:"
        Label fontSizeLabel = new Label("Font Size:");
        grid.add(fontSizeLabel, 0, 7);

        // ChoiceBox to allow user to select font size
        ChoiceBox<String> fontSizeChoice = new ChoiceBox<>(FXCollections.observableArrayList("12", "14", "16", "18", "20"));
        fontSizeChoice.getSelectionModel().selectFirst();
        grid.add(fontSizeChoice, 1, 7);


        // Event calling function for backgroundColor
        themeChoice.setOnAction(event -> setBackgroundColor(grid, num1Label, num2Label, operatorLabel, resultLabel, themeChoice));

        // Event calling function for font changing size
        fontSizeChoice.setOnAction(event -> setFontSize(num1Label, num1Field, num2Label, num2Field, operatorLabel, operatorField, btn, resultLabel, fontSizeChoice));



        // Set the event handler for the button
        btn.setOnAction(event -> {
            double num1 = getNumber(num1Field);
            if (Double.isNaN(num1)) {
                resultLabel.setText("Error: Invalid first number");
                return;
            }

            double num2 = getNumber(num2Field);
            if (Double.isNaN(num2)) {
                resultLabel.setText("Error: Invalid second number");
                return;
            }

            String operator = operatorField.getText();
            double result = calculateResult(num1, num2, operator);
            if (Double.isNaN(result)) {
                resultLabel.setText("Error: Invalid operator");
                return;
            }


            resultLabel.setText("Result: " + result);
            history.add(num1 + " " + operator + " " + num2 + " = " + result);
        });


        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    // Method to set the font size
    private static void setFontSize(Label num1Label, TextField num1Field, Label num2Label, TextField num2Field, Label operatorLabel, TextField operatorField, Button btn, Label resultLabel, ChoiceBox<String> fontSizeChoice) {
        int size = Integer.parseInt(fontSizeChoice.getValue());
        num1Label.setFont(Font.font(size));
        num2Label.setFont(Font.font(size));
        operatorLabel.setFont(Font.font(size));
        resultLabel.setFont(Font.font(size));
        num1Field.setFont(Font.font(size));
        num2Field.setFont(Font.font(size));
        operatorField.setFont(Font.font(size));
        btn.setFont(Font.font(size));
    }

    // Method to set the Background color
    private static void setBackgroundColor(GridPane grid, Label num1Label, Label num2Label, Label operatorLabel, Label resultLabel, ChoiceBox<String> themeChoice) {
        String theme = themeChoice.getValue();
        switch (theme) {
            case "Light" -> {
                grid.setStyle("-fx-background-color: PapayaWhip;");
                num1Label.setTextFill(Color.BLACK);
                num2Label.setTextFill(Color.BLACK);
                operatorLabel.setTextFill(Color.BLACK);
                resultLabel.setTextFill(Color.BLACK);
            }
            case "Honeydew" -> {
                grid.setStyle("-fx-background-color: Honeydew;");
                num1Label.setTextFill(Color.BLACK);
                num2Label.setTextFill(Color.BLACK);
                operatorLabel.setTextFill(Color.BLACK);
                resultLabel.setTextFill(Color.BLACK);
            }
            case "Pink" -> {
                grid.setStyle("-fx-background-color: Plum;");
                num1Label.setTextFill(Color.BLACK);
                num2Label.setTextFill(Color.BLACK);
                operatorLabel.setTextFill(Color.BLACK);
                resultLabel.setTextFill(Color.BLACK);
            }
        }
    }

    /**
     * @param field the field to write
     * @return the number on the field
     */
    private double getNumber(TextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    /**
     * @return the calculation logic
     */
    private double calculateResult(double num1, double num2, String operator) {
        try {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 == 0) {
                        return Double.NaN;
                    }
                    return num1 / num2;
                case "%":
                    return num1 * num2 / 100;
                case "sqrt":
                    return Math.sqrt(num1);
                case "^":
                    return Math.pow(num1, num2);
                default:
                    return Double.NaN;
            }
        } catch (ArithmeticException e) {
            return Double.NaN;
        }
    }


}
