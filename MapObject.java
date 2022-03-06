
/**
 * @title MapObject
 * 
 * @description Represents an object on the map, including it's position and image dimensions.
 * 
 * @author Emmett Rogers
 * @course CIT 591, Final Project
 * @date 2020-04-25
 */

public class MapObject {

	private double mapObjectX;
	private double mapObjectY;
	private double mapObjectWidth = 64.0;
	private double mapObjectHeight = 64.0;
	private double rotationDegrees;
	private String imageFile;

	/**
	 * Constructor with x and y set
	 * @param x position on horizontal axis in pixels
	 * @param y position on vertical axis in pixels
	 */
	public MapObject(double x, double y) {

		this.mapObjectX = x;
		this.mapObjectY = y;
	}
	
	/**
	 * Constructor with x, y, rotation needed, and image
	 * @param x position on horizontal axis in pixels
	 * @param y position on vertical axin in pixels
	 * @param rotationDegrees amount to rotate image in degrees
	 * @param imageFile location of image
	 */
	public MapObject(double x, double y, double rotationDegrees, String imageFile) {
		
		this.mapObjectX = x;
		this.mapObjectY = y;
		this.rotationDegrees = rotationDegrees;
		this.imageFile = imageFile;
	}
	
	/**
	 * Draw map object to the screen.
	 */
	public void drawObject() {
		
		PennDraw.picture(mapObjectX, mapObjectY, imageFile, mapObjectWidth, mapObjectHeight, rotationDegrees);
	}
	
	/**
	 * Get x of this MapObject.
	 * @return x position in pixels
	 */
	public double getMapObjectX() {

		return mapObjectX;
	}

	/**
	 * Get y of this MapObject.
	 * @return y position in pixels
	 */
	public double getMapObjectY() {

		return mapObjectY;
	}
}