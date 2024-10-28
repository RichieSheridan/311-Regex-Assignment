package sherrc._311regex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MainformController {

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField dateOfBirthInput;

    @FXML
    private TextField zipCodeInput;

    @FXML
    private TextArea errorTextArea;

    @FXML
    private Button addButton;

    @FXML
    public void initialize() {
        addButton.setDisable(true); //Disabled add button to start

        firstNameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { //This is how I track focus
                validateFirstName();
            }
        });

        lastNameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateLastName();
            }
        });

        emailInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateEmail();
            }
        });

        dateOfBirthInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateDateOfBirth();
            }
        });

        zipCodeInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateZipCode();
            }
        });
    }

    private void addErrorMessage(String message) {
        if (!errorTextArea.getText().contains(message)) {
            if (!errorTextArea.getText().isEmpty()) {
                errorTextArea.appendText("\n");
            }
            errorTextArea.appendText(message);
        }
    }

    private void removeErrorMessage(String message) {
        String currentText = errorTextArea.getText();
        currentText = currentText.replace(message, "").trim();
        errorTextArea.setText(currentText);
    }

    private void validateFirstName() {
        String input = firstNameInput.getText();
        String errorMessage = "First Name must contain between 2 and 25 alphabetic characters.";
        String regex = "^[A-Za-z]{2,25}$";
        if (!input.matches(regex)) {
            addErrorMessage(errorMessage);
        } else {
            removeErrorMessage(errorMessage);
        }
        checkFormValidity();
    }

    private void validateLastName() {
        String input = lastNameInput.getText();
        String errorMessage = "Last Name must contain between 2 and 25 alphabetic characters.";
        String regex = "^[A-Za-z]{2,25}$";
        if (!input.matches(regex)) {
            addErrorMessage(errorMessage);
        } else {
            removeErrorMessage(errorMessage);
        }
        checkFormValidity();
    }

    private void validateEmail() {
        String input = emailInput.getText();
        String errorMessage = "Email must be a valid Farmingdale email address.";
        String regex = "^[A-Za-z0-9._%+-]+@farmingdale\\.edu$";
        if (!input.matches(regex)) {
            addErrorMessage(errorMessage);
        } else {
            removeErrorMessage(errorMessage);
        }
        checkFormValidity();
    }


    private void validateDateOfBirth() {
        String input = dateOfBirthInput.getText();
        String errorMessage = "Date of Birth must be in MM/DD/YYYY format and a livable range.";
        String regex = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19[0-9]{2}|20[0-1][0-9]|202[0-4])$";

        if (!input.matches(regex)) {
            addErrorMessage(errorMessage);
        } else {
            removeErrorMessage(errorMessage);
        }
        checkFormValidity();
    }


    private void validateZipCode() {
        String input = zipCodeInput.getText();
        String errorMessage = "Zip Code must be a 5 digit number.";
        String regex = "^\\d{5}$";
        if (!input.matches(regex)) {
            addErrorMessage(errorMessage);
        } else {
            removeErrorMessage(errorMessage);
        }
        checkFormValidity();
    }


    private void checkFormValidity() {
        boolean isValid = true;

        //First Name
        String firstName = firstNameInput.getText();
        String nameRegex = "^[A-Za-z]{2,25}$";
        if (!firstName.matches(nameRegex)) {
            isValid = false;
        }

        //Last Name
        String lastName = lastNameInput.getText();
        if (!lastName.matches(nameRegex)) { //Using the same regex as first name
            isValid = false;
        }


        //Email
        String email = emailInput.getText();
        String emailRegex = "^[A-Za-z0-9._%+-]+@farmingdale\\.edu$";
        if (!email.matches(emailRegex)) {
            isValid = false;
        }

        //Date of Birth
        String dobInput = dateOfBirthInput.getText();
        String dobRegex = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19[0-9]{2}|20[0-1][0-9]|202[0-4])$";
        if (!dobInput.matches(dobRegex)) {
            isValid = false;
        }

        //Zip Code
        String zip = zipCodeInput.getText();
        String zipRegex = "^\\d{5}$";
        if (!zip.matches(zipRegex)) {
            isValid = false;
        }

        //Set add button
        addButton.setDisable(!isValid);
    }


    /**
     * Handles the action when the add button is clicked.
     * Clears the error text area, loads the "successfuladd.fxml" scene, and displays it.
     *
     * @param event The action event triggered when the add button is clicked.
     * @throws IOException If the "successfuladd.fxml" file cannot be loaded.
     * @since 1.0
     */
    @FXML
    private void handleAddButtonAction(ActionEvent event) throws IOException {
        System.out.println("Add button clicked"); //Debug
        errorTextArea.clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("successfuladd.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}