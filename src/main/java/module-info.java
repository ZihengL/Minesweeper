module io.github.zihengl.demineur {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens io.github.zihengl.demineur to javafx.fxml;
    exports io.github.zihengl.demineur;

    opens io.github.zihengl.demineur.controllers to javafx.fxml;
    exports io.github.zihengl.demineur.controllers;
    exports io.github.zihengl.demineur.controllers.components;
    opens io.github.zihengl.demineur.controllers.components to javafx.fxml;
}