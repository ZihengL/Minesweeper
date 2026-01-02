package io.github.zihengl.demineur.models.observer;

public class Observable {

    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void notifyObserver() {
        this.observer.update(this);
    }
}
