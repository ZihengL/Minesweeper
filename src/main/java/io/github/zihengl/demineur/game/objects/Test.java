package io.github.zihengl.demineur.game.objects;

public class Test {

    public static void main(String[] args) {
        Grid grid = Grid.getInstance();

        grid.setSize(2, 5);
        System.out.println("width: " + grid.getWidth());
        System.out.println("height: " + grid.getHeight());
    }
}
