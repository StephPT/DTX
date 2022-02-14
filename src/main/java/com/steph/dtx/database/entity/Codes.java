package com.steph.dtx.database.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Codes")
public class Codes implements Serializable {

    @Id
    private String uuid;

    private int code;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "projectUuid")
    private Project project;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
