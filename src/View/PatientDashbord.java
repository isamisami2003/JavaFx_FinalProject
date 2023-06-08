/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class PatientDashbord extends Stage{
    private Scene patientLogin;
    private Scene userRegister;
    private Scene DoctorLogin;
    private Scene BookedAppointments;
    private Scene FreeAppointments;
    private Scene PatientDashbord;
//    --------------------------------
        
    public PatientDashbord() throws IOException{
        FXMLLoader usersManagmentLoader = new FXMLLoader(getClass().getResource("ClinicFXML/patientLogin.fxml"));
        Parent usersManagmentRoot = usersManagmentLoader.load();     
        patientLogin = new Scene(usersManagmentRoot);
        
        FXMLLoader userRegisterLoader = new FXMLLoader(getClass().getResource("ClinicFXML/userRegister.fxml"));
        Parent userRegisterRoot = userRegisterLoader.load();     
        userRegister = new Scene(userRegisterRoot);

        FXMLLoader DoctorLoginLoader = new FXMLLoader(getClass().getResource("ClinicFXML/DoctorLogin.fxml"));
        Parent AdminLoginRoot = DoctorLoginLoader.load();     
        DoctorLogin = new Scene(AdminLoginRoot);
        
        FXMLLoader BookedAppointmentsLoader = new FXMLLoader(getClass().getResource("ClinicFXML/BookedAppointments.fxml"));
        Parent BookedAppointmentsRoot = BookedAppointmentsLoader.load();     
        BookedAppointments = new Scene(BookedAppointmentsRoot);

        FXMLLoader FreeAppointmentsLoader = new FXMLLoader(getClass().getResource("ClinicFXML/FreeAppointments.fxml"));
        Parent FreeAppointmentsRoot = FreeAppointmentsLoader.load();     
        FreeAppointments = new Scene(FreeAppointmentsRoot);

        FXMLLoader PatientDashbordLoader = new FXMLLoader(getClass().getResource("ClinicFXML/PatientDashbord.fxml"));
        Parent PatientDashbordRoot = PatientDashbordLoader.load();     
        PatientDashbord = new Scene(PatientDashbordRoot);

        this.setScene(PatientDashbord);
        this.setTitle("Clinic Management");  
    }
    
    public void changeSceneToUserRegisterPage(){
        this.setScene(userRegister);
    }
    public void changeSceneToDoctorLoginPage(){
        this.setScene(DoctorLogin);
    }
    
    public void changeSceneTopatientLoginPage(){
        this.setScene(patientLogin);
    }
    public void changeSceneToBookedAppointmentsPage(){
        this.setScene(BookedAppointments);
    }
        public void changeSceneToFreeAppointmentsPage(){
        this.setScene(FreeAppointments);
    }
    public void changeSceneToPatientDashborPage(){
        this.setScene(PatientDashbord);
    }
}
