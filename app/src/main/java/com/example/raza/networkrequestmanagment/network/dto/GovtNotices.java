
package com.example.raza.networkrequestmanagment.network.dto;

import java.io.Serializable;

public class GovtNotices implements Serializable {

    private Boolean status;
    private Integer statusCode;
    private Data data;

    /**
     * @return The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode The status_code
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return The data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}
