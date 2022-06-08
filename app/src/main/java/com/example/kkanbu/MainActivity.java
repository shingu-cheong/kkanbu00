package com.example.kkanbu;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.kkanbu.thread.GetHuman;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class MainActivity extends Fragment {
    private MyMqtt myMqtt;
    private ImageButton btn_live, btn_schedule;
    private ImageView profile, present;
    private GetHuman getHuman;
    private int humancount, humancountpre;
    Object SomethingResult;
    private Mqtt3Client client;
    private String json, s;
    private JSONObject jsonObject =null , jsonpre = null;
    private View.OnClickListener cl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myMqtt = new MyMqtt();
        client= myMqtt.getClient();

//
        }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        btn_live = view.findViewById(R.id.btn_live);
        btn_schedule = view.findViewById(R.id.btn_schedule);
        profile = view.findViewById(R.id.home_profile);
        present = view.findViewById(R.id.present);
        GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.home_profileround);
        profile.setBackground(drawable);
        profile.setClipToOutline(true);
        client.toAsync().subscribeWith().topicFilter("Sensor/Humancount").callback(subscribe ->{
            byte[] payload = subscribe.getPayloadAsBytes();

            s = new String(payload, StandardCharsets.UTF_8);
            try {
                jsonObject = new JSONObject(s);
                humancount = jsonObject.getInt("Human");
                if (humancount == 0){
                    present.setImageResource(android.R.drawable.presence_invisible);
                }else {
                    present.setImageResource(android.R.drawable.presence_online);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("mqttpayload", String.valueOf(jsonObject));
        }).send();




        view.findViewById(R.id.btn_popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getContext(), view);
                getActivity().getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_menu1) {
                            Toast.makeText(getActivity(), "메뉴 1 클릭", Toast.LENGTH_SHORT).show();
                        } else if (menuItem.getItemId() == R.id.action_menu2) {
                            Toast.makeText(getActivity(), "메뉴 2 클릭", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "메뉴 3 클릭", Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_live:
                        Intent a = new Intent(getActivity(), monitoring.class);
                        startActivity(a);
                        break;
                    case R.id.btn_schedule:
                        Intent b = new Intent(getActivity(), schedule.class);
                        startActivity(b);
                        break;
                }
            }
        };
        btn_live.setOnClickListener(cl);
        btn_schedule.setOnClickListener(cl);
        return view;
    }
}
