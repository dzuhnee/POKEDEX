module com.ellakyle.pokedexgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ellakyle.pokedexgui to javafx.fxml;
    exports com.ellakyle.pokedexgui;
}