package com.abhi.androidthingsvideoshare;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.io.IOException;

/**
 * Created by abhi on 2/5/17.
 */

public class VideoTextureView extends TextureView implements TextureView.SurfaceTextureListener, MediaPlayer.OnPreparedListener {

    private final String TAG = this.getClass().getSimpleName();
    private MediaPlayer mMediaPlayer;

    public VideoTextureView(Context context) {
        super(context);
        init();
    }

    public VideoTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setSurface(surface);
        try {
            mMediaPlayer.setDataSource("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(this);
        }
        catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
