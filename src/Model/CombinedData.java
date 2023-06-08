/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hp
 */
public class CombinedData {
    private String appointmentId;
    private String userId;
    private String userName;
    // Add more properties as needed

    public CombinedData(String appointmentId, String userId, String userName) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.userName = userName;
        // Initialize additional properties as needed
    }

    public CombinedData(Appointments appointment, Users user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Add getter and setter methods for additional properties
}

