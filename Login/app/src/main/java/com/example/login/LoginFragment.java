package com.example.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.example.login.databinding.LoginFragmentBinding;

import java.util.zip.Inflater;

public class LoginFragment extends Fragment implements ILogin {
    LoginFragmentBinding binding;
    LoginPresenter presenter;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.login_fragment,container,false);
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        presenter = new LoginPresenter(this, getContext());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogin()) {
                    presenter.onLogin(binding.etUser.getText().toString(), binding.etPass.getText().toString());
                }
            }
        });
        return binding.getRoot();
    }

    void focusPass() {
        binding.etPass.requestFocus();
        binding.etPass.setError(getResources().getString(R.string.error_pass));
    }

    void focusUser() {
        binding.etUser.requestFocus();
        binding.etUser.setError(getResources().getString(R.string.error_user));
    }

    private boolean validateLogin() {
        boolean check = false;
        if (TextUtils.isEmpty(binding.etUser.getText().toString())) {
            check = false;
            focusUser();
            return check;
        }

        if (TextUtils.isEmpty(binding.etPass.getText().toString())) {
            check = false;
            focusPass();
            return check;
        }
        String temp = binding.etPass.getText().toString();
        if (temp.length() < 6) {
            focusPass();
            return check;
        }
        for (int i = 0; i < temp.length(); i++) {
            if ((int) temp.charAt(i) > 64 && (int) temp.charAt(i) < 91) {
                check = true;
                break;
            } else {
                check = false;
                if (i >= temp.length()) {
                    focusPass();
                    return check;
                }
            }

        }
        for (int i = 0; i < temp.length(); i++) {
            if ((int) temp.charAt(i) > 47 && (int) temp.charAt(i) < 58) {
                check = true;
                break;
            } else {
                check = false;
                if (i >= temp.length()) {
                    focusPass();
                    return check;
                }
            }
        }
        for (int i = 0; i < temp.length(); i++) {
            if ((int) temp.charAt(i) > 32 && (int) temp.charAt(i) < 48) {
                check = true;
                break;
            } else {
                check = false;
                if (i >= temp.length()) {
                    focusPass();
                    return check;
                }
            }
        }
        for (int i = 0; i < temp.length(); i++) {
            if ((int) temp.charAt(i) > 57 && (int) temp.charAt(i) < 65) {
                check = true;
                break;
            } else {
                check = false;
                if (i >= temp.length()) {
                    focusPass();
                    return check;
                }
            }
        }
        if(check == false){
            focusPass();
        }
        return check;
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessenger(String mess) {
        Toast.makeText(getContext(), "Đăng nhập thất bại"+mess, Toast.LENGTH_LONG).show();
    }
}
