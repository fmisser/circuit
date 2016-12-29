package com.fmisser.circuit.ui.common;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;

import com.fmisser.circuit.R;

import java.io.IOException;

/**
 * Date: 2016/12/29.
 * Author: fmisser
 * Description: 使用 {@link android.view.TextureView } 播放视频
 */

public class VideoTextureView extends FrameLayout implements TextureView.SurfaceTextureListener {

    private TextureView textureView;
    private MediaPlayer mediaPlayer;
    private boolean isPrepared = false;
    private boolean isRecover = false;
    int position;
    int videoId;

    public VideoTextureView(Context context) {
        super(context);
        init(context);
    }

    public VideoTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VideoTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_video_texture_view, this);
        textureView = (TextureView) findViewById(R.id.video_view);
        textureView.setSurfaceTextureListener(this);
    }

    private void prepare(SurfaceTexture surfaceTexture) {
        if (!isPrepared) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(getContext(), Uri.parse("android.resource://" + getContext().getPackageName() + "/" + videoId));
                mediaPlayer.setSurface(new Surface(surfaceTexture));
                mediaPlayer.prepare();
                isPrepared = true;
            } catch (IOException e) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }

    private void release() {
        if (isPrepared) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isPrepared = false;
        }
    }

    public void setVideoId(int id) {
        videoId = id;
    }

    public void play() {
        if (!isPrepared) {
            isRecover = true;
        } else {
            mediaPlayer.start();
        }
    }

    public void onResume() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
            mediaPlayer.start();

            //如果SurfaceTexture没有destroy,则不需要等待Texture重建
            isRecover = false;
        }
    }

    public void onPause() {
        if (mediaPlayer != null) {
            position = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }

        //可能需要复原,设置标志
        isRecover = true;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        prepare(surface);
        if (isRecover) {
            //重建后继续播放
            mediaPlayer.seekTo(position);
            mediaPlayer.start();
            isRecover = false;
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        release();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
