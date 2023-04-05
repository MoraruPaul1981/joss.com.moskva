package model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users", schema = "dbo", catalog = "storage")
@DynamicUpdate
@OptimisticLocking(type=OptimisticLockType.DIRTY)
public class UsersEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Transient
    @Basic
    @Column(name = "fio", nullable = false)
    private int fio;

    @Transient
    @Basic
    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Transient
    @Basic
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Basic
    @Column(name = "rights", nullable = false)
    private int rights;
    @Basic
    @Column(name = "locked", nullable = true)
    private Boolean locked;

    @Transient
    @Basic
    @Column(name = "telephone", nullable = true, length = 255)
    private String telephone;
    @Basic
    @Column(name = "date_update", nullable = true)
    private Date dateUpdate;
    @Transient
    @Basic
    @Column(name = "regoin", nullable = true)
    private Integer regoin;
    @Transient
    @Basic
    @Column(name = "current_table", nullable = true, precision = 0)
    private Long currentTable;
    @Transient
    @Basic
    @Column(name = "baza", nullable = true, length = 50)
    private String baza;
    @Transient
    @Basic
    @Column(name = "kod", nullable = true, length = 255)
    private String kod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFio() {
        return fio;
    }

    public void setFio(int fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getRegoin() {
        return regoin;
    }

    public void setRegoin(Integer regoin) {
        this.regoin = regoin;
    }

    public Long getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Long currentTable) {
        this.currentTable = currentTable;
    }

    public String getBaza() {
        return baza;
    }

    public void setBaza(String baza) {
        this.baza = baza;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (fio != that.fio) return false;
        if (rights != that.rights) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (locked != null ? !locked.equals(that.locked) : that.locked != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (dateUpdate != null ? !dateUpdate.equals(that.dateUpdate) : that.dateUpdate != null) return false;
        if (regoin != null ? !regoin.equals(that.regoin) : that.regoin != null) return false;
        if (currentTable != null ? !currentTable.equals(that.currentTable) : that.currentTable != null) return false;
        if (baza != null ? !baza.equals(that.baza) : that.baza != null) return false;
        if (kod != null ? !kod.equals(that.kod) : that.kod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fio;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + rights;
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (dateUpdate != null ? dateUpdate.hashCode() : 0);
        result = 31 * result + (regoin != null ? regoin.hashCode() : 0);
        result = 31 * result + (currentTable != null ? currentTable.hashCode() : 0);
        result = 31 * result + (baza != null ? baza.hashCode() : 0);
        result = 31 * result + (kod != null ? kod.hashCode() : 0);
        return result;
    }
}
