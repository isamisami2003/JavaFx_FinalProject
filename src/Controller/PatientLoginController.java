package Controller;

import Model.Users;
import View.ViewManger;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PatientLoginController {
    @FXML
    private Hyperlink AdminLogin;
    @FXML
    private Hyperlink RegisterLink;

    @FXML
    private Button loginbutton;

    @FXML
    private TextField patientPasswordTxt;

    @FXML
    private TextField patientUserTxt;

    @FXML
    void AdminLoginHandler(ActionEvent event) throws IOException {
        View.ViewManger.closePatientLogin();
        View.ViewManger.openDoctorLogin();
    }
    
    @FXML
    void RegisterLinkHandler(ActionEvent event) throws IOException {
        View.ViewManger.closePatientLogin();
        View.ViewManger.openUserRegister();

    }

 @FXML
void loginbuttonhandler(ActionEvent event) throws IOException {
    String username = patientUserTxt.getText();
    String password = patientPasswordTxt.getText();

    // Make a user object having this data
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");
    UsersJpaController userController = new UsersJpaController(emf);
    Users user = userController.findByUsernameAndPassword(username, password);

    if (user != null) {
        // User found, proceed to the next scene and show an alert
        View.ViewManger.closePatientLogin();
        View.ViewManger.openPatientDashbord(); // Replace with the appropriate method to open the dashboard

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User found");
        alert.setContentText("User found");
        alert.showAndWait();
    } else {
        // User not found, show an error alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("User not found");
        alert.setContentText("Invalid username or password");
        alert.showAndWait();
    }
}



}

