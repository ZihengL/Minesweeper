package io.github.zihengl.demineur.controllers;

import io.github.zihengl.demineur.models.enums.Status;
import io.github.zihengl.demineur.models.objects.Cell;
import io.github.zihengl.demineur.models.objects.Minesweeper;
import io.github.zihengl.demineur.models.observer.Observable;
import io.github.zihengl.demineur.models.observer.Observer;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;

import java.net.URL;

public class CellComponent extends ImageView implements Observer {

    public static final String RES_DIR = "/io/github/zihengl/demineur/images/Minesweeper_";
    public static final int CELL_SIZE = 30;

    public CellComponent(int x, int y) {
        this.setPreserveRatio(true);
        this.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();

            if (button == MouseButton.PRIMARY)
                Minesweeper.instance.dig(x, y);

            if (button == MouseButton.SECONDARY)
                Minesweeper.instance.toggleFlag(x, y);
        });

        this.update(Minesweeper.instance.getGrid().getCell(x, y));
    }

    @Override
    public void update(Observable observable) {
        Cell cell = (Cell) observable;
        String res = cell.isStatus(Status.DUG) ? String.valueOf(cell.getValue()) : cell.getStatus().name();
        URL url = getClass().getResource(RES_DIR + res + ".png");

        this.setImage(new Image(url.toString()));
        this.setFitWidth(CELL_SIZE);
        this.setFitHeight(CELL_SIZE);
    }
}
