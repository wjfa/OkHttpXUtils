package com.example.administrator.newsapp.okhttp;

/**
 * Created by Administrator on 2016/12/7.
 */

public class User {
    /**
     * 实体类
     *
     *  "Password":"654321"
        "UserName":"qjd",
     *
     *      //表单
             username="...."
            password="...."
     */

    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
