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
 * The persistent class for the view_onesignal database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="view_onesignal",catalog="storage",schema="dbo")
@NamedQuery(name="ViewOnesignal.findAll", query="SELECT v FROM  model. ViewOnesignal v")



public class ViewOnesignal implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private Integer id;


    private BigDecimal uuid;

    private String onesignal;


    @Column(name="current_table")
    private BigDecimal currentTable;

    @Column(name="user_update")
    private int userUpdate;

    @Basic
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;








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