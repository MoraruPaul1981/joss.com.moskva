package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the fio_template database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="fio_template",catalog="storage",schema="dbo")
@NamedQuery(name="FioTemplate.findAll", query="SELECT f FROM   model. FioTemplate f")


public class FioTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private BigDecimal uuid;


    @Column(name="fio_template")
    private BigDecimal fioTemplate;

    @Column(name="fio_uuid")
    private BigDecimal fioUuid;

    @Basic
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;

    @Column(name="user_update")
    private int userUpdate;

    @Basic
    @Column(name="current_table")
    private BigDecimal currentTable;


    @Column(name="status_send")
    private String statusSend;





    public FioTemplate() {
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

    public BigDecimal getFioTemplate() {
        return this.fioTemplate;
    }

    public void setFioTemplate(BigDecimal fioTemplate) {
        this.fioTemplate = fioTemplate;
    }

    public BigDecimal getFioUuid() {
        return this.fioUuid;
    }

    public void setFioUuid(BigDecimal fioUuid) {
        this.fioUuid = fioUuid;
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

}