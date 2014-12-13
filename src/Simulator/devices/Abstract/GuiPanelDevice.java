package Simulator.devices.Abstract;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GuiPanelDevice  extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019594441502457648L;
	/**
	 * 
	 */
	private ImageIcon mImage;
	
	protected GuiPanelDevice(String iconPath){
		super("");
		this.setVisible(true);
		this.setSize(110, 55);
		this.setImage(iconPath);
		//this.repaint();
		//
	}
	
	/**
	 * 
	 * @return
	 */
	public String getImage(){
		return mImage.toString();
	}
	/**
	 * 
	 * @param sPath
	 */
	private void setImage(String sPath){
		File oFile = new File(sPath);
		if(oFile.exists()){
			mImage = ScaleImage(new ImageIcon(sPath), getSize().width, getSize().height) ;
			setIcon(mImage);
		}
		oFile = null;
	}
	
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
