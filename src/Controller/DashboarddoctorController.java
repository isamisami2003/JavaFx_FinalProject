package Controller;

import Controller.exceptions.NonexistentEntityException;
import Model.Appointments;
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
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DashboarddoctorController implements Initializable{
    public static Users selectedUserToUpdate;
    //public static Stage updateStage;
    @FXML
    private Button BookedAppointments;

    @FXML
    private Button CreateUserButton;

    @FXML
    private Button DashbordButtong;

    @FXML
    private Button FreeAppointments;

    @FXML
    private TableColumn<Users, String> GenderCol;

    @FXML
    private Button LogOutButton;

    @FXML
    private TableView<Users> UserTable;

    @FXML
    private Button deleteUserButton;

    @FXML
    private TableColumn<Users, String> emailCol;

    @FXML
    private TableColumn<Users, String> firstNameCol;

    @FXML
    private TableColumn<Users, String> lastNameCol;

    @FXML
    private TableColumn<Users, String> phoneCol;

    @FXML
    private FontAwesomeIconView searchButton;

    @FXML
    private TextField searchPatientTxt;

    @FXML
    private Button showPatientButton;

    @FXML
    private Button updateUserButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phone"));
        GenderCol.setCellValueFactory(new PropertyValueFactory("gender"));
    } 
    
    
    @FXML
    void BookedAppointmentsHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToBookedAppointmentdoctorPage();
    }

    @FXML
    void CreateUserHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToUserRegisterPage();

    }

    @FXML
    void DashbordHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTodashboarddoctorPage();

    }

    @FXML
    void FreeAppointmentsHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneTofreeappointmentsdoctorPage();

    }

    @FXML
    void LogOutButtonHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToDoctorLoginPage();
    }

@FXML
void deleteUserHandler(ActionEvent event) throws NonexistentEntityException {
    // Create the EntityManagerFactory (emf) instance
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");

    // Create instances of the JPA controllers and pass the emf
    UsersJpaController usersController = new UsersJpaController(emf);
    AppointmentsJpaController appointmentsController = new AppointmentsJpaController(emf);

    // Get the selected user's ID from the UserTable
    int selectedUserId = UserTable.getSelectionModel().getSelectedItem().getId();

    // Retrieve the user from the controller using the ID
    Users user = usersController.getUserById(selectedUserId);

    // Delete the user from the database
    usersController.destroy(selectedUserId);

    // Further code implementation...
}


    @FXML
    void showPatientButtonHandler(ActionEvent event) {
    // Create the EntityManagerFactory (emf) instance
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicAppointmentsPu");

    // Create an instance of the JPA controller and pass the emf
    UsersJpaController usersController = new UsersJpaController(emf);
    AppointmentsJpaController AppointmentsController = new AppointmentsJpaController(emf);
    try {
        List<Appointments> AppointmentList = AppointmentsController.findAppointmentsEntities();
        ObservableList<Appointments> AppointmentsList = FXCollections.observableArrayList(AppointmentList);
//        DoctorTable.setItems((ObservableList<Users>) AppointmentsController);
        List<Users> userList = usersController.findUsersEntities();
        ObservableList<Users> usersList = FXCollections.observableArrayList(userList);
        UserTable.setItems(usersList);
    } catch (Exception e) {
        // Handle any exceptions that occur during the retrieval process
        e.printStackTrace();
    } finally {
        // Close the EntityManagerFactory when you're done
        emf.close();
    }
}

    @FXML
    void updateUserHandler(ActionEvent event) {
        ViewManger.dashboarddoctor.changeSceneToUpdateUserControllerPage();

    }

}
