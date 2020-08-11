package com.example.msg.mvp.view;

import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.msg.R;

import java.io.File;
import java.util.Calendar;

public class MRActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView svSurfaceview;
    private Button btnVideoStartStop;
//    private Button btnVideoPlay;

    //是否开始录制
    private boolean isStart = false;

    private MediaRecorder mRecorder;
    private Camera camera;
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mediaPlayer;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mr);
        if(Build.VERSION_CODES.M<=Build.VERSION.SDK_INT){
            requestPermissions(new String[]{"android.permission.CAMERA","android.permission.RECORD_AUDIO","android.permission.WRITE_EXTERNAL_STORAGE"},101);
        }
        initView();
        initEvent();
    }

    private void initEvent() {

        btnVideoStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!isStart) {
                    if (mRecorder == null) {
                        mRecorder = new MediaRecorder();
                    }
                    camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                    if (camera != null) {
                        camera.setDisplayOrientation(90);
                        camera.unlock();
                        try {
                            mRecorder.setCamera(camera);
                            mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                            mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                            mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
                            //设置视频的宽高
                            mRecorder.setVideoSize(640, 480);
                            //设置帧率 1秒30帧
                            mRecorder.setVideoFrameRate(30);
                            mRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
                            mRecorder.setOrientationHint(90);
                            //设置记录会话的最大持续时间（毫秒）
                            mRecorder.setMaxDuration(30 * 1000);
                            //设置预览图像显示到SurfaceView
                            mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
                            btnVideoStartStop.setText("停止");
                            path = getSDPath();
                            if (path != null) {

                                File dir = new File(path + "/zy_video");
                                if (!dir.exists()) {
                                    dir.mkdir();
                                }
                                //最终录制后的视频文件存储位置
                                path = dir + "/" + getDate() + ".mp4";
                                Log.d("123", "video file save path=" + path);
                                //视频输出
                                mRecorder.setOutputFile(path);
                                //录制前的准备
                                mRecorder.prepare();
                                //开始录制
                                mRecorder.start();
                                btnVideoStartStop.setText("停止");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        isStart = true;
                    }
                } else {
                    try {
                        mRecorder.stop();
                        mRecorder.reset();
                        mRecorder.release();
                        mRecorder = null;
                        btnVideoStartStop.setText("开始");
                        if (camera != null) {
                            camera.release();
                            camera = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    isStart = false;

                }

            }
        });
//        btnVideoPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayer == null) {
//                    mediaPlayer = new MediaPlayer();
//                }
//                mediaPlayer.reset();
//                Uri uri = Uri.parse(path);
//                mediaPlayer = MediaPlayer.create(MRActivity.this, uri);
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setDisplay(mSurfaceHolder);
//                try{
//                    mediaPlayer.prepare();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                mediaPlayer.start();
//            }
//        });

        SurfaceHolder holder = svSurfaceview.getHolder();
        holder.addCallback(this);
        // setType必须设置，要不出错.
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private void initView() {
        svSurfaceview = (SurfaceView) findViewById(R.id.sv_surfaceview);
        btnVideoStartStop = (Button) findViewById(R.id.btn_video_start_stop);
//        btnVideoPlay = (Button) findViewById(R.id.btn_video_play);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // 将holder，这个holder为开始在onCreate里面取得的holder，将它赋给mSurfaceHolder
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        svSurfaceview = null;
        mSurfaceHolder = null;
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
            Log.d("123", "surfaceDestroyed release mRecorder");
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static String getDate() {
        Calendar ca = Calendar.getInstance();
        // 获取年份
        int year = ca.get(Calendar.YEAR);
        // 获取月份
        int month = ca.get(Calendar.MONTH);
        // 获取日
        int day = ca.get(Calendar.DATE);
        // 分
        int minute = ca.get(Calendar.MINUTE);
        // 小时
        int hour = ca.get(Calendar.HOUR);
        // 秒
        int second = ca.get(Calendar.SECOND);

        String date = "" + year + (month + 1) + day + hour + minute + second;
        Log.d("123", "date:" + date);

        return date;
    }

    /**
     * 获取SD path
     *
     * @return
     */
    public String getSDPath() {
        File sdDir = null;

        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
        // 判断sd卡是否存在
        if (sdCardExist) {
            // 获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }

        return null;
    }

}
