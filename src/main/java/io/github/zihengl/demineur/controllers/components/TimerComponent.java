package io.github.zihengl.demineur.controllers.components;

import io.github.zihengl.demineur.models.observer.Observable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TimerComponent extends Observable {

    private final Text txtTime;
    private final Timeline timeline;

    private int time;

    public TimerComponent(Text txtTime) {
        this.txtTime = txtTime;
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    this.txtTime.setText(String.format("%03d", this.time++));
                })
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.time = 0;
    }

    public int getTime() {
        return this.time;
    }

    public void play() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void pause() {
        this.timeline.pause();
    }

    public void reset() {
        this.time = 0;
        this.timeline.playFromStart();
    }
}
