/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;

/**
 *
 * @author hp
 */
public class ViewManger {
    public static dashboarddoctor dashboarddoctor;
    public static PatientDashbord PatientDashbord;
    public static userRegister userRegister;
    public static patientLogin patientLogin;
    public static DoctorLogin DoctorLogin;

    private ViewManger(){   
    }
    
    public static void openPatientDashbord() throws IOException{
        if (PatientDashbord == null) {
            PatientDashbord = new PatientDashbord();
            PatientDashbord.show();
        } else {
            PatientDashbord.show();
        }    
    }
    
    public static void closePatientDashbord(){
        if(PatientDashbord != null)
            PatientDashbord.close();
    }
    
    public static void opendashboarddoctor() throws IOException{
        if (dashboarddoctor == null) {
            dashboarddoctor = new dashboarddoctor();
            dashboarddoctor.show();
        } else {
            dashboarddoctor.show();
        }    
    }
    
    public static void closedashboarddoctor(){
        if(dashboarddoctor != null)
            dashboarddoctor.close();
    }

    public static void openUserRegister() throws IOException{
        if (userRegister == null) {
            userRegister = new userRegister();
            userRegister.show();
        } else {
            userRegister.show();
        }    
    }
    
    public static void closeUserRegister(){
        if(userRegister != null)
            userRegister.close();
    }
 
    public static void openpatientLogin() throws IOException{
        if (patientLogin == null) {
            patientLogin = new patientLogin();
            patientLogin.show();
        } else {
            patientLogin.show();
        }    
    }
    
    public static void closePatientLogin(){
        if(patientLogin != null)
            patientLogin.close();
    }

    public static void openDoctorLogin() throws IOException{
        if (DoctorLogin == null) {
            DoctorLogin = new DoctorLogin();
            DoctorLogin.show();
        } else {
            DoctorLogin.show();
        }    
    }
    
    public static void closeDoctorLogin(){
        if(DoctorLogin != null)
            DoctorLogin.close();
    }    
}
