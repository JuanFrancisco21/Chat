module com.ajaguilar.Chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
	requires javafx.base;
	requires javafx.graphics;

    opens com.ajaguilar.Chat to javafx.fxml,javafx.graphics,javafx.controls,Modelo, DAO,com.ajaguilar.Taller;
    opens Modelo to java.xml.bind,javafx.controlsModelo, DAO,com.ajaguilar.Taller;
    opens Utiles to java.xml.bind,javafx.controls,Modelo, DAO,com.ajaguilar.Taller;
    opens DAO to java.xml.bind,javafx.controls,Modelo, DAO,com.ajaguilar.Taller;
   
    exports com.ajaguilar.Chat;
    exports Modelo;
    exports DAO;
    exports Utiles;
}