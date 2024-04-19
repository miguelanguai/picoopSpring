package project.picoop.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author mguaitav
 * 
 */
@Entity
@Table(name = "picoopuser")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "pswd", nullable = false)
    private String pswd;

    @Column(name = "credits", nullable = false)
    private int credits;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {

        return this.username;
    }

    /**
     * @param username new value of {@link #getUsername}.
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * @return mail
     */
    public String getMail() {

        return this.mail;
    }

    /**
     * @param mail new value of {@link #getMail}.
     */
    public void setMail(String mail) {

        this.mail = mail;
    }

    /**
     * @return pswd
     */
    public String getPswd() {
        return pswd;
    }

    /**
     * @param pswd new value of {@link #getPswd}.
     */
    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    /**
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits new value of {@link #getCredits}.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

}