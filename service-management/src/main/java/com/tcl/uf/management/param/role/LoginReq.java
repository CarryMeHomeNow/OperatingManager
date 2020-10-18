package com.tcl.uf.management.param.role;

public class LoginReq {
    private String username;
    private String password;
    private String verCode;
    private String verKey;

    public String getVerKey() {
        return verKey;
    }

    public void setVerKey(String verKey) {
        this.verKey = verKey;
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

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }
}
