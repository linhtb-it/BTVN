package com.example.login;
import android.content.Context;

import java.util.List;

public class LoginPresenter {
    ILogin iLogin;
    Context context;
    List<User> users;

    public LoginPresenter(ILogin iLogin, Context context) {
        this.iLogin = iLogin;
        this.context = context;
    }

    public  boolean  check(String user, String pass){
        SQLHelper sqlHelper = new SQLHelper(context);
        users = sqlHelper.getAllUser();
        for( int i=0; i< users.size(); i++){
            if (users.get(i).getUser().equals(user) && users.get(i).getPassWord().equals(pass) ){
                return true;
            }
        }
        return false;
    }
    public void onLogin(String user,String pass){
        if(check(user,pass))
            iLogin.onSuccess();
        else
            iLogin.onMessenger(pass);
    }
}
