package com.example.kkanbu.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Groupmember {
    @SerializedName("id")
    @Expose
    private GroupmemberId id;

    public GroupmemberId getId() {
        return id;
    }

    public void setId(GroupmemberId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Groupmember{" +
                "id=" + id +
                '}';
    }

    public Groupmember() {
        super();
    }
}
