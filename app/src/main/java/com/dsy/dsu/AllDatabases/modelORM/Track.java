
package com.dsy.dsu.AllDatabases.modelORM;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the track database table.
 *
 */


public class Track implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    @JsonProperty("current_table")
    private BigDecimal currentTable;


    @JsonProperty("date_update")
    private Date dateUpdate;

    private int dir;

    private String fullname;

    private String name;


    @JsonProperty("user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public Track() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCurrentTable() {
        return this.currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.currentTable = currentTable;
    }

    public Date getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public int getDir() {
        return this.dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserUpdate() {
        return this.userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

}