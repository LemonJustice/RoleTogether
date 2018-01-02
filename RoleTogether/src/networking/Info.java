package networking;

import java.io.Serializable;

public class Info implements Serializable{
	float x;
	float y;
	private static final long serialVersionUID = 1L;
	
	public Info(float x, float y/*more information should be added here*/) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public String XtoString() {
		return Float.toString(x);
	}
	
	public String YtoString() {
		return Float.toString(y);
	}
}
