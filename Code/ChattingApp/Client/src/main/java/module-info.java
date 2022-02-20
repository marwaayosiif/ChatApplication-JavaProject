module Client {
    requires transitive javafx.controls;
    requires  javafx.fxml;
    requires  org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires java.rmi;
    requires Common;
    requires javafx.web;
    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    exports gov.iti.jets.service.dtos;
    exports gov.iti.jets.service;
    exports gov.iti.jets;
}