package io.github.zihengl.demineur.controllers.command;

import io.github.zihengl.demineur.Application;
import io.github.zihengl.demineur.controllers.components.CellComponent;
import io.github.zihengl.demineur.controllers.MainController;
import io.github.zihengl.demineur.models.enums.Difficulties;
import io.github.zihengl.demineur.models.objects.Grid;
import io.github.zihengl.demineur.models.objects.Minesweeper;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewGameCommand implements Command<ActionEvent> {

    private final MainController controller;
    private final Difficulties difficulty;

    public NewGameCommand(MainController controller, Difficulties difficulty) {
        this.controller = controller;
        this.difficulty = difficulty;
    }

    @Override
    public void handle(ActionEvent event) {
        Minesweeper.instance.createNewGame(this.difficulty);
        GridPane cellGrid = this.controller.getCellGrid();

        cellGrid.getChildren().clear();
        Grid grid = Minesweeper.instance.getGrid();
        for (int y = 0; y < grid.getHeight(); y++)
            for (int x = 0; x < grid.getWidth(); x++)
                cellGrid.add(new CellComponent(x, y), y, x);

        Stage stage = Application.getStage();
        stage.sizeToScene();
        stage.centerOnScreen();

        this.controller.setStatusVisibility(false);
        this.controller.getTimer().reset();
    }
}
