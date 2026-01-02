module io.github.zihengl.demineur {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.github.zihengl.demineur to javafx.fxml;
    exports io.github.zihengl.demineur;

    opens io.github.zihengl.demineur.controllers to javafx.fxml;
    exports io.github.zihengl.demineur.controllers;
}