package com.ek.study.serialize.protostuff;


import io.protostuff.Tag;
import lombok.Data;


@Data
public class Entity {
    @Tag(1)
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}





