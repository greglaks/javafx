package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;

public class Util {
	
	public static String getTodayTime(){
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
		String now = dateFormat.format(d);
		return now;
	}
	
	public static byte[] convertFileToByteArray(File file){
		byte[] result=null;
        FileInputStream fileInStr=null;
        try{
            
            fileInStr=new FileInputStream(file);
            long fileSize=file.length();
            
            if(fileSize>Integer.MAX_VALUE){
                return null;
            }
            
            if(fileSize>0){
                result=new byte[(int)fileSize];
                fileInStr.read(result);
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                fileInStr.close();
            } catch (Exception e) {
            }
        }
        return result;

	}
	
	public static Image getOnlineImage(){
		 Image onLineImage = new Image(Util.class.getResourceAsStream("/circle_green.png"));
		 return onLineImage;
	}
	public static Image getOffnlineImage(){
		 Image offLineImage = new Image(Util.class.getResourceAsStream("/circle_red.png"));
		 return offLineImage;
	}
}
