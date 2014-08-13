package storage;

public class Imagenode {
	private int id;
	private String title;
	private String content;
	private String url;
	private String md5;
	private String source;
	
	
	public Imagenode(int id, String title, String content, String url,
			String md5, String source) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.url = url;
		this.md5 = md5;
		this.source = source;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
