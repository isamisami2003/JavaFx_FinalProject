package Controller;

import Model.Appointments;
import Model.BookedAppointments;
import Model.Users;
import View.ViewManger;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookedAppointmentdoctorController implements Initializable {


    @FXML
    private Button BookedAppointmentsbutton;

    @FXML
    private Button CommentButton;

    @FXML
    private Button DashBoardButton;

    @FXML
    private Button FreeAppointmentsButton;

    @FXML
    private TableView<BookedAppointments> UserTable;

    @FXML
    private TableColumn<BookedAppointments, String> commentCol;

    @FXML
    private TextArea commentTxtArea;

    @FXML
    private TableColumn<BookedAppointments, String> firstNameCol;

    @FXML
    private TableColumn<BookedAppointments, String> lastNameCol;

    @FXML
    private Button logoutButton;

    @FXML
    private FontAwesomeIconView searchButton;

    @FXML
    private TextField searchPationtTxt;

    @FXML
    private Button showPatientButton;

    @FXML
    private TableColumn<BookedAppointments, String> statusCol;
    @FXML
    private TableColumn<BookedAppointments, String> ageCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
//        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
//        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
//        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
//        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
    }

    @FXML
    void BookedAppointmentsHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToBookedAppointmentdoctorPage();

    }

    @FXML
    void CommentHandler(ActionEvent event) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");
         BookedAppointmentsJpaController controller = new BookedAppointmentsJpaController(emf); 
         BookedAppointments selectedAppointment = UserTable.getSelectionModel().getSelectedItem();
       

        try {
            BookedAppointments appointment = controller.findBookedAppointments();
            if (appointment != null) {
                String comment = appointment.getDoctorComment();
                String newComment = commentTxtArea.getText();
                appointment.setDoctorComment(newComment);
                appointment.setStatus("finished");
                controller.edit(appointment);
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Appointment Found");
                alert.setContentText("No waiting appointment with comment found.");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    void logoutButtonHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToDoctorLoginPage();
    }

    @FXML
    void showPatientHandler(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");

        // Create an instance of the JPA controller and pass the emf
        BookedAppointmentsJpaController bookedAppointmentsJpaController = new BookedAppointmentsJpaController(emf);

        try {
            List<BookedAppointments> bookAppointmentList = bookedAppointmentsJpaController.findBookedAppointmentsEntities();
            ObservableList<BookedAppointments> BookedAppointmentsList = FXCollections.observableArrayList(bookAppointmentList);
            UserTable.setItems(BookedAppointmentsList);

        } catch (Exception e) {
            // Handle any exceptions that occur during the retrieval process
            e.printStackTrace();
        } finally {
            // Close the EntityManagerFactory when you're done
            emf.close();
        }
    }
}


