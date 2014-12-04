package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class SendedTab extends Tab {
	
	private Label listItemCaption = new Label("List item");
	private String caption;
	private final String incomingDocumentCaption = "IncomingDocument";
	private final String incomingDocumentBackUpCaption = "IncomingDocument_Backup";
	private EventHandler<DragEvent > dragListener = new EventHandler<DragEvent >() {

		@Override
		public void handle(DragEvent  arg0) {
			 Dragboard db = arg0.getDragboard();
             if (db.hasFiles()) {
            	 arg0.acceptTransferModes(TransferMode.COPY);
            	// labelBox.getStyleClass().add("drophover");
             } else {
            	 arg0.consume();
             }
		}
	};
	
	private EventHandler<DragEvent> dropedListener = new EventHandler<DragEvent>() {

		@Override
		public void handle(DragEvent arg0) {
			 Dragboard db = arg0.getDragboard();
             boolean success = false;
             if (db.hasFiles()) {
                 success = true;
                 for (File file:db.getFiles()) {
                	 if(!file.isDirectory()){
                		 File documentFolder = new File(incomingDocumentCaption);
                		 try {
                			 FileUtils.copyFileToDirectory(file, documentFolder);
                			 detectItem();
                		 } catch (IOException e) {
                			 e.printStackTrace();
                		 }                		 
                	 }
                 }
             }
             arg0.setDropCompleted(success);
             arg0.consume();
            // labelBox.getStyleClass().clear();
			// labelBox.getStyleClass().add("topcontainer");
		}
	};
	private ComboBox combo;
	
	private Label fileSendingNumber;
	private Label fileSendingBackupNumber;
	private String sendingNumberCaption = "K odesláni: ";
	private String sendingBackupNumberCaption = "Odeslane:";
	private File documentFolder = new File(incomingDocumentCaption);
	private File documentBackUpFolder = new File(incomingDocumentBackUpCaption);
	private HBox labelBox;
	private VBox itemListWrapper;

	public SendedTab(String caption){
		this.caption = caption;
		setText(this.caption);
		createContents();
		combo.getItems().add("My Incoming Documents");
		combo.getItems().add("Other Incoming Documents");
		createFolder();
		detectItem();
	}

	private void detectItem() {
		int documentFolderFilesNumber = documentFolder.listFiles().length;
		boolean isDocumentFolderEmpty =  documentFolderFilesNumber == 0;
		updateFileNumber();
		
		if(isDocumentFolderEmpty){
			listDocumentFolder(documentBackUpFolder);
		}
		else{
			listDocumentFolder(documentFolder);	
			//Do the file transfering to web server here and call this method
			//moveFilesToBackUp();
		}
		
	}

	private void updateFileNumber() {
		int documentFolderFilesNumber = documentFolder.listFiles().length;
		int documentBackupFolderFilesNumber = documentBackUpFolder.listFiles().length;
		fileSendingNumber.setText(sendingNumberCaption+" "+documentFolderFilesNumber);
		fileSendingBackupNumber.setText(sendingBackupNumberCaption+" "+documentBackupFolderFilesNumber);
		
	}

	private void moveFilesToBackUp() {
		for(File f: documentFolder.listFiles()){
			try {
				FileUtils.moveFileToDirectory(f, documentBackUpFolder, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		updateFileNumber();
		
	}

	private void listDocumentFolder(File documentFolder) {
		itemListWrapper.getChildren().clear();
		
		int index = 0;
		for(File f : documentFolder.listFiles()){
			if(index < 3){
				index++;
				HBox item = createFileItem(f,true);
				itemListWrapper.getChildren().add(item);
			}
		}
		
	}

	private void createFolder() {
		File incomingDocumentFolder = new File(incomingDocumentCaption);
		File incomingDocumentBackUpFolder = new File(incomingDocumentBackUpCaption);
		
		
		incomingDocumentFolder.mkdir();
		incomingDocumentBackUpFolder.mkdir();
		
	}

	private void createContents() {
		listItemCaption.setAlignment(Pos.CENTER);
		AnchorPane pane = new AnchorPane();
		setClosable(false);
		setContent(pane);
	
		labelBox = new HBox();
		labelBox.setOnDragOver(dragListener);
		labelBox.setOnDragDropped(dropedListener);
		labelBox.getStyleClass().add("topcontainer");
		AnchorPane.setTopAnchor(labelBox, 0.0);
		AnchorPane.setLeftAnchor(labelBox, 0.0);
		AnchorPane.setRightAnchor(labelBox, 0.0);
		labelBox.setPrefHeight(160);
		
		labelBox.setAlignment(Pos.CENTER);
		Label titleLabel = new Label();
		titleLabel.getStyleClass().add("title");
		titleLabel.setText("Drop file here");
		titleLabel.setTextAlignment(TextAlignment.CENTER);
		labelBox.getChildren().add(titleLabel);
		pane.getChildren().add(labelBox);
		
		fileSendingNumber = new Label(sendingNumberCaption );
		fileSendingBackupNumber = new Label(sendingBackupNumberCaption );
		fileSendingNumber.getStyleClass().add("labelnormal");
		fileSendingBackupNumber.getStyleClass().add("labelnormal");
		combo = new ComboBox();
		GridPane gridPane = new GridPane();
		gridPane.setPrefHeight(40);
		gridPane.getStyleClass().add("middlepane");
		AnchorPane.setTopAnchor(gridPane, 160.0);
		AnchorPane.setLeftAnchor(gridPane, 0.0);
		AnchorPane.setRightAnchor(gridPane, 0.0);

		ColumnConstraints c1 = new ColumnConstraints(10, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
		c1.setHgrow(Priority.ALWAYS);
		c1.setPercentWidth(10);

		ColumnConstraints c2 = new ColumnConstraints(10, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
		c2.setHgrow(Priority.ALWAYS);
		c2.setPercentWidth(10);
		
		ColumnConstraints c3 = new ColumnConstraints(10, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
		c3.setHgrow(Priority.ALWAYS);
		c3.setPercentWidth(40);
		
		ColumnConstraints c4 = new ColumnConstraints(10, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
		c4.setHgrow(Priority.ALWAYS);
		c4.setPercentWidth(40);
		
		gridPane.setHalignment(fileSendingNumber, HPos.CENTER);
		gridPane.setHalignment(fileSendingBackupNumber, HPos.CENTER);
		
		gridPane.getColumnConstraints().add(c1);
		gridPane.getColumnConstraints().add(c2);
		gridPane.getColumnConstraints().add(c3);
		gridPane.getColumnConstraints().add(c4);
		
		combo.setMinWidth(Control.USE_COMPUTED_SIZE);
		combo.setMaxWidth(Double.MAX_VALUE);
		
		gridPane.setHalignment(fileSendingNumber, HPos.CENTER);
		gridPane.setHgrow(combo, Priority.ALWAYS);
		gridPane.setColumnSpan(combo, 2);
		
		
		gridPane.add(fileSendingNumber, 0, 0);
		gridPane.add(fileSendingBackupNumber, 1, 0);
		gridPane.add(combo, 2, 0);
		pane.getChildren().add(gridPane);
		
		VBox itemListPane = new VBox();
		itemListPane.setPrefHeight(200);
		itemListPane.setSpacing(20);
		itemListPane.setFillWidth(false);
		itemListPane.setAlignment(Pos.BASELINE_CENTER);
		AnchorPane.setTopAnchor(itemListPane, 210.0);
		AnchorPane.setLeftAnchor(itemListPane, 0.0);
		AnchorPane.setRightAnchor(itemListPane, 0.0);
		pane.getChildren().add(itemListPane);
		itemListPane.getChildren().add(listItemCaption);
		
		itemListWrapper = new VBox();
		itemListWrapper.setPrefWidth(200);
		itemListWrapper.setAlignment(Pos.CENTER);
		itemListWrapper.setSpacing(10);
		itemListPane.getChildren().add(itemListWrapper);
		
		
		HBox bottomPane = new HBox();
		
		bottomPane.getStyleClass().add("bottompane");
		bottomPane.setPrefHeight(10);
		AnchorPane.setBottomAnchor(bottomPane, 0.0);
		AnchorPane.setLeftAnchor(bottomPane, 0.0);
		AnchorPane.setRightAnchor(bottomPane, 0.0);
		bottomPane.setAlignment(Pos.CENTER_RIGHT);
		
		Hyperlink link = new Hyperlink("www.invio.cz");
		Button b1 = new Button("Test");
		Button b2 = new Button("Test");
		
		bottomPane.setSpacing(100);
		
		b1.getStyleClass().add("basebutton");
		b2.getStyleClass().add("basebutton");
		
		bottomPane.getChildren().add(link);
		bottomPane.getChildren().add(b1);
		bottomPane.getChildren().add(b2);
		
		pane.getChildren().add(bottomPane);
		
	}
	
	
	//Set the flag to true, if the transfer to web server is complete
	private HBox createFileItem(File file, boolean isComplete){
		HBox box = new HBox();
		VBox wrapper = new VBox();
		wrapper.setSpacing(5);
		box.setSpacing(5);
		if(isComplete){
			Image envelopeImage = new Image(SendingTab.class.getResourceAsStream("/envlope.png"));
			ImageView envelopeView = new ImageView(envelopeImage);
			box.getChildren().add(envelopeView);
		}
		
		BasicFileAttributes attr;
		try {
			attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			Label fileName = new Label();
			fileName.getStyleClass().add("textfileitem");
			fileName.setText(file.getName());
			
			FileTime time = attr.creationTime();
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time.toMillis());
			SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd kk:mm:ss");
			String dateString = format.format(cal.getTime());
			
			Label timeLabel = new Label();
			timeLabel.setText(dateString);
			
			wrapper.getChildren().add(fileName);
			wrapper.getChildren().add(timeLabel);
			box.getChildren().add(wrapper);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return box;
	}
}
