package rühmatöö2;

import javafx.scene.shape.Circle;


public class InimeseKujutis extends Circle {
	
	private int x;
	private int y;
	
	
	public InimeseKujutis(double centerX, double centerY, double radius, int x, int y) {
		super(centerX, centerY, radius);
		this.x = x;
		this.y = y;
	}

	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
