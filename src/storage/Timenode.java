package storage;

public class Timenode {
	private int id;
	private int nextnode;
	private String time_description;
	private int time_owner;
	private int event_headnode;
	private String display;
	
	public Timenode()
	{
		display = "true";
	}

	
	/***
	 * 
	 * @param nextnode
	 * @param time_description
	 * @param time_owner
	 * @param event_headnode
	 * @param display set it true
	 */
	public Timenode(int nextnode, String time_description, int time_owner,
			int event_headnode, String display) {
		super();
		this.nextnode = nextnode;
		this.time_description = time_description;
		this.time_owner = time_owner;
		this.event_headnode = event_headnode;
		this.display = display;
	}

	
	
	public Timenode(int id, int nextnode, String time_description,
			int time_owner, int event_headnode, String display) {
		super();
		this.id = id;
		this.nextnode = nextnode;
		this.time_description = time_description;
		this.time_owner = time_owner;
		this.event_headnode = event_headnode;
		this.display = display;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNextnode() {
		return nextnode;
	}

	public void setNextnode(int nextnode) {
		this.nextnode = nextnode;
	}

	public String getTime_description() {
		return time_description;
	}

	public void setTime_description(String time_description) {
		this.time_description = time_description;
	}

	public int getTime_owner() {
		return time_owner;
	}

	public void setTime_owner(int time_owner) {
		this.time_owner = time_owner;
	}

	public int getEvent_headnode() {
		return event_headnode;
	}

	public void setEvent_headnode(int event_headnode) {
		this.event_headnode = event_headnode;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	
}
