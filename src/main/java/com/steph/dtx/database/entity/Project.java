package com.steph.dtx.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Project")
public class Project implements Serializable {

    @Id
    private String uuid;

    private String name;

    private String authorised;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthorised(String authorised) {
        this.authorised = authorised;
    }

    public String getAuthorised() {
        return authorised;
    }
}
