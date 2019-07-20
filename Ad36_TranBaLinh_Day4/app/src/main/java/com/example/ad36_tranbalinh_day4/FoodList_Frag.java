package com.example.ad36_tranbalinh_day4;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodList_Frag extends Fragment {
    ArrayList<Food> foodList;
    RecyclerView rvFoodList;
    FoodAdapter foodAdapter;
    TextView tvTotalPrince;
    Button btnOrder;
    ItranferData__Food itranferData__food;
    TextView tvTempCount;
    ImageView imgCart;

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
    public double tempCount() {
        double count=0;
        int size = foodList.size();
        for (int i = 0; i < size; i++) {
            count += foodList.get(i).getFoodCount();
        }
        return count;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Content){
            this.itranferData__food = (ItranferData__Food) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement onViewSelected!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_food_fragment, container, false);

        rvFoodList = view.findViewById(R.id.rvFoodList);
        tvTotalPrince = view.findViewById(R.id.tvTotalPrince);
        tvTotalPrince.setText(totalPrince()+" $");
        btnOrder = view.findViewById(R.id.btnOrder);
        tvTempCount = view.findViewById(R.id.tvTempCount);
        tvTempCount.setText(tempCount()+"");
        imgCart = view.findViewById(R.id.imgOrder);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itranferData__food.receiveData(true);
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
        foodAdapter = new FoodAdapter(foodList);
        rvFoodList.setLayoutManager(layoutManager);
        rvFoodList.setAdapter(foodAdapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i<foodList.size();i++){
                    foodList.get(i).setFoodCount(0);
                }
                itranferData__food.sendData(foodList);
                tvTotalPrince.setText(totalPrince()+" $");
                Toast.makeText(getContext(), "Gọi món thành công !", Toast.LENGTH_SHORT).show();
            }
        });

        foodAdapter.setIonClickFood(new IonClickFood() {
            @Override
            public void onClickButtonAdd(Food food) {
                food.setFoodCount(food.getFoodCount() + 1);
                Toast.makeText(getContext(), "Đã thêm " + food.getFoodName() + " vào giỏ", Toast.LENGTH_SHORT).show();
                tvTotalPrince.setText(totalPrince()+" $");
                tvTempCount.setText(tempCount()+"");
                itranferData__food.sendData(foodList);
            }

            @Override
            public void onClickItems(Food food) {
                food.setFoodCount(food.getFoodCount() + 1);
                Toast.makeText(getContext(), "Đã thêm " + food.getFoodName() + " vào giỏ", Toast.LENGTH_SHORT).show();
                tvTotalPrince.setText(totalPrince()+" $");
                tvTempCount.setText(tempCount()+"");
                itranferData__food.sendData(foodList);
            }
        });
        return view;
    }
}
