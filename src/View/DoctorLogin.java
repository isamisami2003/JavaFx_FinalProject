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
public class DoctorLogin extends Stage{
    private Scene DoctorLogin;
    private Scene userRegister;
    
    public DoctorLogin() throws IOException{
        FXMLLoader DoctorLoginLoader = new FXMLLoader(getClass().getResource("ClinicFXML/DoctorLogin.fxml"));
        Parent DoctorLoginRoot = DoctorLoginLoader.load();     
        DoctorLogin = new Scene(DoctorLoginRoot);

        FXMLLoader userRegisterLoader = new FXMLLoader(getClass().getResource("ClinicFXML/userRegister.fxml"));
        Parent userRegisterRoot = userRegisterLoader.load();     
        userRegister = new Scene(userRegisterRoot);
        

        
        this.setScene(DoctorLogin);
        this.setTitle("Clinic Management");  
    }
    
    public void changeSceneToDoctorLoginPage(){
        this.setScene(DoctorLogin);
    }
    
    public void changeSceneToUserRegisterPage(){
        this.setScene(userRegister);
    }

}
