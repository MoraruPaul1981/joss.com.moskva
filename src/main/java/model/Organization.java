package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

/**
 * The persistent class for the organization database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="organization",catalog="storage",schema="dbo")
@NamedQuery(name="Organization.findAll", query="SELECT o FROM  model. Organization o")



public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String name;


    private String fullname;


    private String inn;

    private String kpp;


    @Basic
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name="date_update", nullable = true)
    private Date dateUpdate;

    @Column(name="user_update")
    private int userUpdate;


    @Column(name="chosen_organization")
    private Integer chosenOrganization;


    @Column(name="current_table")
    private BigDecimal currentTable;


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