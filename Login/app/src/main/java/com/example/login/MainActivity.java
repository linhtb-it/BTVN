package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment(LoginFragment.newInstance());
    }
    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }
}
