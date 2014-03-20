import java.awt.image.BufferedImage;

/**
 * @author Dufour Mathieu
 * @version 1
 * Classe abstraite FiltreBuilder de nos traitements, impl�mente les threads
 * @see Thread
 */

public abstract class FiltreBuilder extends Thread{
	/**
	 * Le 1er pixel en largeur � traiter de notre image
	 */
	int minX;
	/**
	 * Le 1er pixel en hauteur � traiter de notre image
	 */
	int minY;
	/**
	 * Le dernier pixel en largeur � traiter de notre image
	 */
	int maxX;
	/**
	 * Le dernier pixel en hauteur � traiter de notre image
	 */
	int maxY;
	/**
	 * L'image de base de notre application 
	 * Lors du lancement de l'appli ou de l'ouverture d'une nouvelle image
	 */
	BufferedImage imageBase;
	/**
	 * Image sur laquelle seront effectu� les traitements, c'est une copie de l'image de base 
	 */
	BufferedImage imageTmp;	
	
	
	/**
	 * Constructeur de notre classe FiltreBuilder qui d�finit le traitement � effectu� et la zone de l'image � traiter
	 * @param imag Image de base
	 * @param imagDest Image qui sera modifi�
	 * @param minX 1er pixel � traiter en largeur
	 * @param minY 1er pixel � traiter en hauteur
	 * @param maxX Dernier pixel � traiter en largeur
	 * @param maxY Dernier pixel � traiter en hauteur
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
	 * @param value Pixel � modifi� 
	 * @return Pixel modifi�
	 */
	abstract int setPixel(int value); // pour rgb > fait un & logique 
									  // pour gris > la moyenne
	@Override
	/**
	 * Fonction de traitement de notre image
	 * Modifie chaque pixel dans la zone d�finie par minX, minY, maxX et maxY
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
