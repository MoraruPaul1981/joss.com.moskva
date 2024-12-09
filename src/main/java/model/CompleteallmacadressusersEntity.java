package model;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "completeallmacadressusers", schema = "dbo", catalog = "storage")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CompleteallmacadressusersEntity implements Serializable  {


    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
   /* @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Basic
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "fio", nullable = true, length = 60)
    private String fio;
    @Basic
    @Column(name = "mac", nullable = true, length = 100)
    private String mac;
    @Basic
    @Column(name = "date_update", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    @Basic
    @Column(name = "current_table", nullable = true, precision = 0)
    private BigDecimal currentTable;
    @Basic
    @Column(name = "uuid", nullable = true, precision = 0)
    private BigDecimal uuid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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
