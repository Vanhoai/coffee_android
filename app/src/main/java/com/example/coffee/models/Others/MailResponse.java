package com.example.coffee.models.Others;

import com.example.coffee.utils.HttpResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MailResponse extends HttpResponse implements Serializable {

    @SerializedName("data")
    private Mail mail;

    public MailResponse(String message, int code, Mail mail) {
        super(message, code);
        this.mail = mail;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "MailResponse{" +
                "mail=" + mail +
                '}';
    }
}
