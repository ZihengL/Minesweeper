package io.github.zihengl.demineur;

import io.github.zihengl.demineur.models.objects.Grid;

public class Test {

    public static void main(String[] args) {

        Grid grid = new Grid(5, 10);

        for (int i = 0; i < grid.getHeight(); i++) {
            System.out.printf("\n");

            for (int j = 0; j < grid.getWidth(); j++) {
                System.out.print(grid.cells[i][j].getValue() + "\t");
            }
        }
    }
}
