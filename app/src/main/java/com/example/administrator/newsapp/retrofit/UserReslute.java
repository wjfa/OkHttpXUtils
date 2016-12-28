package com.example.administrator.newsapp.retrofit;

/**
 * Created by Administrator on 2016/12/7.
 */

public class UserReslute {
    /**
     * User的父类
     *
     *  "errcode": 1,                  //状态值
        "errmsg": "注册成功！",        //返回信息
        "tokenid": 171                 //用户令牌
     *
     */
    public int errcode;//状态值
    public String errmsg;//返回信息
    public int tokenid;//用户令牌

    public UserReslute(int errcode, String errmsg, int tokenid) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.tokenid = tokenid;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getTokenid() {
        return tokenid;
    }

    public void setTokenid(int tokenid) {
        this.tokenid = tokenid;
    }
}
