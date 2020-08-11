package com.example.msg.mvp.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msg.R;
import com.example.msg.mvp.adapter.ChatAdapter;
import com.example.msg.mvp.entity.msgEntity;
import com.example.storage.core.MsgSql;
import com.example.storage.core.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvChat;
    private EditText etActSendMsg;
    private Button btActSendMsg;
    private ImageView ivActSendMsgWheat;
    private ImageView ivActSendMsgPic;
    private List<msgEntity> list = new ArrayList<>();
    private ChatAdapter adapter = new ChatAdapter(list);
    private String code;
    private ImageView ivMr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        code = getIntent().getStringExtra("code");

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
//        btActSendMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String msg = etActSendMsg.getText().toString();
//                if (!TextUtils.isEmpty(msg)) {
//
//                }
//            }
//        });
        ivMr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatActivity.this,MRActivity.class));
            }
        });
    }

    private void initData() {
        String ucode = (String) SharePreferenceUtils.get(this, "ucode", "");
        MsgSql msgSql = new MsgSql(this, "GSIM1", null, 1);
        SQLiteDatabase msgSqlWritableDatabase = msgSql.getWritableDatabase();
        Cursor query = msgSqlWritableDatabase.query("msgtb", null, null, null, null, null, null);
        while (query.moveToNext()) {
            String fucode = query.getString(query.getColumnIndex("fucode"));
            String tocode = query.getString(query.getColumnIndex("tocode"));
            String info = query.getString(query.getColumnIndex("info"));
            String sendtime = query.getString(query.getColumnIndex("sendtime"));
            msgEntity msgEntity = new msgEntity();
            if (fucode.equals(ucode) && tocode.equals(code)) {
                msgEntity.setFromuser(fucode);
                msgEntity.setMsg(info);
                msgEntity.setTouser(tocode);
                msgEntity.setMsgtime(sendtime);
                msgEntity.setType(0);
            } else if (tocode.equals(ucode) && fucode.equals(code)) {
                msgEntity.setFromuser(fucode);
                msgEntity.setMsg(info);
                msgEntity.setTouser(tocode);
                msgEntity.setMsgtime(sendtime);
                msgEntity.setType(1);
            }
            list.add(msgEntity);
            adapter.notifyDataSetChanged();
        }
        query.close();

    }

    private void initView() {
        rvChat = findViewById(R.id.rv_chat);
        etActSendMsg = findViewById(R.id.et_act_send_msg);
        btActSendMsg = findViewById(R.id.bt_act_send_msg);
        ivActSendMsgWheat = findViewById(R.id.iv_act_send_msg_wheat);
        ivActSendMsgPic = findViewById(R.id.iv_act_send_msg_pic);
        rvChat.setAdapter(adapter);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        ivMr = findViewById(R.id.iv_mr);

    }
}
