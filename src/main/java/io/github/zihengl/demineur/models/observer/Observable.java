package io.github.zihengl.demineur.models.observer;

import io.github.zihengl.demineur.controllers.MainController;

public class Observable {

    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void notifyObserver() {
        this.observer.update(this);
    }
}
