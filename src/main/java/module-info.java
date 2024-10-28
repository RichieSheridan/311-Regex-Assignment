module sherrc._311regex {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens sherrc._311regex to javafx.fxml;
    exports sherrc._311regex;
}