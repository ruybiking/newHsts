package vn.edu.fpt.hsts.bizlogic.model;

import java.util.List;

/**
 * Created by QUYHKSE61160 on 11/13/15.
 */
public class PracticeDataModel {

    private String kcalEstimate;
    private List<String> listPatientCalories;
    private List<String> listPatientDistance;
    private List<String> listPatientTime;

    public PracticeDataModel() {
    }

    public List<String> getListPatientTime() {
        return listPatientTime;
    }

    public void setListPatientTime(List<String> listPatientTime) {
        this.listPatientTime = listPatientTime;
    }

    public String getKcalEstimate() {
        return kcalEstimate;
    }

    public void setKcalEstimate(String kcalEstimate) {
        this.kcalEstimate = kcalEstimate;
    }

    public List<String> getListPatientCalories() {
        return listPatientCalories;
    }

    public void setListPatientCalories(List<String> listPatientCalories) {
        this.listPatientCalories = listPatientCalories;
    }

    public List<String> getListPatientDistance() {
        return listPatientDistance;
    }

    public void setListPatientDistance(List<String> listPatientDistance) {
        this.listPatientDistance = listPatientDistance;
    }

}
