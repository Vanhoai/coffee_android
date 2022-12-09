package com.example.coffee.interfaces;

import com.example.coffee.models.Others.MailResponse;

public interface MailCallback {

    public void onSuccess(MailResponse mailResponse);
    public void onFailed(boolean failed);

}
