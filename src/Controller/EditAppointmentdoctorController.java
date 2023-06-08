/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointments;
import View.ViewManger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class EditAppointmentdoctorController implements Initializable {


    private Appointments oldAppointment;

    @FXML
    private Button DashBoardbutton;
    @FXML
    private Button FreeAppointmentsbutton;
    @FXML
    private Button BookedAppointmentsbutton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button saveButton;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private TextField AppointmentDateTxt;
    @FXML
    private TextField AppointmentTimeTxt;
    @FXML
    private TextField AppointmentDayTxt;
    @FXML
    private Button backButton;
    @FXML
    private RadioButton bookedRadio;
    @FXML
    private RadioButton freeRadio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        this.oldAppointment = Controller.AppointmentsJpaController.selectedAppointmentToUpdate;
//
//        AppointmentDateTxt.setText(oldAppointment.getAppointmentDate());
//        AppointmentTimeTxt.setText(oldAppointment.getAppointmentTime());
//        AppointmentDayTxt.setText(oldAppointment.getAppointmentDay());
//
//        if (oldAppointment.getStatus().equals("free")) {
//            statusGroup.selectToggle(freeRadio);
//        }
//
//        if (oldAppointment.getStatus().equals("booked")) {
//            statusGroup.selectToggle(bookedRadio);
//        }
    }
    @FXML
    private void DashBoardbuttonhandler(ActionEvent event) {
                ViewManger.dashboarddoctor.changeSceneTodashboarddoctorPage();

    }

    @FXML
    private void FreeAppointmentsbuttonhandler(ActionEvent event) {
                ViewManger.dashboarddoctor.changeSceneTofreeappointmentsdoctorPage();

    }

    @FXML
    private void BookedAppointmentsbuttonhandler(ActionEvent event) {
                ViewManger.dashboarddoctor.changeSceneToBookedAppointmentdoctorPage();

    }

    @FXML
    private void logoutButtonhandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToDoctorLoginPage();

    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws Exception {
        String appointmentDate =  AppointmentDateTxt.getText();
        String appointmentTime = AppointmentTimeTxt.getText();
        String appointmentDay = AppointmentDayTxt.getText();
        String status = ((RadioButton) statusGroup.getSelectedToggle()).getText();
      

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");
        AppointmentsJpaController appointmentController = new AppointmentsJpaController(emf);
        //make an new user object having this data
        Appointments newAppointment = new Appointments(appointmentDate, appointmentTime, appointmentDay, status);

        newAppointment.setId(oldAppointment.getId());
        appointmentController.edit(newAppointment);

        //close the update stage using our global stage var in UsersManagmentController and show an alert
        ViewManger.dashboarddoctor.changeSceneToEditAppointmentdoctorPage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment updated");
        alert.setContentText("Appointment updated");
        alert.showAndWait();

    }

    @FXML
    private void backButtonhandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTocreateAppointmentdoctorPage();

    }
    
}
