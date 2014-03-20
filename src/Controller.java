//THE CONTROLLER

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 * @author Dufour Mathieu
 * @version 1
 * Classe Controlleur implémentant la classe Observer et faisant le lien entre notre modèle et notre vue
 */

class Controller implements Observer{
	/**
	 * Notre vue
	 */
	public FirstVue vue;
	/**
	 * Notre image
	 */
	public LoadImage image;
	/**
	 * Notre image qui sera sauvegardée
	 */
	public LoadImage imagesave;
	/**
	 * Le chemin d'accès à l'image à ouvrir ou à sauvegarder
	 */
	public String path;
	
	/**
	 * Constructeur de notre controlleur
	 * @param img Modèle utilisé dans par notre controlleur
	 * @see LoadImage
	 */
	public Controller(LoadImage img) {
		this.vue = new FirstVue(this);
		
		this.image = img;
		
		this.vue.imgLabel.setIcon(this.image.icon);
		
		this.vue.pane1.setSize(this.image.getSize());
		this.vue.frame1.setVisible(true);
	}
	

	@Override
	/**
	 * Fonction d'update, pas utilisée.
	 * @see Observer
	 */
	public void update(Observable o, Object arg) {	
	}
	
	/**
	 * Fonction appelée lorsque click sur le MenuItem ItemOuvrirImage
	 * Permet d'ouvrir une image
	 */
	public void onItemOuvrirImageClicked() {		
		vue.fileChooser.setCurrentDirectory(new File("doc"));
		if(vue.fileChooser.showOpenDialog(vue.frame1) == JFileChooser.APPROVE_OPTION) {
			path = vue.fileChooser.getSelectedFile().getAbsolutePath();				
			image = new LoadImage(path);
			this.vue.imgLabel.setIcon(this.image.icon);
		}
		else {
			System.out.println("Erreur ouverture image");
		}
	}
	
	/**
	 * Fonction appelée lorsque click sur le MenuItem ItemSaveImage
	 * Permet d'enregistrer une image
	 */
	public void onItemSaveImageClicked() {
		vue.fileChooser.setCurrentDirectory(new File("doc"));
		if(vue.fileChooser.showSaveDialog(vue.frame1) == JFileChooser.APPROVE_OPTION) {
			path = vue.fileChooser.getSelectedFile().getAbsolutePath();	
			String [] tab = vue.fileChooser.getSelectedFile().getAbsolutePath().split("\\.");
			String ext = tab[tab.length-1];
			
			File savedFile = vue.fileChooser.getSelectedFile();
			if(ext.matches("jpg") | ext.matches("png") | ext.matches("gif")) {
				try {
					ImageIO.write(imagesave.img, ext, savedFile);
				} catch (IOException e) {
					System.out.println("Erreur sauvegarde image");
				}
			}
			else System.out.println("Mauvaise extension !");
		}
	}
	
	/**
	 * Fonction appelée lorsque click sur le MenuItem ItemQuitter
	 * Quitte l'application
	 */
	public void onItemQuitterClicked() {	
		vue.frame1.dispose();		
	}
	
	/**
	 * Fonction appelée lorsque click sur le MenuItem ItemConfig
	 * Affiche le dialog de configuration
	 */
	public void onItemConfigClicked() {
		vue.conf.setVisible(true);
	}
	
	/**
	 * Fonction appelée lorsque click sur le bouton ButtonConfAnnul
	 * Annule la configuration
	 */
	public void onButtonConfAnnulClicked() {
		vue.conf.dispose();
	}
	
	/**
	 * Fonction appelée lorsque click sur le bouton ButtonConfOk
	 * Assigne le nombre de thread à utiliser dans les traitements
	 */
	public void onButtonConfOkClicked() {
		
		image.nbThread = (int) vue.threadSpin.getValue();
		if(image.nbThread <= 0)
			image.nbThread = 1;
		vue.conf.dispose();
	}
	
	/**
	 * Fonction appelée lorsque click sur le bouton ButtonGreen
	 * Effectue le traitement vert sur l'image affichée
	 */
	public void onButtonGreenClicked() {
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreRGB []t = new FiltreRGB[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			t[i] = new FiltreRGB(image.img, imageDest, minWidth, minHeight, maxWidth, maxHeight, 0xFF00FF00);
	
			
			t[i].start();
			
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			minHeight = maxHeight;
			nbFois--;
		}
		
		vue.imgLabel.setIcon(new ImageIcon(imageDest));
		imagesave = new LoadImage(imageDest, image.nbThread);
	}
	
	/**
	 * Fonction appelée lorsque click sur le bouton ButtonRed
	 * Effectue le traitement rouge sur l'image affichée
	 */
	public void onButtonRedClicked() {		
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreRGB []t = new FiltreRGB[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			t[i] = new FiltreRGB(image.img, imageDest, minWidth, minHeight, maxWidth, maxHeight, 0xFFFF0000);
	
			t[i].start();
			
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			minHeight = maxHeight;
			nbFois--;
		}
		vue.imgLabel.setIcon(new ImageIcon(imageDest));
		imagesave = new LoadImage(imageDest, image.nbThread);
	}

	/**
	 * Fonction appelée lorsque click sur le bouton ButtonBlue
	 * Effectue le traitement bleu sur l'image affichée
	 */
	public void onButtonBlueClicked() {
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreRGB []t = new FiltreRGB[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			
			t[i] = new FiltreRGB(image.img, imageDest, minWidth, minHeight, maxWidth, maxHeight, 0xFF0000FF);
		
			t[i].start();
	
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			minHeight = maxHeight;
			nbFois--;
		}
		
		vue.imgLabel.setIcon(new ImageIcon(imageDest));
		imagesave = new LoadImage(imageDest, image.nbThread);
	}
	
	/**
	 * Fonction appelée lorsque click sur le bouton ButtonGris
	 * Effectue le traitement gris sur l'image affichée
	 */
	public void onButtonGrisClicked() {
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreGris []t = new FiltreGris[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			t[i] = new FiltreGris(image.img, imageDest, minWidth, minHeight, maxWidth, maxHeight);
	
			t[i].start();
	
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			minHeight = maxHeight;
			nbFois--;
		}
		vue.imgLabel.setIcon(new ImageIcon(imageDest));
		imagesave = new LoadImage(imageDest, image.nbThread);
	}
	
	/**
	 * Fonction appelée lorsque click sur le bouton ButtonOriginal
	 * Affiche l'image de base
	 */
	public void onButtonOriginalClicked() {
		vue.imgLabel.setIcon(new ImageIcon(image.img));
	}
}
