package application;

import java.util.Date;

public class Data {
	
	public static final int MAX_FILE_NAME_CHAR = 30; 
	public static final String ID = "id";
	public static final String FILE_NAME = "file_name";
	public static final String FILE = "file";
	public static final String DATE_SENDING = "date_sending";
	public static final String SERVER_ID = "server_id";
	
	private int id;
	private String fileName;
	private byte[] file;
	private String sendingDate;
	private Long serverId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getSendingDate() {
		return sendingDate;
	}
	public void setSendingDate(String sendingDate) {
		this.sendingDate = sendingDate;
	}
	public Long getServerId() {
		return serverId;
	}
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	
	
}
