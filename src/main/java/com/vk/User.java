/**
 * User.java
 */
package com.vk;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Entity class for User
 */
@Data
@Entity
public class User {
    private @Id @GeneratedValue long id;
    @NotNull
    @Size(min=2, max=30)
    @Pattern(regexp = "^[a-zA-Z0-9.\\-/+=@_ &]*$")
    private String givenName;

    @NotNull
    @Size(min=2, max=30)
    @Pattern(regexp = "^[a-zA-Z0-9.\\-/+=@_ &]*$")
    private String familyName;

    @Email
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.\\-/+=@_ &]*$")
    private String email;
    private Date created;

    public User() {
        this.created = new Date();
    }

    public User(String givenName, String familyName, String email) {
        this.email = email;
        this.givenName = givenName;
        this.familyName = familyName;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }


    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
