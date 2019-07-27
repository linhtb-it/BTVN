package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SQLHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlHelper = new SQLHelper(getBaseContext());
        try{
            sqlHelper.insertUser("linhtb","Linh@230798");
            sqlHelper.insertUser("user","Pass@123");
        }catch (Exception e){
            Toast.makeText(getBaseContext(), ".", Toast.LENGTH_SHORT).show();
        }
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
