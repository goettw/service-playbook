package serviceplaybook.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"bytes"})
public class FileMeta {

	private String fileName;
	private String fileSize;
	private String fileType;
	private String id;
	private String URL;
	private MongoLocalEntity source;
	
	public MongoLocalEntity getSource() {
		return source;
	}
	public void setSource(MongoLocalEntity source) {
		this.source = source;
	}
	public String getUrl() {
		return URL;
	}
	public void setUrl(String uRL) {
		URL = uRL;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private byte[] bytes;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
