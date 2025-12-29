package io.github.zihengl.demineur.game.enums;

public enum Difficulties {

    BEGINNER(9, 9, 10),
    INTERMEDIATE(16, 16, 40),
    EXPERT(30, 16, 99);

    public final int width;
    public final int height;
    public final int mines;

    private Difficulties(int height, int width, int mines) {
        this.height = height;
        this.width = width;
        this.mines = mines;
    }

    public String toString() {
        String name = this.name().charAt(0) + this.name().substring(1).toLowerCase();

        return name + "\n" + height + " x " + width + " (" + mines + " mines)";
    }
}
