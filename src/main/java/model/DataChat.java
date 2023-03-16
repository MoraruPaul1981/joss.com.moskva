package model;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the data_chat database table.
 *
 */
/**
 * @author moraru_pi
 *
 */
@Entity
@Table(name = "data_chat", catalog = "storage", schema = "dbo")
@NamedQuery(name = "DataChat.findAll", query = "SELECT d FROM DataChat d")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class DataChat implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer alreadyshownnotifications;

    @Column(name = "chat_uuid")
    private BigDecimal chatUuid;

    @Column(name = "current_table")
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "image_chat")
    private String imageChat;

    private String message;

    @Column(name = "status_write")
    private boolean statusWrite;

    @Column(name = "user_update")
    private int userUpdate;

    private BigDecimal uuid;

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

    public String getImageChat() {
        return this.imageChat;
    }

    public void setImageChat(String imageChat) {
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
