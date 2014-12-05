package application;

import java.awt.GridLayout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AccountWindow extends Stage {

	private AnchorPane root;

	public AccountWindow(){
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
		addAccountInfo();
	}
	
	private void addAccountInfo() {
		GridPane gridLayout = new GridPane();
		gridLayout.setVgap(10);
		AnchorPane.setTopAnchor(gridLayout, 60.0);
		AnchorPane.setLeftAnchor(gridLayout, 10.0);
		AnchorPane.setRightAnchor(gridLayout, 0.0);
		root.getChildren().add(gridLayout);
		
		Label infoLabel = new Label("Informance o uctu");
		Label emailLabel = new Label("Email:");
		Label hesloLabel = new Label("Heslo:");
		Label ucteniLabel = new Label("Ucetni:");
		
		TextField emailText = new TextField();
		PasswordField hesloText = new PasswordField();
		CheckBox ucetniCheckBox = new CheckBox();
		
		Button saveButton = new Button("Ulozit");
		
		gridLayout.add(infoLabel, 0, 0);
		gridLayout.add(emailLabel, 0, 1);
		gridLayout.add(hesloLabel, 0, 2);
		gridLayout.add(ucteniLabel, 0, 3);
		
		gridLayout.add(emailText, 1, 1);
		gridLayout.add(hesloText, 1, 2);
		gridLayout.add(ucetniCheckBox, 1, 3);
		gridLayout.add(saveButton, 0, 4);
		
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
				
			}
		});
	}
}
