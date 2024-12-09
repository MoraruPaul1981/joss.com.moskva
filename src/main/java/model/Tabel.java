package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tabel database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="tabel",catalog="storage",schema="dbo")
@NamedQuery(name="Tabel.findAll", query="SELECT t FROM   model. Tabel t")



public class Tabel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;


    private int cfo;


    @Column(name="month_tabels")
    private int monthTabels;


    @Column(name="year_tabels")
    private int yearTabels;

    @Basic
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;


    private BigDecimal uuid;

    @Column(name="status_send")
    private String statusSend;




    @Column(name="user_update")
    private int userUpdate;


    @Column(name="current_table")
    private BigDecimal currentTable;






    public Tabel() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCfo() {
        return this.cfo;
    }

    public void setCfo(int cfo) {
        this.cfo = cfo;
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

    public int getMonthTabels() {
        return this.monthTabels;
    }

    public void setMonthTabels(int monthTabels) {
        this.monthTabels = monthTabels;
    }

    public String getStatusSend() {

        return this.statusSend;
    }

    public void setStatusSend(String statusSend) {

        this.statusSend = statusSend;
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

    public int getYearTabels() {
        return this.yearTabels;
    }

    public void setYearTabels(int yearTabels) {
        this.yearTabels = yearTabels;
    }

}