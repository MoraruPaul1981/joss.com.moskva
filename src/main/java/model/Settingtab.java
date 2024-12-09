package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import javax.ejb.Lock;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the settings_tabels database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="settings_tabels",catalog="storage",schema="dbo")
@NamedQuery(name="Settingtab.findAll", query="SELECT s FROM   model. Settingtab s")


public class Settingtab implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;

    @Column(name="user_update")
    private Integer userUpdate;

    @Column(name="version_dsu1")
    private Integer versionDsu1;


    private Integer organizations;

    private BigDecimal uuid;

    @Column(name="current_table")
    private BigDecimal currentTable;


    private String onesignal;





    public Settingtab() {
    }

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

    public String getOnesignal() {
        return this.onesignal;
    }

    public void setOnesignal(String onesignal) {
        this.onesignal = onesignal;
    }

    public Integer getOrganizations() {
        return this.organizations;
    }

    public void setOrganizations(Integer organizations) {
        this.organizations = organizations;
    }

    public Integer getUserUpdate() {
        return this.userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

    public Integer getVersionDsu1() {
        return this.versionDsu1;
    }

    public void setVersionDsu1(Integer versionDsu1) {
        this.versionDsu1 = versionDsu1;
    }

}