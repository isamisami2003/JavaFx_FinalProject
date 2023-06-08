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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookedAppointmentsController implements Initializable {

    @FXML
    private Button BookedAppointmentsButton;

    @FXML
    private Button DashBoardButton;

    @FXML
    private TableView<Users> DoctorsTable;

    @FXML
    private TableColumn<Users, String> FirstnameCol;

    @FXML
    private Button FreeAppointmentsButton;

    @FXML
    private TextField SearchDoctorTXt;

    @FXML
    private TableColumn<BookedAppointments, String> commentCol;

    @FXML
    private TableColumn<Users, String> emailCol;

    @FXML
    private TableColumn<Users, String> lastnameCol;

    @FXML
    private Button logoutButton;

    @FXML
    private FontAwesomeIconView searchButton;

    @FXML
    private Button showDoctorsButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FirstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        commentCol.setCellValueFactory(new PropertyValueFactory("doctor_comment"));
    } 
    @FXML
    void BookedAppointmentsHandler(ActionEvent event) {
        ViewManger.PatientDashbord.changeSceneToBookedAppointmentsPage();

    }

    @FXML
    void DashBoardHandler(ActionEvent event) {
        ViewManger.PatientDashbord.changeSceneToPatientDashborPage();

    }

    @FXML
    void FreeAppointmentsHandler(ActionEvent event) {
        ViewManger.PatientDashbord.changeSceneToFreeAppointmentsPage();

    }

    @FXML
    void logoutButtonhandler(ActionEvent event) {
        ViewManger.PatientDashbord.changeSceneTopatientLoginPage();
    }

    @FXML
    void showDoctorsHandler(ActionEvent event) {
    // Create the EntityManagerFactory (emf) instance
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");

    // Create an instance of the JPA controller and pass the emf
    UsersJpaController usersController = new UsersJpaController(emf);
    AppointmentsJpaController AppointmentsController = new AppointmentsJpaController(emf);

    try {
        List<Appointments> AppointmentList = AppointmentsController.findAppointmentsEntities();
        ObservableList<Appointments> AppointmentsList = FXCollections.observableArrayList(AppointmentList);
//        DoctorTable.setItems((ObservableList<Users>) AppointmentsController);
        List<Users> userList = usersController.getDoctors();
        ObservableList<Users> usersList = FXCollections.observableArrayList(userList);
        DoctorsTable.setItems(usersList);
       
    } catch (Exception e) {
        // Handle any exceptions that occur during the retrieval process
        e.printStackTrace();
    } finally {
        // Close the EntityManagerFactory when you're done
        emf.close();
    }
    }

}
