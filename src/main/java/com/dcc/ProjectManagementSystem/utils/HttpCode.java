package com.dcc.ProjectManagementSystem.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpCode {
    public static int getHttpCode(String url) throws IOException {
            URL u = new URL(url);

            HttpURLConnection uConnection = (HttpURLConnection) u.openConnection();

            uConnection.connect();

            return uConnection.getResponseCode();

    }
}
