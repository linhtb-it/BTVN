package com.example.login;
import android.content.Context;
public class LoginPresenter {
    ILogin iLogin;
    Context context;

    public LoginPresenter(ILogin iLogin, Context context) {
        this.iLogin = iLogin;
        this.context = context;
    }

    public  void  check(String user, String pass){

    }
    public void onLogin(String user,String pass){
            iLogin.onSuccess();
    }
}
