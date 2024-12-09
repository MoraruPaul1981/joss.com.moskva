package model;



import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.*;


/**
 * The persistent class for the vid_tc database table.
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="materials_databinary",catalog="storage",schema="dbo")

public class MateriBinary implements Serializable {
    private static final long serialVersionUID = 1L;


    public MateriBinary() {

    }
    @Basic
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;




    /*@Type(type = "org.hibernate.type.BlobType")
    @Lob
    @Column(name = "image", nullable = true)
    protected java.sql.Blob image;*/
    @Basic
    @Lob
    @Column(name = "image", columnDefinition="varbinary(MAX)",nullable = false)
    private byte[] image;


    @Basic
    @Column(name = "files" )
    private String files;



    @Basic
    @Column(name = "date_update", nullable = true)
    private Date dateUpdate;


    @Basic
    @Column(name = "uuid", nullable = false, precision = 0)
    private BigDecimal uuid;

    @Basic
    @Column(name = "parent_uuid", nullable = true, precision = 0)
    private BigDecimal parentUuid;

    @Basic
    @Column(name = "user_update", nullable = true)
    private Integer userUpdate;

    @Basic
    @Column(name = "current_table", nullable = true, precision = 0)
    private BigDecimal currentTable;






    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public BigDecimal getUuid() {

        return uuid;
    }

    public void setUuid(BigDecimal uuid) {

        this.uuid = uuid;
    }
    public byte[] getImage() {

        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {

        this.files = files;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public BigDecimal getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(BigDecimal parentUuid) {

        this.parentUuid = parentUuid;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getCurrentTable() {

        return currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.currentTable = currentTable;
    }
}