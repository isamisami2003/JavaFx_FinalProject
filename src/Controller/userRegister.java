package Controller;

import Model.Users;
import View.ViewManger;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class userRegister {

    @FXML
    private TextField AgeTextField;

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
    private TextField passwordTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private Button singUpButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    void backToLoginPageLinkhandler(ActionEvent event) throws IOException {
        View.ViewManger.closeUserRegister();
        View.ViewManger.openpatientLogin();
    }

    @FXML
    void singUpButtonHandler(ActionEvent event) throws SQLException, ClassNotFoundException {
        String firstName = firstNameTextField.getText();
        String LastName = lastNameTextField.getText();
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        String ageText = AgeTextField.getText();
        int age = Integer.parseInt(ageText);
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String gender = ((RadioButton)genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton)roleGroup.getSelectedToggle()).getText();
        // make an user object having this data
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");
        UsersJpaController userController=new UsersJpaController(emf);
        Users U1= new Users(firstName,LastName, username,password,age,email,phone,gender,role);
        userController.create(U1);
        
        //after saving should return to the back scene and show an alert
        ViewManger.PatientDashbord.changeSceneTopatientLoginPage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User inserted");
        alert.setContentText("User inserted");
        alert.showAndWait();
    }
               
}
