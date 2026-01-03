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

    private final GridPane container;
    private final Difficulties difficulty;

    public NewGameCommand(GridPane container, Difficulties difficulty) {
        this.container = container;
        this.difficulty = difficulty;
    }

    @Override
    public void handle(ActionEvent event) {
        Minesweeper.instance.createNewGame(this.difficulty);
        this.container.getChildren().clear();

        Grid grid = Minesweeper.instance.getGrid();
        for (int y = 0; y < grid.getHeight(); y++)
            for (int x = 0; x < grid.getWidth(); x++)
                this.container.add(new CellComponent(x, y), y, x);

        Stage stage = Application.getStage();
        stage.sizeToScene();
        stage.centerOnScreen();

        MainController.getTimer().reset();
    }
}
