package storage;

public class Eventnode {
	private int id;
	private int owner;
	private String event_description;
	private String display;
	
	public Eventnode(){
		display = "true";
	}
	
	public Eventnode(int owner,String e_description){
		this.owner = owner;
		event_description = e_description;
		display = "true";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getEvent_description() {
		return event_description;
	}
	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	
	
}
