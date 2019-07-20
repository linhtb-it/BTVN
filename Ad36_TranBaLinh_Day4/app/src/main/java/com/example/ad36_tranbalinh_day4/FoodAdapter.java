package com.example.ad36_tranbalinh_day4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Viewholder> {
    ArrayList<Food> foodList;
    IonClickFood ionClickFood;

    public void setIonClickFood(IonClickFood ionClickFood) {
        this.ionClickFood = ionClickFood;
    }

    public FoodAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.items_food,parent,false);
        Viewholder viewholder= new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.Viewholder holder, int position) {
        final Food food = foodList.get(position);
        holder.tvFoodName.setText(food.getFoodName());
        holder.tvFoodCost.setText(food.getFoodCost()+"$");
        switch (food.getFoodIcon()){
            case 1: holder.imgFoodPhoto.setImageResource(R.drawable.kfc_1);
                break;
            case 2: holder.imgFoodPhoto.setImageResource(R.drawable.kfc_2);
                break;
            case 3: holder.imgFoodPhoto.setImageResource(R.drawable.kfc_3);
                break;
            case 4: holder.imgFoodPhoto.setImageResource(R.drawable.kfc_4);
                break;
            case 5: holder.imgFoodPhoto.setImageResource(R.drawable.kfc_5);
                break;
            case 6: holder.imgFoodPhoto.setImageResource(R.drawable.kfc_6);
                break;
                default:holder.imgFoodPhoto.setImageResource(R.drawable.ic_photo_red_40dp);
        }
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickFood.onClickButtonAdd(food);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickFood.onClickItems(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imgFoodPhoto;
        TextView tvFoodName;
        TextView tvFoodCost;
        ImageButton btnAdd;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imgFoodPhoto = itemView.findViewById(R.id.imgfoodPhoto);
            tvFoodName = itemView.findViewById(R.id.tvfoodName);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            tvFoodCost = itemView.findViewById(R.id.tvfoodCost);
        }
    }
}