package com.example.map.mvp.view;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.map.R;
import com.example.map.adapter.ActInfoAdapter;
import com.example.map.adapter.FriendAdapter;
import com.example.map.entity.ActEntity;
import com.example.router.RouterManager;
import com.example.router.RouterPath;
import com.example.widget.BNViewGroup;
import com.example.widget.OnViewClickListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.Main)
public class MapActivity extends AppCompatActivity {

    private MapView mMapView;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private RecyclerView rvAct;
    private List<ActEntity> list = new ArrayList<>();
    private MapView map;
    private BNViewGroup actTestBnviewgroup;
    private RecyclerView friendMsgRv;
    private FriendAdapter adapter;
    private List<String> nameList = new ArrayList<>();
    private LinearLayout actMain;
    private TextView tvActMainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (Build.VERSION_CODES.M <= Build.VERSION.SDK_INT) {
            requestPermissions(new String[]{
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.ACCESS_NETWORK_STATE",
                    "android.permission.ACCESS_WIFI_STATE",
                    "android.permission.READ_PHONE_STATE",
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.WRITE_CONTACTS",
                    "android.permission.READ_CONTACTS",
                    "android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"
            }, 1);
        }
        initView(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        initStyle();
    }

    private void initStyle() {
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.showMyLocation(true);
    }

    private void initView(Bundle savedInstanceState) {
        mMapView = findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        rvAct = findViewById(R.id.rv_act);

        ActEntity actEntity = new ActEntity("2019/12/19 16:30:00", "下午3点西二旗XX餐厅");
        ActEntity entity = new ActEntity("2019/12/19 19:30:00", "下午3点西二旗XX餐厅");
        list.add(actEntity);
        list.add(entity);
        ActInfoAdapter infoAdapter = new ActInfoAdapter(R.layout.item_layout, list);
        rvAct.setAdapter(infoAdapter);
        rvAct.setLayoutManager(new LinearLayoutManager(this));
        map = findViewById(R.id.map);
        actTestBnviewgroup = findViewById(R.id.act_test_bnviewgroup);

        actTestBnviewgroup.setListener(new OnViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                if (view.getId() == R.id.iv_contract) {
                    startActivity(new Intent(MapActivity.this, ContractActivity.class));
                } else if (view.getId() == R.id.iv_msg) {
                    RouterManager.getInstance().route(RouterPath.MSG);
                }

            }
        });
        //右边列表
        friendMsgRv = findViewById(R.id.friend_msg_rv);
        nameList.add("444");
        nameList.add("88");
        actMain = findViewById(R.id.act_main);
        tvActMainUser = findViewById(R.id.tv_act_main_user);
        adapter = new FriendAdapter(nameList);
        friendMsgRv.setAdapter(adapter);
        friendMsgRv.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvActMainUser.setText(adapter.getData().get(position).toString());
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 500);
                valueAnimator.setDuration(3000);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int value = (int) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = actMain.getLayoutParams();
                        layoutParams.width = value;
                        actMain.setLayoutParams(layoutParams);
                    }
                });
                valueAnimator.start();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//            if(event.getAction()==MotionEvent.ACTION_DOWN) {
//                ViewGroup.LayoutParams layoutParams = actMain.getLayoutParams();
//                if (layoutParams.width > 0) {
//                    ValueAnimator valueAnimator = ValueAnimator.ofInt(400, 0);
//                    valueAnimator.setDuration(3000);
//                    valueAnimator.setInterpolator(new LinearInterpolator());
//                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                        @Override
//                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                            int value = (int) valueAnimator.getAnimatedValue();
//                            layoutParams.width = value;
//                            actMain.setLayoutParams(layoutParams);
//                        }
//                    });
//                    valueAnimator.start();
//                }
//        }
//        return super.onTouchEvent(event);
//    }
}
