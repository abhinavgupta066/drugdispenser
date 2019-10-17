package com.gstar.drugaid;

public class Patients {

    private String id;
    private String patientName1;
    private String patientNumber1;
    private String patientProblem1;
    private String patientPrescription1;
    private int patientPills1;
    private int patientTimes1;
    private double dispenser1;
    private int timeInterval1;
    private int i;
    private int hour;
    private int minute;

    public Patients(String id, String patientName1, String patientNumber1, String patientProblem1, String patientPrescription1, int patientPills1,int patientTimes1,int i,int hour,int minute, int timeInterval) {

        this.patientTimes1 = patientTimes1;
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.timeInterval1 = timeInterval;
        this.patientName1 = patientName1;
        this.patientNumber1 = patientNumber1;
        this.patientProblem1 = patientProblem1;
        this.patientPrescription1 = patientPrescription1;
        this.patientPills1 = patientPills1;
        this.dispenser1 = 0.0;
        this.i = i;
    }

    public String getPatientName1() {
        return patientName1;
    }

    public String getPatientNumber1() {
        return patientNumber1;
    }

    public String getPatientProblem1() {
        return patientProblem1;
    }

    public String getPatientPrescription1() {
        return patientPrescription1;
    }

    public int getPatientPills1() {
        return patientPills1;
    }

    public int getPatientTimes1() {
        return patientTimes1;
    }

    public double getDispenser1() {
        return dispenser1;
    }

    public int getTimeInterval1() {
        return timeInterval1;
    }

    public int getI() {
        return i;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}