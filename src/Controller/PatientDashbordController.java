package Controller;

import Model.Appointments;
import Model.CombinedData;
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

public class PatientDashbordController implements Initializable{

    @FXML
    private Button BookedAppointmentsButton;

    @FXML
    private Button DashBoardbutton;

    @FXML
    private TableView<Users> DoctorTable;

    @FXML
    private Button FreeAppointmentsButton;

    @FXML
    private FontAwesomeIconView SerachButton;

    @FXML
    private TableColumn<Users, String> emailCol;

    @FXML
    private TableColumn<Users, String> firstnameCol;

    @FXML
    private TableColumn<Users, String> lastnameCol;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<Users, String> phoneCol;

    @FXML
    private TextField searchDoctorTxt;

    @FXML
    private Button showDoctorButton;

    @FXML
    private TableColumn<Appointments, String> statusCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
    } 
    
    @FXML
    void BookedAppointmentsHandler(ActionEvent event) {
        ViewManger.PatientDashbord.changeSceneToBookedAppointmentsPage();
    }

    @FXML
    void DashBoardbuttonhandler(ActionEvent event) {
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
void showDoctorHandler(ActionEvent event) {
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
        DoctorTable.setItems(usersList);
        


    } catch (Exception e) {
        // Handle any exceptions that occur during the retrieval process
        e.printStackTrace();
    } finally {
        // Close the EntityManagerFactory when you're done
        emf.close();
    }
}



}
