package com.example.btllogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Giaodien extends AppCompatActivity {
    private RecyclerView rcvUser;
    private UserAdapter muserAdapter;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.Open,R.string.Close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        rcvUser=findViewById(R.id.rckview);
//        muserAdapter=new UserAdapter(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
//        rcvUser.setLayoutManager(gridLayoutManager);
//        muserAdapter.setdata(getlist());
//        rcvUser.setAdapter(muserAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(itemDecoration)   ;

        muserAdapter = new UserAdapter(this);
        muserAdapter.setdata(getlist());
        rcvUser.setAdapter(muserAdapter);
    }
    private List<User> getlist(){
        List<User> list = new ArrayList<>();
//        list.add(new User(R.drawable.mc1,"MC1"));
//        list.add(new User(R.drawable.mc2,"MC2"));
//        list.add(new User(R.drawable.mc3,"MC3"));
//        list.add(new User(R.drawable.mc4,"MC4"));
//        list.add(new User(R.drawable.mc5,"MC5"));
//        list.add(new User(R.drawable.mc5,"MC6"));
        for(int i = 1;i<=20;i++){
            int resID = getResId("mc" + i%6, R.drawable.class);
            Uri imgUri = getUri(resID);
            User p = new User(imgUri,"Hoàng Trung Cường "+i);
            String price = String.format("%.2f",new Random().nextFloat() * 1000)+"$";
            p.setPrice(price);
            list.add(p);
        }
        return list;
    }
    public Uri getUri (int resId){
        return Uri.parse("android.resource://"  + this.getPackageName().toString() + "/" + resId);
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}