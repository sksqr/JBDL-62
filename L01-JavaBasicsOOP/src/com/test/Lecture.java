package com.test;

import java.util.Date;

public class Lecture {

    static Integer numberOfObjects = 0;

    static
    {
        System.out.println("Executing static block");
    }
    private String name;
    private Date createdDate;
    private String status;
    private String mentor;

    public Lecture(String name, Date createdDate, String status, String mentor) {
        numberOfObjects++;
        this.name = name;
        this.createdDate = createdDate;
        this.status = status;
        this.mentor = mentor;
    }

    public Lecture() {
        numberOfObjects++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        //
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", status='" + status + '\'' +
                ", mentor='" + mentor + '\'' +
                '}';
    }


}
