module project.moviedatabase {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens project.moviedatabase to javafx.fxml;
    exports project.moviedatabase;
}