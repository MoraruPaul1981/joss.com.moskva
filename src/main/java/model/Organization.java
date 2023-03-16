package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the organization database table.
 *
 */
@Entity
@Table(name="organization",catalog="storage",schema="dbo")
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name="chosen_organization")
    private Integer chosenOrganization;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    private String fullname;

    private String inn;

    private String kpp;

    private String name;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getChosenOrganization() {
        if (this.chosenOrganization==null) {
            return 1;
        }else {
            return this.chosenOrganization;
        }
    }

    public void setChosenOrganization(Integer chosenOrganization) {
        this.chosenOrganization = chosenOrganization;
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

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getInn() {
        return this.inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Object getKpp() {
        return this.kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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