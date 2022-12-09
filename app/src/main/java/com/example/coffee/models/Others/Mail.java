package com.example.coffee.models.Others;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Mail implements Serializable {

    @SerializedName("accepted")
    ArrayList<String> accepted;

    @SerializedName("rejected")
    ArrayList<String> rejected;


    public Mail(ArrayList<String> accepted, ArrayList<String> rejected) {
        this.accepted = accepted;
        this.rejected = rejected;
    }

    public ArrayList<String> getAccepted() {
        return accepted;
    }

    public void setAccepted(ArrayList<String> accepted) {
        this.accepted = accepted;
    }

    public ArrayList<String> getRejected() {
        return rejected;
    }

    public void setRejected(ArrayList<String> rejected) {
        this.rejected = rejected;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "accepted=" + accepted +
                ", rejected=" + rejected +
                '}';
    }
}
