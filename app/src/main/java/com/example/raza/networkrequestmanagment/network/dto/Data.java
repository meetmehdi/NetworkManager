
package com.example.raza.networkrequestmanagment.network.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {


    private List<Department> departments = new ArrayList<Department>();

    private String subscription;

    /**
     * @return The departments
     */
    public List<Department> getDepartments() {
        return departments;
    }

    /**
     * @param departments The departments
     */
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    /**
     * @return The subscription
     */
    public String getSubscription() {
        return subscription;
    }

    /**
     * @param subscription The subscription
     */
    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

}
