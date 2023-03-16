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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the fio database table.
 *
 */
@Entity
@Table(name="fio",catalog="storage",schema="dbo")
@NamedQuery(name="Fio.findAll", query="SELECT f FROM Fio f")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class Fio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone= "Europe/Moscow")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("BirthDate")
    @Column(name="BirthDate")
    private Date birthDate;



    @NotNull
    @Column(name="current_organization")
    private Integer currentOrganization;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    private String f;

    private String n;

    private String name;

    private String o;

    private String snils;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public Fio() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() throws ParseException {
        DateFormat	dateFormat =   new SimpleDateFormat("yyyy-MM-dd",new Locale("ru"));
        if ( this.birthDate==null) {
            Date date = dateFormat.parse("1900-01-01");
            return date;

        }else {
            Date date = dateFormat.parse(this.birthDate.toString());
            return date;
        }
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getCurrentOrganization() {
        if (this.currentOrganization==null) {
            return 1;
        }else {
            return this.currentOrganization;
        }
    }

    public void setCurrentOrganization(int currentOrganization) {
        this.currentOrganization = currentOrganization;
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

    public String getF() {
        return this.f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getN() {
        return this.n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getO() {
        return this.o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getSnils() {
        if (this.snils==null) {
            return " ";
        }else {
            return this.snils;
        }
    }

    public void setSnils(String snils) {
        this.snils = snils;
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