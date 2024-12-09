package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "listmacmasterssous", schema = "dbo", catalog = "storage")
public class ListmacmasterssousEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
/*  @GeneratedValue(strategy = GenerationType.AUTO)
    @TableGenerator(name="TABLE_GEN",table="T_GENERATOR",pkColumnName="GEN_KEY",pkColumnValue="TEST",valueColumnName="GEN_VALUE",initialValue=1,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
*/
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = true, length = 200)
    private String name;
    @Basic
    @Column(name = "macadress", nullable = false, length = 200)
    private String macadress;
    @Basic
    @Access(AccessType.PROPERTY)
    @Column(name = "plot", nullable = true)
    private Long plot;

    @Basic
    @Access(AccessType.PROPERTY)
    @Column(name = "date_update", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date  dateUpdate;
    @Basic
    @Column(name = "user_update", nullable = true)
    private Long userUpdate;
    @Basic
    @Column(name = "current_table", nullable = true, precision = 0)
    private BigDecimal  currentTable;
    @Basic
    @Column(name = "uuid", nullable = true, precision = 0)
    private BigDecimal  uuid;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacadress() {
        return macadress;
    }

    public void setMacadress(String macadress) {
        this.macadress = macadress;
    }


    public Long getPlot() {
        if(plot==null){
             plot=0l;
        }
        return this.plot;
    }

    public void setPlot(Long plot) {this.plot = plot;}

    public Date getDateUpdate() {
        DateFormat dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",new Locale("ru","RU"));
        String Дата = dateFormat.format(dateUpdate);
        try {
            this.dateUpdate= dateFormat.parse(Дата);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Long userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal  getCurrentTable() {

        return currentTable;
    }

    public void setCurrentTable(BigDecimal  currentTable) {

        this.currentTable = currentTable;
    }

    public BigDecimal  getUuid() {

        return uuid;
    }

    public void setUuid(BigDecimal uuid) {

        this.uuid = uuid;
    }




}
