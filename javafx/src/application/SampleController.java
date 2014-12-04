package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class SampleController implements Initializable {
	
	@FXML
	private HBox topPane;
	public void initialize(URL location, ResourceBundle resources) {
		topPane.getStyleClass().add("topcontainer");
	}
	
}
