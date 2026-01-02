package io.github.zihengl.demineur.models.objects;

import io.github.zihengl.demineur.models.observer.Observable;
import io.github.zihengl.demineur.models.observer.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer extends Observable {

    private int time;
    private final Timeline timeline;

    public Timer() {
        this.time = 0;
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    this.time++;
                    this.notifyObserver();
                })
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
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
