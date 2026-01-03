package io.github.zihengl.demineur.controllers;

import io.github.zihengl.demineur.controllers.command.NewGameCommand;
import io.github.zihengl.demineur.controllers.command.QuitCommand;
import io.github.zihengl.demineur.controllers.components.TimerComponent;
import io.github.zihengl.demineur.models.enums.Difficulties;
import io.github.zihengl.demineur.models.enums.Gamestate;
import io.github.zihengl.demineur.models.objects.Minesweeper;
import io.github.zihengl.demineur.models.observer.Observable;
import io.github.zihengl.demineur.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainController implements Observer {

    private static TimerComponent timer;

    public static TimerComponent getTimer() {
        return MainController.timer;
    }

    @FXML private Menu optionsMenu;

    @FXML private Text txtTime;
    @FXML private Text txtDugCount;
    @FXML private Text txtFlagsCount;

    @FXML private GridPane cellGrid;

    // MENUBAR
    @FXML private void initialize() {
        // Timer
        MainController.timer = new TimerComponent(this.txtTime);

        // Menubar: Difficulty buttons
        for (Difficulties difficulty : Difficulties.values()) {
            MenuItem item = new MenuItem();
            item.setOnAction(new NewGameCommand(this.cellGrid, difficulty));
            item.setText(difficulty.name());

            this.optionsMenu.getItems().add(item);
        }

        // Menubar: Quit button
        MenuItem quitItem = new MenuItem();
        quitItem.setText("Quit");
        quitItem.setOnAction(new QuitCommand());
        this.optionsMenu.getItems().addAll(new SeparatorMenuItem(), quitItem);

        // To update flags and dug counts
        Minesweeper.instance.setObserver(this);

        // Default startup difficulty to Beginner
        this.optionsMenu.getItems().getFirst().fire();
        this.update(Minesweeper.instance);
    }

    private void showEndGameAlert(boolean isVictory) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Endgame");
        confirm.setHeaderText(isVictory ? "VICTORY" : "DEFEAT");

        confirm.showAndWait();
    }

    @Override
    public void update(Observable observable) {
        Minesweeper instance = (Minesweeper) observable;

        String dug = String.format("%03d", instance.getDugCount());
        this.txtDugCount.setText(dug);

        String flags = String.format("%03d", instance.getFlagsCount());
        this.txtFlagsCount.setText(flags);

        Gamestate state = instance.getGamestate();
        if (!state.equals(Gamestate.ONGOING))
            this.showEndGameAlert(state.equals(Gamestate.VICTORY));

    }
}
