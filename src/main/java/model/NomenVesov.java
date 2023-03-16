package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the nomen_vesov database table.
 *
 */
@Entity
@Table(name="nomen_vesov",catalog="storage",schema="dbo")
@NamedQuery(name="NomenVesov.findAll", query="SELECT n FROM NomenVesov n")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class NomenVesov implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String articul;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    private Integer edizm;

    private BigDecimal koeff;

    private String name;

    private String namefull;

    @Column(name="type_material")
    private int typeMaterial;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public NomenVesov() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticul() {
        return this.articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
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

    public Integer getEdizm() {
        if(this.edizm==null) {
            return 0 ;
        }else {
            return this.edizm;
        }

    }

    public void setEdizm(int edizm) {
        this.edizm = edizm;
    }

    public BigDecimal getKoeff() {
        return this.koeff;
    }

    public void setKoeff(BigDecimal koeff) {
        this.koeff = koeff;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamefull() {
        return this.namefull;
    }

    public void setNamefull(String namefull) {
        this.namefull = namefull;
    }

    public int getTypeMaterial() {
        return this.typeMaterial;
    }

    public void setTypeMaterial(int typeMaterial) {
        this.typeMaterial = typeMaterial;
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