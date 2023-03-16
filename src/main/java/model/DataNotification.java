package model;



import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the data_notification database table.
 *
 */
@Entity
@Table(name="data_notification",catalog="storage",schema="dbo")
@NamedQuery(name="DataNotification.findAll", query="SELECT d FROM DataNotification d")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class DataNotification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer alreadyshownnotifications;

    @Column(name="callsback_note_task")
    private String callsbackNoteTask;

    private int clock;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_start")
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    @Column(name="head_message")
    private String headMessage;

    private String message;

    private int rights;

    @Column(name="status_write")
    private int statusWrite;

    @Column(name="type_tasks")
    private String typeTasks;

    private BigDecimal uuid;

    @Column(name="uuid_notifications")
    private BigDecimal uuidNotifications;

    public DataNotification() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlreadyshownnotifications() {
        if(this.alreadyshownnotifications==null) {
            return 0;
        }else {
            return this.alreadyshownnotifications;
        }


    }

    public void setAlreadyshownnotifications(int alreadyshownnotifications) {
        this.alreadyshownnotifications = alreadyshownnotifications;
    }

    public String getCallsbackNoteTask() {
        return this.callsbackNoteTask;
    }

    public void setCallsbackNoteTask(String callsbackNoteTask) {
        this.callsbackNoteTask = callsbackNoteTask;
    }

    public int getClock() {
        return this.clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public BigDecimal getCurrentTable() {
        return this.currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.currentTable = currentTable;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getHeadMessage() {
        return this.headMessage;
    }

    public void setHeadMessage(String headMessage) {
        this.headMessage = headMessage;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRights() {
        return this.rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public int getStatusWrite() {
        return this.statusWrite;
    }

    public void setStatusWrite(int statusWrite) {
        this.statusWrite = statusWrite;
    }

    public String getTypeTasks() {
        return this.typeTasks;
    }

    public void setTypeTasks(String typeTasks) {
        this.typeTasks = typeTasks;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getUuidNotifications() {
        return this.uuidNotifications;
    }

    public void setUuidNotifications(BigDecimal uuidNotifications) {
        this.uuidNotifications = uuidNotifications;
    }

}
