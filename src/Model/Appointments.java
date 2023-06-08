/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointments.findAll", query = "SELECT a FROM Appointments a")
    , @NamedQuery(name = "Appointments.findById", query = "SELECT a FROM Appointments a WHERE a.id = :id")
    , @NamedQuery(name = "Appointments.findByAppointmentDate", query = "SELECT a FROM Appointments a WHERE a.appointmentDate = :appointmentDate")
    , @NamedQuery(name = "Appointments.findByAppointmentDay", query = "SELECT a FROM Appointments a WHERE a.appointmentDay = :appointmentDay")
    , @NamedQuery(name = "Appointments.findByAppointmentTime", query = "SELECT a FROM Appointments a WHERE a.appointmentTime = :appointmentTime")
    , @NamedQuery(name = "Appointments.findByStatus", query = "SELECT a FROM Appointments a WHERE a.status = :status")})
public class Appointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "appointment_date")
    private String appointmentDate;
    @Column(name = "appointment_day")
    private String appointmentDay;
    @Column(name = "appointment_time")
    private String appointmentTime;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "appointmentId")
    private Collection<BookedAppointments> bookedAppointmentsCollection;
        
    public Appointments() {

        }     

    public Appointments(String appointmentDate, String appointmentTime, String appointmentDay, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentDay = appointmentDay;
        this.status = status;
    }
    public Appointments(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<BookedAppointments> getBookedAppointmentsCollection() {
        return bookedAppointmentsCollection;
    }

    public void setBookedAppointmentsCollection(Collection<BookedAppointments> bookedAppointmentsCollection) {
        this.bookedAppointmentsCollection = bookedAppointmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointments)) {
            return false;
        }
        Appointments other = (Appointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Appointments[ id=" + id + " ]";
    }
    
}
