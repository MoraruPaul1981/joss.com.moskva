package model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "scannerserversuccess", schema = "dbo", catalog = "storage")
public class GattserversuccessEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
/*  @GeneratedValue(strategy = GenerationType.AUTO)
    @TableGenerator(name="TABLE_GEN",table="T_GENERATOR",pkColumnName="GEN_KEY",pkColumnValue="TEST",valueColumnName="GEN_VALUE",initialValue=1,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
*/

    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "operations", nullable = true, length = 255)
    private String operations;
    @Basic
    @Column(name = "completedwork", nullable = true, length = 255)
    private String completedwork;
    @Basic
    @Column(name = "namedevice", nullable = true, length = 255)
    private String namedevice;
    @Basic
    @Column(name = "macdevice", nullable = true, length = 255)
    private String macdevice;
    @Basic
    @Column(name = "gps1", nullable = true, length = 255)
    private String gps1;
    @Basic
    @Column(name = "gps2", nullable = true, length = 255)
    private String gps2;

    @Basic
    @Column(name = "fio")
    private String Fio;



    @Basic
    @Column(name = "adress", nullable = true, length = 255)
    private String adress;
    @Basic
    @Column(name = "city", nullable = true, length = 255)
    private String city;
    @Basic
    @Column(name = "date_update", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    @Basic
    @Column(name = "uuid", nullable = true, precision = 0)
    private BigDecimal uuid;
    @Basic
    @Column(name = "version", nullable = true, precision = 0)
    private Long version;
    @Basic
    @Access(AccessType.PROPERTY)
    @Column(name = "sim", nullable = true, length = 255)
    private Integer sim;

    @Basic
    @Column(name = "iemi", nullable = true, length = 255)
    private String iemi;
    @Basic
    @Column(name = "current_table", nullable = true, precision = 0)
    private BigDecimal currentTable;

    @Basic
    @Column(name = "getstatusrow", nullable = true)
    private Integer  getstatusrow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getCompletedwork() {
        return completedwork;
    }

    public void setCompletedwork(String completedwork) {
        this.completedwork = completedwork;
    }

    public String getNamedevice() {
        return namedevice;
    }

    public void setNamedevice(String namedevice) {
        this.namedevice = namedevice;
    }

    public String getMacdevice() {
        return macdevice;
    }

    public void setMacdevice(String macdevice) {
        this.macdevice = macdevice;
    }

    public String getGps1() {
        return gps1;
    }

    public void setGps1(String gps1) {
        this.gps1 = gps1;
    }

    public String getGps2() {
        return gps2;
    }

    public void setGps2(String gps2) {
        this.gps2 = gps2;
    }

    public Integer getGetstatusrow() {
        return getstatusrow;
    }

    public void setGetstatusrow(Integer getstatusrow) {
        this.getstatusrow = getstatusrow;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public BigDecimal getUuid() {
        return uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getSim() {
        if(this.sim==null) {
            return this.sim =0;
        }
        return sim;
    }

    public void setSim(Integer sim1) {
        this.sim = sim;
    }



    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        if(iemi==null){
            iemi=new String();
        }
        this.iemi = iemi;

    }

    public BigDecimal getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.currentTable = currentTable;
    }



    public void setFio(String fio) {

        this.Fio = fio;
    }

    public String getFio() {

        return Fio;
    }

}
