package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OptimisticLockType;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * The persistent class for the get_materials_data database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="get_materials_data",catalog="storage",schema="dbo")
@NamedQuery(name="GetMaterialsData.findAll", query="SELECT g FROM  model. GetMaterialsData g")


public class GetMaterialsData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;


    @Column(name="type_material")
    private int typeMaterial;


    @Column(name="nomen_vesov")
    private int nomenVesov;


    @ColumnDefault("0")
    private Integer tracks;

    private Integer companys;

    private BigDecimal count;


    @Basic
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    @Column(name="date_update", nullable = true)
    private Date dateUpdate;

    private BigDecimal uuid;

    @Column(name="user_update")
    private int userUpdate;

    private int cfo;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Column(name="status_send")
    private String statusSend;


    private String ttn;

    @Basic
    @Access(AccessType.PROPERTY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date datattn;














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
        if(this.datattn==null){
            DateFormat dateFormat =   new SimpleDateFormat("yyyy-MM-dd",new Locale("ru","RU"));
            Date datebuffer=null;
            try {
                datebuffer= dateFormat.parse("2000-01-01");
                this.datattn=datebuffer;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
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