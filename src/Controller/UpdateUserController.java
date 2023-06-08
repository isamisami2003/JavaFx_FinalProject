/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Users;
import View.ViewManger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdateUserController implements Initializable {

    private Users oldUser;

    @FXML
    private RadioButton AdminRadio;

    @FXML
    private TextField AgeTextField;

    @FXML
    private RadioButton FemaleRadio;

    @FXML
    private Hyperlink backToLoginPageLink;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private TextField passwordTextField;

    @FXML
    private RadioButton patientRadio;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private Button singUpButton;

    @FXML
    private TextField userNameTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldUser = Controller.UsersJpaController.selectedUserToUpdate;

        firstNameTextField.setText(oldUser.getFirstname());
        lastNameTextField.setText(oldUser.getLastname());
        userNameTextField.setText(oldUser.getUsername());
        int age = oldUser.getAge();
        AgeTextField.setText(String.valueOf(age));
        emailTextField.setText(oldUser.getEmail());
        phoneTextField.setText(oldUser.getPhone());
        passwordTextField.setText(oldUser.getPassword());

        if (oldUser.getGender().equals("male")) {
            genderGroup.selectToggle(maleRadio);
        }

        if (oldUser.getGender().equals("female")) {
            genderGroup.selectToggle(FemaleRadio);
        }
        if (oldUser.getRole().equals("patient")) {
            genderGroup.selectToggle(patientRadio);
        }
        if (oldUser.getRole().equals("admin")) {
            genderGroup.selectToggle(AdminRadio);
        }
    }

    @FXML
    private void singUpButtonHandler(ActionEvent event) {
        //get the new data from text field's and store it in new user object
        String firstName = firstNameTextField.getText();
        String LastName = lastNameTextField.getText();
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        String ageText = AgeTextField.getText();
        int age = Integer.parseInt(ageText);
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton) roleGroup.getSelectedToggle()).getText();
        // make an user object having this data
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");
        UsersJpaController userController = new UsersJpaController(emf);

// Create a new user object with the updated data
        Users updatedUser = new Users(firstName, LastName, username, password, age, email, phone, gender, role);

// Set the ID of the updated user to the ID of the old user
        updatedUser.setId(oldUser.getId());

        try {
            // Update the user in the database
            userController.edit(updatedUser);

            // Close the update stage using our global stage variable in UsersManagementController
            
//            Controller.DashboarddoctorController.updateStage.close();

            // Show an alert to indicate successful user update
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User updated");
            alert.setContentText("User updated");
            alert.showAndWait();
        } catch (Exception ex) {
            // Handle any exceptions that occur during the update process
            ex.printStackTrace();

            // Show an alert with the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred during user update");
            alert.showAndWait();
        }
    }

    @FXML
    private void backToLoginPageLinkhandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTodashboarddoctorPage();

    }

}
