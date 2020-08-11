package com.example.map.mvp.view;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.R;
import com.example.map.adapter.PeopleRVAdapter;
import com.example.widget.TitleBar;

import java.util.ArrayList;

public class PhonePeopleActivity extends AppCompatActivity {

    private TitleBar tbAddcontract;
    private RecyclerView rvPhonePeople;
    private ArrayList<String> list =new ArrayList<>();
    private PeopleRVAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_people);

        initView();
    }

    private void initView() {
        tbAddcontract = findViewById(R.id.tb_addcontract);
        rvPhonePeople = findViewById(R.id.rv_phone_people);
        Cursor query = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null, null);
        while (query.moveToNext()){
            String name = query.getString(query.getColumnIndex("ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME"));
            list.add(name);
        }
        query.close();
        adapter = new PeopleRVAdapter(list);
        rvPhonePeople.setAdapter(adapter);
        rvPhonePeople.setLayoutManager(new LinearLayoutManager(this));
    }
}
