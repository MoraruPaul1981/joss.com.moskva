package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the depatment database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="depatment",catalog="storage",schema="dbo")
@NamedQuery(name="Depatment.findAll", query="SELECT d FROM  model. Depatment d")



public class Depatment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String name;


    private int organization;

    @Basic
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;


    @Column(name="user_update")
    private int userUpdate;

    @Basic
    @Column(name="current_table")
    private BigDecimal currentTable;


    private BigDecimal uuid;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrganization() {
        return this.organization;
    }

    public void setOrganization(int organization) {
        this.organization = organization;
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