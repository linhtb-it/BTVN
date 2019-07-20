package com.example.ad36_tranbalinh_day4;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cart_Frag extends Fragment {
    ArrayList<Food> foodList;
    RecyclerView rvFoodList;
    CartAdapter cartAdapter;
    TextView tvPrince;
    ImageView imgBack;
    ItranferData__Food itranferData__food;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public void setData(ArrayList<Food> foodList){
        this.foodList = foodList;
    }
    public double totalPrince() {
        double total=0;
        int size = foodList.size();
        for (int i = 0; i < size; i++) {
            total += foodList.get(i).getPrince();
        }
        return total;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment,container,false);
        rvFoodList = view.findViewById(R.id.rvFoodList);
        tvPrince = view.findViewById(R.id.tvTotalPrince);
        ArrayList<Food> foodTemp = new ArrayList<>();
        imgBack = view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itranferData__food.receiveData(false);
            }
        });
        for(int i=0; i<foodList.size(); i++){
            if (foodList.get(i).getFoodCount()>0)
                foodTemp.add(foodList.get(i));
        }
        tvPrince.setText(totalPrince()+" $");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
        cartAdapter = new CartAdapter(foodTemp);
        rvFoodList.setLayoutManager(layoutManager);
        rvFoodList.setAdapter(cartAdapter);

        return view;
    }

}
