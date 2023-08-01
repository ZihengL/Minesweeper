package com.example.minesweeper;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class CustomTimertask extends TimerTask {
    public static final int limit = 999;
    public static TextView label;

    // private final Handler handler = new Handler(Looper.getMainLooper());
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Timer timer;
    private int elapsedTime;
    private boolean paused = true;
    // private TextView label;

    public CustomTimertask() {
        this(0);
    }

    public CustomTimertask(int elapsedTime) {
        this.elapsedTime = elapsedTime;
        this.resume();
    }

    // SETTERS

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    // GETTERS

    public int getElapsedTime() {
        return this.elapsedTime;
    }

    public boolean isPaused() {
        return this.paused;
    }

    // METHODS

    public void resume() {
        timer = new Timer();
        paused = false;
        timer.schedule(this, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            paused = true;
            timer.cancel();
            timer = null;
        }
    }

    public void restart() {
        this.stop();
        elapsedTime = 0;
        this.resume();
    }

    @Override
    public void run() {
        if (elapsedTime > limit)
            stop();
        if (!paused) {
            elapsedTime++;
            handler.post(() -> { label.setText(String.format(Locale.CANADA, "%03d", elapsedTime)); });
        }
    }
}
