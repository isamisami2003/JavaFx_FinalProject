package Controller;

import Model.Appointments;
import View.ViewManger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FreeappointmentsdoctorController implements Initializable{

    @FXML
    private Button BookedAppointmentsbutton;

    @FXML
    private Button DashBoardbutton;

    @FXML
    private Button FreeAppointmentsbutton;

    @FXML
    private TableColumn<Appointments, String> appointmentDateCol;

    @FXML
    private TableColumn<Appointments, String> appointmentDayCol;

    @FXML
    private TableColumn<Appointments, String> appointmentTimeCol;

    @FXML
    private TableView<Appointments> appointmentsTable;

    @FXML
    private Button createAppointmentButton;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<Appointments, String> statusCol;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    private Button showAppointmentsButton;

    private EntityManagerFactory entityManagerFactory;

    private AppointmentsJpaController appointmentsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        appointmentDateCol.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        appointmentDayCol.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        appointmentTimeCol.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    }


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
    void createAppointmentHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTocreateAppointmentdoctorPage();
    }

    @FXML
    void deleteAppointmenthandler(ActionEvent event) {
 Appointments selectedappointments = appointmentsTable.getSelectionModel().getSelectedItem();

        if (selectedappointments != null) {
            int AppointmentId = selectedappointments.getId(); // Assuming getId() returns the ID of the user

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure you want to delete this user?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        // Delete the selected user from the database using the delete method in UsersJpaController
                        appointmentsController.destroy(AppointmentId);
                        // Handle any exceptions that may occur during deletion
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Handle the exception appropriately
                    } finally {
                        Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                        deletedSuccessAlert.setTitle("User deleted");
                        deletedSuccessAlert.setContentText("User deleted");
                        deletedSuccessAlert.show();
                        // Close the entityManagerFactory if necessary
                        entityManagerFactory.close();
                    }
                }
            });
        }
    }

    @FXML
    void logoutButtonhandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToDoctorLoginPage();
    }

    @FXML
    void updateAppointmentHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToUpdateUserControllerPage();
    }
    @FXML
    void ShowAppointmentHandler(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");

        // Create an instance of the JPA controller and pass the emf
        AppointmentsJpaController appointmentsController = new AppointmentsJpaController(emf);

        try {
            List<Appointments> AppointmentList = appointmentsController.findAppointmentsEntities();
            ObservableList<Appointments> AppointmentsList = FXCollections.observableArrayList(AppointmentList);
            appointmentsTable.setItems(AppointmentsList);

        } catch (Exception e) {
            // Handle any exceptions that occur during the retrieval process
            e.printStackTrace();
        } finally {
            // Close the EntityManagerFactory when you're done
            emf.close();
        }
    }
}
