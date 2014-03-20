import java.awt.image.BufferedImage;

/**
 * @author Dufour Mathieu
 * @version 1
 * Classe FiltreGris de nos traitements, hérite de la classe abstraite FiltreBuilder
 * Effectue un traitement de gris sur notre image
 * @see FiltreBuilder
 */

class FiltreGris extends FiltreBuilder {

	/**
	 * Constructeur de la classe FiltreGris, appel le constructeur de la classe mère FiltreBuilder
	 * @param imag Image de base
	 * @param imagDest Image qui sera modifié
	 * @param minX 1er pixel à traiter en largeur
	 * @param minY 1er pixel à traiter en hauteur
	 * @param maxX Dernier pixel à traiter en largeur
	 * @param maxY Dernier pixel à traiter en hauteur
	 */
	public FiltreGris(BufferedImage imag, BufferedImage imagDest, int minX, int minY, int maxX, int maxY) {
		super(imag, imagDest, minX, minY, maxX, maxY);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Fonction de modification d'un pixel
	 * Récupère chaque composante de couleur du pixel
	 * Modifie cette composante
	 * Additione les composantes et divise le tout par 4 afin de pouvoir obtenir une nuance de gris
	 * Assigne cette nouvelle couleur
	 * @param value Pixel à modifié 
	 * @return Pixel modifié
	 */
	int setPixel(int value) {
		int r, g, b, a;
		int pixel = value;

		int grey, newPixel;
		
		a = pixel & 0xFF000000 >> 32;
		r = (pixel & 0xFFFF0000) >> 16;
		g = (pixel & 0xFF00FF00) >> 8;
		b = (pixel & 0xFF0000FF);
		r=g=b;
	    grey = (a + r*3) / 4;
		
		newPixel = (grey << 32) | (grey << 16) | (grey << 8) | grey;
		
		return newPixel;
	}

}
