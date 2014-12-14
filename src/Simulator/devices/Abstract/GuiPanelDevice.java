package Simulator.devices.Abstract;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Abstract class of the GUI object of the Devices.
 * @created 14.12.2014
 * @author Michael Huber
 * @version 1.0
 */
public class GuiPanelDevice  extends JLabel {
	/**
	 * Serial ID for GUI objects
	 */
	private static final long serialVersionUID = -8019594441502457648L;
	/**
	 * the Icon object which saves the Image for display purpose
	 */
	private ImageIcon mImage;
	/**
	 * Constructor of the class
	 * @param iconPath the path to the image which should be loaded
	 */
	protected GuiPanelDevice(String iconPath){
		super("");
		this.setVisible(true);
		this.setSize(110, 55);
		this.setImage(iconPath);
		//this.repaint();
		//
	}
	/**
	 * get the path to the loaded image
	 * @return the path to the image
	 */
	public String getImage(){
		return mImage.toString();
	}
	/**
	 * set the image path and load it into the label
	 * @param sPath the path to the image
	 */
	private void setImage(String sPath){
		File oFile = new File(sPath);
		if(oFile.exists()){
			mImage = ScaleImage(new ImageIcon(sPath), getSize().width, getSize().height) ;
			setIcon(mImage);
		}
		oFile = null;
	}
	/**
	 * This function scales the loaded image to the given size
	 * @param oImage image object
	 * @param iNewWidth the width the image should be sized
	 * @param iNewHeight the height the image should be sized
	 * @return the resized image object
	 */
	private ImageIcon ScaleImage(ImageIcon oImage, Integer iNewWidth, Integer iNewHeight){
		//Load the Image into the Buffer and resize the whole Image
		Image img = oImage.getImage();
		BufferedImage bi = new BufferedImage(iNewWidth, iNewHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0,0, iNewWidth, iNewHeight, null);
		//Load the resized Image into the Icon object
		oImage = new ImageIcon(bi);
		return oImage;
	}
}
