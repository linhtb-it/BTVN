package com.example.ad36_tranbalinh_day4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Content extends AppCompatActivity implements ItranferData__Food{
    private TextView tvLoginName;
    private static final String TAG = "Content";
    ArrayList<Food> foodList;
    ArrayList<Food> foodListTemp;
    Fragment fragment_FoodList;
    Fragment fragment_Cart;
    String loginName;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    creat_Food();
                    return true;
                case R.id.navigation_cart:
                    creat_Cart();
                    return true;
            }
            return false;
        }
    };
    public void creat_Cart(){
        ((Cart_Frag)fragment_Cart).setData(foodList);
        getFrame(fragment_Cart);
    }
    public void creat_Food(){
        if(foodListTemp == null || foodListTemp.size()<=0)
            ((FoodList_Frag)fragment_FoodList).setData(foodList);
        else
            ((FoodList_Frag)fragment_FoodList).setData(foodListTemp);
        getFrame(fragment_FoodList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        tvLoginName = findViewById(R.id.tvLoginName);
        Intent intent = getIntent();

        fragment_FoodList = new FoodList_Frag();
        fragment_Cart = new Cart_Frag();

        loginName = intent.getStringExtra("loginName");
        tvLoginName.setText("Xin Chào: "+loginName);

        foodList = new ArrayList<>();
        foodList.add(new Food("Chicken-Combo1", 10, 0, 1));
        foodList.add(new Food("Chicken-Combo2", 9, 0, 2));
        foodList.add(new Food("Potato", 12, 0, 3));
        foodList.add(new Food("Hamburger", 10, 0, 4));
        foodList.add(new Food("Chicken-Combo3", 10, 0, 5));
        foodList.add(new Food("Chicken-Combo4", 10, 0, 6));
        foodList.add(new Food("Chicken-Combo11", 10, 0, 1));
        foodList.add(new Food("Chicken-Combo21", 9, 0, 2));
        foodList.add(new Food("Potato2", 12, 0, 3));
        foodList.add(new Food("Hamburger2", 10, 0, 4));
        foodList.add(new Food("Chicken-Combo31", 10, 0, 5));
        foodList.add(new Food("Chicken-Combo41", 10, 0, 6));
        foodList.add(new Food("Chicken-Combo12", 10, 0, 1));
        foodList.add(new Food("Chicken-Combo22", 9, 0, 2));
        foodList.add(new Food("Potato32", 12, 0, 3));
        foodList.add(new Food("Hamburger3", 10, 0, 4));
        foodList.add(new Food("Chicken-Combo32", 10, 0, 5));
        foodList.add(new Food("Chicken-Combo42", 10, 0, 6));
        creat_Food();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void getFrame(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(),"Error",Toast.LENGTH_LONG).show();
            Log.d(TAG, "getFragment: "+e.getMessage());
        }
    }

    @Override
    public void sendData(ArrayList<Food> foodList) {
        this.foodListTemp = foodList;
    }

    @Override
    public void receiveData(boolean x) {
        if(x){
            creat_Cart();
        }
        else
            creat_Food();
    }
}
