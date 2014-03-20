import java.awt.image.BufferedImage;

/**
 * @author Dufour Mathieu
 * @version 1
 * Classe abstraite FiltreBuilder de nos traitements, implémente les threads
 * @see Thread
 */

public abstract class FiltreBuilder extends Thread{
	/**
	 * Le 1er pixel en largeur à traiter de notre image
	 */
	int minX;
	/**
	 * Le 1er pixel en hauteur à traiter de notre image
	 */
	int minY;
	/**
	 * Le dernier pixel en largeur à traiter de notre image
	 */
	int maxX;
	/**
	 * Le dernier pixel en hauteur à traiter de notre image
	 */
	int maxY;
	/**
	 * L'image de base de notre application 
	 * Lors du lancement de l'appli ou de l'ouverture d'une nouvelle image
	 */
	BufferedImage imageBase;
	/**
	 * Image sur laquelle seront effectué les traitements, c'est une copie de l'image de base 
	 */
	BufferedImage imageTmp;	
	
	
	/**
	 * Constructeur de notre classe FiltreBuilder qui définit le traitement à effectué et la zone de l'image à traiter
	 * @param imag Image de base
	 * @param imagDest Image qui sera modifié
	 * @param minX 1er pixel à traiter en largeur
	 * @param minY 1er pixel à traiter en hauteur
	 * @param maxX Dernier pixel à traiter en largeur
	 * @param maxY Dernier pixel à traiter en hauteur
	 */
	public FiltreBuilder(BufferedImage imag, BufferedImage imagDest, int minX,int minY, int maxX, int maxY){
		this.minX = minX; 
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		if(!(imag.equals(null))){
			imageBase = imag;
			imageTmp = imagDest;
		}
	}
	
	/**
	 * Fonction de modification d'un pixel
	 * @param value Pixel à modifié 
	 * @return Pixel modifié
	 */
	abstract int setPixel(int value); // pour rgb > fait un & logique 
									  // pour gris > la moyenne
	@Override
	/**
	 * Fonction de traitement de notre image
	 * Modifie chaque pixel dans la zone définie par minX, minY, maxX et maxY
	 */
	public void run(){
		int oldPixel = 0, newPixel = 0;
		int i, j;	
		
		for(i = minX; i < maxX; ++i) {
			for(j = minY; j < maxY; ++j) {
				
				oldPixel = imageBase.getRGB(i, j);
				imageTmp.setRGB(i, j, oldPixel);
				newPixel = setPixel(oldPixel);
				
				imageTmp.setRGB(i, j, newPixel);
			}
		}
		
	}
}
