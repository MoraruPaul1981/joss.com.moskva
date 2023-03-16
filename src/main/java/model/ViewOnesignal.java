package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the view_onesignal database table.
 *
 */
@Entity
@Table(name="view_onesignal",catalog="storage",schema="dbo")
@NamedQuery(name="ViewOnesignal.findAll", query="SELECT v FROM ViewOnesignal v")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class ViewOnesignal implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private Integer id;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;


    private String onesignal;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public ViewOnesignal() {
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOnesignal() {
        return this.onesignal;
    }

    public void setOnesignal(String onesignal) {
        this.onesignal = onesignal;
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