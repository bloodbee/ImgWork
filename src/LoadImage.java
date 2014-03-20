import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author Dufour Mathieu
 * @version 1
 * Classe LoadImage repr�sentant notre mod�le
 */

class LoadImage extends Component {
	/**
	 * VersionUID de la classe
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Image sur laquelle sont effectu� les traitements
	 */
	BufferedImage img = null;
	/**
	 * ImageIcon servant � afficher img
	 */
	ImageIcon icon;
	/**
	 * Nombre de threads utilis�s dans notre application pour les traitements
	 */
	int nbThread = 1;
	
	/**
	 * Constructeur de la classe LoadImage
	 * Permet l'ouverture d'une image de base
	 */
	public LoadImage() {
		try {
	        img = ImageIO.read(new File("doc/imgbase.jpg"));
	        icon = new ImageIcon(img);
	    } catch (IOException e) {
	    	System.out.println("Erreur cr�ation image base");
	    }
	}
	
	/**
	 * Constructeur de la classe LoadImage
	 * Permet l'ouverture d'une image dont le chemin est pass� en param�tre
	 * @param path Chemin de l'image
	 */
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
			System.out.println("Erreur cr�ation image");
		}
	}
	
	/**
	 * Constructeur de la classe LoadImage
	 * Permet la copie d'une BufferedImage pass�e en param�tre
	 * @param img L'image � copier
	 * @param thread Le nombre de thread utilis�s dans les traitements de notre application
	 */
	public LoadImage(BufferedImage img, int thread) {
		this.img = img;
		nbThread = thread;
		this.icon = new ImageIcon(img);
	}
}
