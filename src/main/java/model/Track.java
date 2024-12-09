package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the track database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="track",catalog="storage",schema="dbo")
@NamedQuery(name="Track.findAll", query="SELECT t FROM    model. Track t")
public class Track implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;



    @Basic
    @Access(AccessType.PROPERTY)
    private String name;

    private String fullname;

    @Basic
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;

    @Column(name="user_update")
    private Integer userUpdate;


    private Integer dir;


    private BigDecimal uuid;

    @Column(name="current_table")
    private BigDecimal currentTable;



    @Basic
    @Access(AccessType.PROPERTY)
    private Integer owner;


    @Basic
    @Access(AccessType.PROPERTY)
    private Integer vid_tc;



    public Track() {
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

    public int getDir() {
        return this.dir;
    }

    public void setDir(Integer dir) {
        this.dir = dir;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        if (this.name==null) {
            return this.name="";
        }
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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


    public Integer getOwner() {
        if (this.owner==null) {
            return this.owner=0;
        }
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }



    public Integer getVid_tc() {
        if (this.vid_tc==null) {
            return this.vid_tc=1;
        }
        return vid_tc;
    }

    public void setVid_tc(Integer vid_tc) {
        this.vid_tc = vid_tc;
    }

}