package com.example.btvn_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_json.adapter.WeatherAdapter;
import com.example.btvn_json.databinding.ActivityMainBinding;
import com.example.btvn_json.entity.Climate;
import com.example.btvn_json.entity.Clouds;
import com.example.btvn_json.entity.Coord;
import com.example.btvn_json.entity.Main;
import com.example.btvn_json.entity.Sys;
import com.example.btvn_json.entity.Weather;
import com.example.btvn_json.entity.Wind;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String json_String = " { \"coord\": { \"lon\": 139,\"lat\": 35}, \"weather\": [ { \"id\": 800, \"main\": \"Clear\", \"description\": \"clear sky\", \"icon\": \"01n\" } ], \"base\": \"stations\", \"main\": { \"temp\": 289.92, \"pressure\": 1009, \"humidity\": 92, \"temp_min\": 288.71, \"temp_max\": 290.93 }, \"wind\": { \"speed\": 0.47, \"deg\": 107.538 }, \"clouds\": { \"all\": 2 }, \"dt\": 1560350192, \"sys\": { \"type\": 3, \"id\": 2019346, \"message\": 0.0065, \"country\": \"JP\", \"sunrise\": 1560281377, \"sunset\": 1560333478 }, \"timezone\": 32400, \"id\": 1851632, \"name\": \"Shuzenji\", \"cod\": 200 }";
    ActivityMainBinding binding;
    WeatherAdapter weatherAdapter;

    public int ConvertKenvinToFahrenheit(double kenvin){
        return (int)((kenvin-237.5)*1.8) +32;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Climate climate_OBJ;
        Coord coord_OBJ;
        List<Weather> weather_OBJs = new ArrayList<>();
        Main main_OBJ;
        Wind wind_OBJ;
        Clouds clouds_OBJ;
        Sys sys_OBJ;

        try{
            JSONObject climate = new JSONObject(json_String);

            JSONObject coord = climate.getJSONObject("coord");
                int coord_lon = coord.getInt("lon");
                int coord_lat = coord.getInt("lat");
                coord_OBJ = new Coord(coord_lon,coord_lat);

            JSONArray weathers = climate.getJSONArray("weather");
            for(int i=0; i< weathers.length();i++){
                JSONObject weather = weathers.getJSONObject(i);
                String weather_id = weather.getString("id");
                String weather_main = weather.getString("main");
                String weather_des = weather.getString("description");
                String weather_icon = weather.getString("icon");
                weather_OBJs.add(new Weather(weather_id,weather_main,weather_des,weather_icon));
            }

            String base = climate.getString("base");
            JSONObject main = climate.getJSONObject("main");
                double main_temp = main.getDouble("temp");
                long main_pressure = main.getLong("pressure");
                long main_humidity = main.getLong("humidity");
                double main_temp_min = main.getDouble("temp_min");
                double main_temp_max = main.getDouble("temp_max");
                main_OBJ = new Main(main_temp,main_pressure,main_humidity,main_temp_min,main_temp_max);


            JSONObject wind = climate.getJSONObject("wind");
                double wind_speed = wind.getDouble("speed");
                double wind_deg = wind.getDouble("deg");
                wind_OBJ = new Wind(wind_speed,wind_deg);

            JSONObject clouds = climate.getJSONObject("clouds");
                    int clouds_all = clouds.getInt("all");
                    clouds_OBJ = new Clouds(clouds_all);

            String dt = climate.getString("dt");

            JSONObject sys =climate.getJSONObject("sys");
                int sys_type = sys.getInt("type");
                String sys_id = sys.getString("id");
                String sys_message = sys.getString("message");
                String sys_country = sys.getString("country");
                String sys_sunrise = sys.getString("sunrise");
                String sys_sunset = sys.getString("sunset");
                sys_OBJ = new Sys(sys_type,sys_id, sys_message,sys_country,sys_sunrise,sys_sunset);

            String timeZone = climate.getString("timezone");
            String id = climate.getString("id");
            String mane = climate.getString("name");
            String cod = climate.getString("cod");

            climate_OBJ = new Climate(coord_OBJ,weather_OBJs,base,main_OBJ,wind_OBJ,clouds_OBJ,dt,sys_OBJ,timeZone,id,mane,cod);

            weatherAdapter = new WeatherAdapter(weather_OBJs);

            binding.tvName.setText(climate_OBJ.getName());
            binding.tvTemp.setText("Temperature: "+ConvertKenvinToFahrenheit(main_OBJ.getTemp()) +" F");
            binding.tvHumidity.setText("Humidity: "+main_OBJ.getHumidity()+ " %");
            binding.tvWindSpeed.setText("Wind speed: "+wind_OBJ.getSpeed()+" m/s");

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(),1,RecyclerView.VERTICAL,false);
            binding.rvWeather.setLayoutManager(layoutManager);
            binding.rvWeather.setAdapter(weatherAdapter);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
