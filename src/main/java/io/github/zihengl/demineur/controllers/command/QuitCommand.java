package io.github.zihengl.demineur.controllers.command;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class QuitCommand implements Command<ActionEvent>  {

    @Override
    public void handle(ActionEvent event) {
        Platform.exit();
    }
}
