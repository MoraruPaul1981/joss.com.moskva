package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the get_materials_data database table.
 *
 */
@Entity
@Table(name="get_materials_data",catalog="storage",schema="dbo")
@NamedQuery(name="GetMaterialsData.findAll", query="SELECT g FROM GetMaterialsData g")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class GetMaterialsData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private int cfo;

    private Integer companys;

    private BigDecimal count;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datattn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    @Column(name="nomen_vesov")
    private int nomenVesov;

    @Column(name="status_send")
    private String statusSend;


    @ColumnDefault("0")
    private Integer tracks;

    private String ttn;

    @Column(name="type_material")
    private int typeMaterial;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public GetMaterialsData() {
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

    public Integer getCompanys() {
        if(this.companys==null) {
            return 0;
        }else {
            return this.companys;
        }


    }

    public void setCompanys(int companys) {
        this.companys = companys;
    }

    public BigDecimal getCount() {
        return this.count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCurrentTable() {
        return this.currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.currentTable = currentTable;
    }

    public Date getDatattn() {
        return this.datattn;
    }

    public void setDatattn(Date datattn) {
        this.datattn = datattn;
    }

    public Date getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public int getNomenVesov() {
        return this.nomenVesov;
    }

    public void setNomenVesov(int nomenVesov) {
        this.nomenVesov = nomenVesov;
    }

    public String getStatusSend() {
        return this.statusSend;
    }

    public void setStatusSend(String statusSend) {
        this.statusSend = statusSend;
    }

    public Integer getTracks() {
        return this.tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public String getTtn() {
        return this.ttn;
    }

    public void setTtn(String ttn) {
        this.ttn = ttn;
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