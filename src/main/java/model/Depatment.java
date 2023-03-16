package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the depatment database table.
 *
 */
@Entity
@Table(name="depatment",catalog="storage",schema="dbo")
@NamedQuery(name="Depatment.findAll", query="SELECT d FROM Depatment d")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class Depatment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    private String name;

    private int organization;

    @Column(name="user_update")
    private int userUpdate;

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