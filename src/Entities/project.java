/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author marwe
 */
public class project {
    int id ;
    int project_user_id;
    String title;
    String description;
    Date creat_at;
    int status;
    int domain_id_id;

    public project() {
    }

    @Override
    public String toString() {
        return "project{" + "id=" + id + ", project_user_id=" + project_user_id + ", title=" + title + ", description=" + description + ", creat_at=" + creat_at + ", status=" + status + ", domain_id_id=" + domain_id_id + '}';
    }

    public project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id) {
        this.id = id;
        this.project_user_id = project_user_id;
        this.title = title;
        this.description = description;
        this.creat_at = creat_at;
        this.status = status;
        this.domain_id_id = domain_id_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_user_id() {
        return project_user_id;
    }

    public void setProject_user_id(int project_user_id) {
        this.project_user_id = project_user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreat_at() {
        return creat_at;
    }

    public void setCreat_at(Date creat_at) {
        this.creat_at = creat_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDomain_id_id() {
        return domain_id_id;
    }

    public void setDomain_id_id(int domain_id_id) {
        this.domain_id_id = domain_id_id;
    }
}
