package application;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;

public class DownloadTab extends Tab {
	
	private String caption;
	private Label dateLabel;
	private final String downloadFolderName = "Downloaded";
	public DownloadTab(String caption){
		this.caption = caption;
		File incomingDocumentFolder = new File(downloadFolderName);
		incomingDocumentFolder.mkdir();
		createContents();
	}

	private void createContents() {
		setText(this.caption);
		AnchorPane pane = new AnchorPane();
		setClosable(false);
		setContent(pane);
		
		DropShadow dropShadow = new DropShadow();
		 dropShadow.setRadius(5.0);
		 dropShadow.setOffsetX(3.0);
		 dropShadow.setOffsetY(3.0);
		 dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		 
		 Circle circle = new Circle(40, LinearGradient.valueOf("linear-gradient(to bottom, #b4e391 0%,#61c419 50%,#b4e391 100%)"));
		 circle.setEffect(dropShadow);
		 AnchorPane.setTopAnchor(circle, 60.0);
		 AnchorPane.setLeftAnchor(circle, 60.0);
		 pane.getChildren().add(circle);
		 
		 Image arrowDown = new Image(DownloadTab.class.getResourceAsStream("/arrowdown.png"));
		 ImageView imageView = new ImageView(arrowDown);
		 AnchorPane.setTopAnchor(imageView, 77.0);
		 AnchorPane.setLeftAnchor(imageView, 77.0);
		 pane.getChildren().add(imageView);
		
		
		
		Button b = new Button("This is a button");
		b.getStyleClass().add("basebutton");
		AnchorPane.setTopAnchor(b, 20.0);
		AnchorPane.setLeftAnchor(b, 60.0);
		pane.getChildren().add(b);
		
		Label captionLabel = new Label("This is a label");
		captionLabel.getStyleClass().add("labelnormal");
		AnchorPane.setTopAnchor(captionLabel, 70.0);
		AnchorPane.setLeftAnchor(captionLabel, 160.0);
		pane.getChildren().add(captionLabel);
		
		dateLabel = new Label("This is a label");
		AnchorPane.setTopAnchor(dateLabel, 90.0);
		AnchorPane.setLeftAnchor(dateLabel, 160.0);
		pane.getChildren().add(dateLabel);
		
	}
}
