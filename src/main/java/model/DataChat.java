package model;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the data_chat database table.
 *
 */
/**
 * @author moraru_pi
 *
 */
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "data_chat", catalog = "storage", schema = "dbo")
@NamedQuery(name = "DataChat.findAll", query = "SELECT d FROM   model.DataChat d")

public class DataChat implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private BigDecimal uuid;

    private String message;


    @Basic
    @Lob
    @Column(name = "image_chat", columnDefinition="varbinary(MAX)",nullable = false)
    private byte[] imageChat;





    @Column(name = "status_write")
    private boolean statusWrite;


    @Column(name = "chat_uuid")
    private BigDecimal chatUuid;

    @Column(name = "user_update")
    private int userUpdate;

    @Basic
    @Column(name = "date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;

    @Basic
    @Column(name = "current_table")
    private BigDecimal currentTable;


    private Integer alreadyshownnotifications;



    public DataChat() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlreadyshownnotifications() {
        return this.alreadyshownnotifications;
    }

    public void setAlreadyshownnotifications(Integer alreadyshownnotifications) {
        this.alreadyshownnotifications = alreadyshownnotifications;
    }

    public BigDecimal getChatUuid() {
        return this.chatUuid;
    }

    public void setChatUuid(BigDecimal chatUuid) {
        this.chatUuid = chatUuid;
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

    public byte[] getImageChat() {
        return this.imageChat;
    }

    public void setImageChat(byte[] imageChat) {
        this.imageChat = imageChat;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatusWrite() {
        return this.statusWrite;
    }

    public void setStatusWrite(boolean statusWrite) {
        this.statusWrite = statusWrite;
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
    // TODO mappimg

}
