package com.upgrad.quora.service.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_auth", schema = "public", catalog = "quora")
public class UserAuthEntity {
    private long id;
    private String uuid;
    private String accessToken;
    private Timestamp expiresAt;
    private Timestamp loginAt;
    private Timestamp logoutAt;
    private UsersEntity usersByUserId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uuid", nullable = false, length = 200)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "access_token", nullable = false, length = 500)
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Basic
    @Column(name = "expires_at", nullable = false)
    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Basic
    @Column(name = "login_at", nullable = false)
    public Timestamp getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Timestamp loginAt) {
        this.loginAt = loginAt;
    }

    @Basic
    @Column(name = "logout_at", nullable = true)
    public Timestamp getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(Timestamp logoutAt) {
        this.logoutAt = logoutAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthEntity that = (UserAuthEntity) o;

        if (id != that.id) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (expiresAt != null ? !expiresAt.equals(that.expiresAt) : that.expiresAt != null) return false;
        if (loginAt != null ? !loginAt.equals(that.loginAt) : that.loginAt != null) return false;
        if (logoutAt != null ? !logoutAt.equals(that.logoutAt) : that.logoutAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (expiresAt != null ? expiresAt.hashCode() : 0);
        result = 31 * result + (loginAt != null ? loginAt.hashCode() : 0);
        result = 31 * result + (logoutAt != null ? logoutAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
