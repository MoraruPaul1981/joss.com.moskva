package model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "prof", schema = "dbo", catalog = "storage")
@NamedQuery(name="Prof.findAll", query="SELECT pr FROM Prof pr")
@DynamicUpdate
@OptimisticLocking(type=OptimisticLockType.DIRTY)


public class Prof implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "user_update", nullable = false)
    private int userUpdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update", nullable = false)
    private Date dateUpdate;

    @Column(name = "current_table", nullable = true, precision = 0)
    private BigDecimal currentTable;

    @Column(name = "uuid", nullable = true, precision = 0)
    private BigDecimal uuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public BigDecimal getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {

        this.currentTable = currentTable;
    }

    public BigDecimal getUuid() {
        return uuid;
    }

    public void setUuid(BigDecimal uuid) {

        this.uuid = uuid;
    }


}
