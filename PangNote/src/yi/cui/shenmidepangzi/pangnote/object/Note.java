package yi.cui.shenmidepangzi.pangnote.object;

public class Note {
	public static int TYPE_NOTE = 1;
	public static int TYPE_AECHIVED = 2;
	public static int TYPE_DELETED = 3;
	
	private long id;
	private String content;
	private int type;
	private String time;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return time + "\n" + content;
	}
}
