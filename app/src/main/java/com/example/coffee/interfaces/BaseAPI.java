package com.example.coffee.interfaces;

import com.example.coffee.app.Constants;

public interface BaseAPI {
    
    String BASE_URL = Constants.DEBUG ? String.format("%s%s", Constants.BASE_URL_LOCAL, Constants.API_VERSION) : String.format("%s%s", Constants.BASE_URL, Constants.API_VERSION);

}
