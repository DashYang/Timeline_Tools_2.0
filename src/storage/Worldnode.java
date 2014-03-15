package storage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Worldnode {
	private int id;
	private String name;
	private String creator;
	private String creattime;
	private int time_headnode;
	
	public Worldnode() {
		super();
		this.name = "unknown";
		this.creator = "unknown";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		this.creattime = time;
	}
	
	public int getTime_headnode() {
		return time_headnode;
	}
	public void setTime_headnode(int time_headnode) {
		this.time_headnode = time_headnode;
	}
	public Worldnode(int id, String name, String creator, String creattime) {
		super();
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.creattime = creattime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	
}
