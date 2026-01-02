package io.github.zihengl.demineur.controllers;

import io.github.zihengl.demineur.controllers.command.commands.menubar.NewGameCommand;
import io.github.zihengl.demineur.controllers.command.commands.menubar.QuitCommand;
import io.github.zihengl.demineur.models.enums.Difficulties;
import io.github.zihengl.demineur.models.objects.Timer;
import io.github.zihengl.demineur.models.observer.Observable;
import io.github.zihengl.demineur.models.observer.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainController implements Observer {

    public static final Timer timer = new Timer();

    @FXML private Menu optionsMenu;
    @FXML private HBox dashboard;
    @FXML private Text timerText;

    @FXML private GridPane cellGrid;

    // MENUBAR
    @FXML private void initialize() {
        timer.setObserver(this);

        for (Difficulties difficulty : Difficulties.values()) {
            MenuItem item = new MenuItem();
            item.setOnAction(new NewGameCommand(this.cellGrid, difficulty));
            item.setText(difficulty.name());

            this.optionsMenu.getItems().add(item);
        }

        MenuItem quitItem = new MenuItem();
        quitItem.setText("Quit");
        quitItem.setOnAction(new QuitCommand());
        this.optionsMenu.getItems().addAll(new SeparatorMenuItem(), quitItem);

        this.optionsMenu.getItems().getFirst().fire();
    }

    @Override
    public void update(Observable observable) {
        this.timerText.setText(String.valueOf(timer.getTime()));
    }
}
