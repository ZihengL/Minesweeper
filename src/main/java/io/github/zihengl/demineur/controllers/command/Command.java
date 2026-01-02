package io.github.zihengl.demineur.controllers.command;

import javafx.event.Event;
import javafx.event.EventHandler;

public interface Command<T extends Event> extends EventHandler<T> {

    // Déclaration explicite pour la clarté.
    void handle(T event);
}