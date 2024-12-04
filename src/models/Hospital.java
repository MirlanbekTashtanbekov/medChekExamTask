package models;

import java.util.ArrayList;
import java.util.List;

public class Hospital {

    private Long id;
    private String hospitalNam;
    private String address;
    private List<Department> departments = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    public Hospital() {
    }

    public Hospital(Long id, String hospitalNam, String address) {
        this.id = id;
        this.hospitalNam = hospitalNam;
        this.address = address;
    }

    public Hospital(Long id, String hospitalNam, String address, List<Department> departments, List<Doctor> doctors, List<Patient> patients) {
        this.id = id;
        this.hospitalNam = hospitalNam;
        this.address = address;
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalNam() {
        return hospitalNam;
    }

    public void setHospitalNam(String hospitalNam) {
        this.hospitalNam = hospitalNam;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "\nHospital" +
                "\nid: " + id +
                "\nHospital name: " + hospitalNam +
                "\nAddress: " + address +
                "\nDepartments: " + departments +
                "\nDoctors: " + doctors +
                "\nPatients: " + patients;
    }
}
