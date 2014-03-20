import java.awt.image.BufferedImage;

/**
 * @author Dufour Mathieu
 * @version 1
 * Classe FiltreRGB de nos traitements, h�rite de la classe abstraite FiltreBuilder
 * Effectue un traitement de couleur sur notre image
 * @see FiltreBuilder
 */

class FiltreRGB extends FiltreBuilder {
	/**
	 * Type de couleur � appliquer sur un pixel
	 */
	int masque;
	
	
	/**
	 * Constructeur de la classe FiltreRGB, appel le constructeur de la classe m�re FiltreBuilder
	 * @param imag Image de base
	 * @param imagDest Image qui sera modifi�
	 * @param minX 1er pixel � traiter en largeur
	 * @param minY 1er pixel � traiter en hauteur
	 * @param maxX Dernier pixel � traiter en largeur
	 * @param maxY Dernier pixel � traiter en hauteur
	 */
	public FiltreRGB(BufferedImage imag, BufferedImage imagDest, int debutX, int debutY, int finX,
			int finY, int masque) {
		super(imag, imagDest, debutX, debutY, finX, finY);
		this.masque = masque;
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Fonction de modification d'un pixel
	 * R�cup�re la composante en rgb du pixel
	 * Modifie cette composante avec un masque (rouge, vert ou bleu)
	 * Assigne cette nouvelle couleur
	 * @param value Pixel � modifi� 
	 * @return Pixel modifi�
	 */
	int setPixel(int value) {
		int pixel = 0;
		
		pixel = value & this.masque;
		
		return pixel;
	}

}
