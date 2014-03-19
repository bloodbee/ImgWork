import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


class LoadImage extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage img = null;
	ImageIcon icon;
	
	int nbThread = 1;
	
	/*
	 * Constrcteur !
	 */
	public LoadImage() {
		try {
	        img = ImageIO.read(new File("doc/imgbase.jpg"));
	        icon = new ImageIcon(img);
	    } catch (IOException e) {
	    	System.out.println("Erreur création image base");
	    }
	}
	
	public LoadImage(String path) {
		try {
			if(path.matches(".*\\.jpg") || path.matches(".*\\.png") || path.matches(".*\\.gif")) {
				img = ImageIO.read(new File(path));
				icon = new ImageIcon(img);
			}
			else {
				img = ImageIO.read(new File("doc/imgbase.jpg"));	
		        icon = new ImageIcon(img);
			}
		} catch(IOException e) {
			System.out.println("Erreur création image");
		}
	}
	
	public LoadImage(BufferedImage img, int thread) {
		this.img = img;
		nbThread = thread;
		this.icon = new ImageIcon(img);
	}
}
