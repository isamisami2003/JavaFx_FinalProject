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
public class userRegister extends Stage{
        private Scene patientLogin;
        private Scene userRegister;
        
        public userRegister() throws IOException{
            
        FXMLLoader usersManagmentLoader = new FXMLLoader(getClass().getResource("ClinicFXML/patientLogin.fxml"));
        Parent usersManagmentRoot = usersManagmentLoader.load();     
        patientLogin = new Scene(usersManagmentRoot);
        
        FXMLLoader userRegisterLoader = new FXMLLoader(getClass().getResource("ClinicFXML/userRegister.fxml"));
        Parent userRegisterRoot = userRegisterLoader.load();     
        userRegister = new Scene(userRegisterRoot);
        
        this.setScene(userRegister);
        this.setTitle("Clinic Management");  
}
    public void changeSceneTopatientLoginPage(){
        this.setScene(patientLogin);
    }
       
    public void changeSceneToUserRegisterPage(){
        this.setScene(userRegister);
    }   
}