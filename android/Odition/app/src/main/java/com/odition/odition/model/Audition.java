package com.odition.odition.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;

@ParseClassName("Audition")
public class Audition extends ParseObject {

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getSummary() {
        return getString("summary");
    }

    public void setSummary(String summary) {
        put("summary", summary);
    }

    public String getLogo() {
        return getString("logo");
    }

    public void setLogo(String logo) {
        put("logo", logo);
    }

    public String getInstructions() {
        return getString("instructions");
    }

    public void setInstructions(String instructions) {
        put("instructions", instructions);
    }

    public String getLocation() {
        return getString("location");
    }

    public void setLocation(String location) {
        put("location", location);
    }

    public Date getApplicationDeadline() {
        return getDate("applicationDeadline");
    }

    public void setApplicationDeadline(Date applicationDeadline) {
        put("applicationDeadline", applicationDeadline);
    }

    public Date getProjectStartDate() {
        return getDate("projectStartDate");
    }

    public void setProjectStartDate(Date projectStartDate) {
        put("projectStartDate", projectStartDate);
    }

    public Date getProjectEndDate() {
        return getDate("projectEndDate");
    }

    public void setProjectEndDate(Date projectEndDate) {
        put("projectEndDate", projectEndDate);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setStatus(String status) {
        // Created, Open, Under Review, Closed
        put("status", status);
    }

    public List<String> getRoles() {
        return getList("roles");
    }

    public void setRoles(List<String> roles) {
        put("roles", roles);
    }

    public String getPayment() {
        return getString("payment");
    }

    public void setPayment(String payment) {
        put("payment", payment);
    }
}
