
package com.example.raza.networkrequestmanagment.network.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable {


    private Integer depId;
    private String depName;

    private List<Object> subDeps = new ArrayList<Object>();

    /**
     * @return The depId
     */
    public Integer getDepId() {
        return depId;
    }

    /**
     * @param depId The dep_id
     */
    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    /**
     * @return The depName
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName The dep_name
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }

    /**
     * @return The subDeps
     */
    public List<Object> getSubDeps() {
        return subDeps;
    }

    /**
     * @param subDeps The sub_deps
     */
    public void setSubDeps(List<Object> subDeps) {
        this.subDeps = subDeps;
    }

}
