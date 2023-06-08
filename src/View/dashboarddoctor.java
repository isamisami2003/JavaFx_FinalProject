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
public class dashboarddoctor extends Stage{
        private Scene dashboarddoctor;  
        private Scene DoctorLogin;
        private Scene BookedAppointmentdoctor;
        private Scene freeappointmentsdoctor;
        private Scene EditAppointmentdoctor;
        private Scene createAppointmentdoctor;
        private Scene UpdateUser;
        private Scene userRegister;

        public dashboarddoctor() throws IOException{
        FXMLLoader dashboarddoctorLoader = new FXMLLoader(getClass().getResource("ClinicFXML/dashboarddoctor.fxml"));
        Parent dashboarddoctorRoot = dashboarddoctorLoader.load();     
        dashboarddoctor = new Scene(dashboarddoctorRoot);
        
        FXMLLoader DoctorLoginLoader = new FXMLLoader(getClass().getResource("ClinicFXML/DoctorLogin.fxml"));
        Parent DoctorLoginRoot = DoctorLoginLoader.load();     
        DoctorLogin = new Scene(DoctorLoginRoot);
        
        FXMLLoader BookedAppointmentdoctorLoader = new FXMLLoader(getClass().getResource("ClinicFXML/BookedAppointmentdoctor.fxml"));
        Parent BookedAppointmentdoctorRoot = BookedAppointmentdoctorLoader.load();     
        BookedAppointmentdoctor = new Scene(BookedAppointmentdoctorRoot);

        FXMLLoader freeappointmentsdoctorLoader = new FXMLLoader(getClass().getResource("ClinicFXML/freeappointmentsdoctor.fxml"));
        Parent freeappointmentsdoctorRoot = freeappointmentsdoctorLoader.load();     
        freeappointmentsdoctor = new Scene(freeappointmentsdoctorRoot);

        FXMLLoader EditAppointmentdoctorLoader = new FXMLLoader(getClass().getResource("ClinicFXML/EditAppointmentdoctor.fxml"));
        Parent EditAppointmentdoctorRoot = EditAppointmentdoctorLoader.load();     
        EditAppointmentdoctor = new Scene(EditAppointmentdoctorRoot);

        FXMLLoader createAppointmentdoctorLoader = new FXMLLoader(getClass().getResource("ClinicFXML/createAppointmentdoctor.fxml"));
        Parent createAppointmentdoctorRoot = createAppointmentdoctorLoader.load();     
        createAppointmentdoctor = new Scene(createAppointmentdoctorRoot);
        
        FXMLLoader userRegisterLoader = new FXMLLoader(getClass().getResource("ClinicFXML/userRegister.fxml"));
        Parent userRegisterRoot = userRegisterLoader.load();     
        userRegister = new Scene(userRegisterRoot);
        
        FXMLLoader UpdateUserLoader = new FXMLLoader(getClass().getResource("ClinicFXML/UpdateUser.fxml"));
        Parent UpdateUserRoot = UpdateUserLoader.load();     
        UpdateUser = new Scene(UpdateUserRoot);
      
        

        this.setScene(dashboarddoctor);
        this.setTitle("Clinic Management");  
    }
    public void changeSceneToUserRegisterPage(){
        this.setScene(userRegister);
    }        
    public void changeSceneToDoctorLoginPage(){
        this.setScene(DoctorLogin);
    }
    public void changeSceneToBookedAppointmentdoctorPage(){
        this.setScene(BookedAppointmentdoctor);
    }
    public void changeSceneToEditAppointmentdoctorPage(){
        this.setScene(EditAppointmentdoctor);
    }
    public void changeSceneTocreateAppointmentdoctorPage(){
        this.setScene(createAppointmentdoctor);
    }
    public void changeSceneTofreeappointmentsdoctorPage(){
        this.setScene(freeappointmentsdoctor);
    }
      public void changeSceneTodashboarddoctorPage(){
        this.setScene(dashboarddoctor);
    }
    public void changeSceneToUpdateUserControllerPage(){
        this.setScene(UpdateUser);
    }
}
