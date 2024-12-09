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
import  model.*;


/**
 * The persistent class for the fio database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="fio",catalog="storage",schema="dbo")
@NamedQuery(name="Fio.findAll", query="SELECT f FROM model.Fio f")



public class Fio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;


    @Basic
    @NotNull
    private String name;
    @Basic
    private String f;
    @Basic
    private String n;
    @Basic
    private String o;


    @Basic
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    @JsonProperty("BirthDate")
/*    @Column(name="BirthDate")*/
    private Date birthDate;


    @Basic
    private String snils;

    @Basic
    @Access(AccessType.PROPERTY)
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;


    @Basic
    @Access(AccessType.PROPERTY)
    @NotNull
    @Column(name="current_organization")
    private Integer currentOrganization;

    @Basic
    @Column(name="current_table")
    private BigDecimal currentTable;



    @Basic
    private Integer prof;

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
            this.birthDate = dateFormat.parse("2000-01-01");
            return this.birthDate;

        }else {
            this.birthDate = dateFormat.parse(this.birthDate.toString());
            return this.birthDate;
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
        DateFormat	dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",new Locale("ru"));
        String Дата = dateFormat.format(this.dateUpdate);
        try {
            this.dateUpdate= dateFormat.parse(Дата);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getF() {
        if (this.f==null) {
            this.f="";
        }
        return this.f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getN() {
        if (this.currentOrganization==null) {
            return this.n="";
        }
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
        if (this.o==null) {
            return this.o="";
        }
        return this.o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getSnils() {
            return this.snils;
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
    public Integer getProf() {
            return this.prof;

    }

    public void setProf(Integer prof) {
        this.prof = prof;
    }
}