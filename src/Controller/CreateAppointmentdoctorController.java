package Controller;

import Model.Appointments;
import View.ViewManger;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateAppointmentdoctorController {

    @FXML
    private TextField AppointmentDateTXt;

    @FXML
    private TextField AppointmentDayTXt;

    @FXML
    private Label AppointmentTimeTXt;

    @FXML
    private TextField AppointmentTimeText;

    @FXML
    private FontAwesomeIconView BackButton;

    @FXML
    private Button BookedAppointmentsButton;

    @FXML
    private Button DashBoardbutton;

    @FXML
    private Button FreeAppointmentsButton;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    void BookedAppointmentsHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToBookedAppointmentdoctorPage();
    }

    @FXML
    void DashBoardHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTodashboarddoctorPage();
    }

    @FXML
    void FreeAppointmentsHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTofreeappointmentsdoctorPage();
    }

    @FXML
    void backButtonhandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTocreateAppointmentdoctorPage();
    }

    @FXML
    void createButtonhandler(ActionEvent event) {
      
        String appointmentDate = AppointmentDateTXt.getText();
        String appointmentTime = AppointmentTimeTXt.getText();
        String appointmentDay = AppointmentDayTXt.getText();
        String status = ((RadioButton) statusGroup.getSelectedToggle()).getText();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");
        AppointmentsJpaController appointmentController = new AppointmentsJpaController(emf);
        try {
            Appointments a = new Appointments(appointmentDate, appointmentTime, appointmentDay, status);
            appointmentController.create(a);
            ViewManger.dashboarddoctor.changeSceneTofreeappointmentsdoctorPage();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment inserted");
            alert.setContentText("Appointment inserted");
            alert.showAndWait();
        } catch (Exception e) {
            // Handle any exceptions that occur during the retrieval process
            e.printStackTrace();
        } finally {
            // Close the EntityManagerFactory when you're done
            emf.close();
        }
    }

    @FXML
    void logoutButtonhandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToDoctorLoginPage();
    }

}
