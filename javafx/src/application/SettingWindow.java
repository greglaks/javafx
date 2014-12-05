package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingWindow extends Stage {

	private AnchorPane root;
	private Label directoryLabel;

	public SettingWindow(){
		
		createContents();
	}

	private void createContents() {
		setHeight(400);
		setWidth(420);
		setResizable(false);
		initStyle(StageStyle.DECORATED);
		
		root = new AnchorPane();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		setScene(scene);
		
		//Check if online
		getIcons().add(Util.getOnlineImage());
		
		addHeaderButtonsPane();
		addLabelPane();
		addButtonPane();
		addTextArea();
	}

	private void addTextArea() {
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(10);
		textArea.setPrefColumnCount(6);
		AnchorPane.setTopAnchor(textArea, 170.0);
		AnchorPane.setLeftAnchor(textArea, 10.0);
		AnchorPane.setRightAnchor(textArea, 10.0);
		
		root.getChildren().add(textArea);
	}

	private void addButtonPane() {
		HBox hbox = new HBox();
		hbox.setPrefHeight(40);
		AnchorPane.setTopAnchor(hbox, 120.0);
		AnchorPane.setLeftAnchor(hbox, 10.0);
		AnchorPane.setRightAnchor(hbox, 0.0);
		root.getChildren().add(hbox);
		
		Button b1 = new Button("Zmenit");
		Button b2 = new Button("Ulozit zmenu");
		hbox.getChildren().add(b1);
		hbox.getChildren().add(b2);
		
		hbox.setMargin(b1, new Insets(4, 10, 4, 0));
		hbox.setMargin(b2, new Insets(4, 10, 4, 10));
		b1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				DirectoryChooser directoryChooser = new DirectoryChooser();
		            final File selectedDirectory = directoryChooser.showDialog(SettingWindow.this);
		            if (selectedDirectory != null) {
		            	directoryLabel.setText(selectedDirectory.getAbsolutePath());
		            }
				
			}
		});
	}

	private void addLabelPane() {
		VBox vbox = new VBox();
		root.getChildren().add(vbox);
		
		AnchorPane.setTopAnchor(vbox, 60.0);
		AnchorPane.setLeftAnchor(vbox, 10.0);
		AnchorPane.setRightAnchor(vbox, 0.0);
		
		Label l1 = new Label("Informance o sledovane slozce");
		Label l2 = new Label("Sledovana a slozka");
		directoryLabel = new Label();
		
		
		vbox.getChildren().add(l1);
		vbox.getChildren().add(l2);
		vbox.getChildren().add(directoryLabel);
		
	}

	private void addHeaderButtonsPane() {
		HBox buttonPane = new HBox();
		buttonPane.getStyleClass().add("bottompane");
		buttonPane.setPrefHeight(30);
		AnchorPane.setTopAnchor(buttonPane, 0.0);
		AnchorPane.setLeftAnchor(buttonPane, 0.0);
		AnchorPane.setRightAnchor(buttonPane, 0.0);
		root.getChildren().add(buttonPane);
		
		Button b1 = new Button("Slozka");
		Button b2 = new Button("Ucet");
		Button b3 = new Button("Rozsirene");
		Button b4 = new Button("Napoveda");
		
		buttonPane.setMargin(b1, new Insets(3,24,3,0));
		buttonPane.setMargin(b2, new Insets(3,24,3,24));
		buttonPane.setMargin(b3, new Insets(3,24,3,24));
		buttonPane.setMargin(b4, new Insets(3,0,3,24));
		
		buttonPane.getChildren().add(b1);
		buttonPane.getChildren().add(b2);
		buttonPane.getChildren().add(b3);
		buttonPane.getChildren().add(b4);
		
		b2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				SettingWindow.this.close();
				AccountWindow accountWindow = new AccountWindow();
				accountWindow.show();
			}
		});
	}
}
