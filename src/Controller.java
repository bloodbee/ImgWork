//THE CONTROLLER

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;


class Controller implements Observer{
	public FirstVue vue;
	public LoadImage image;
	public LoadImage imagesave;
	
	Boolean isFiltrer = false;
	
	public String path;
	
	public Controller(LoadImage img) {
		this.vue = new FirstVue(this);
		
		this.image = img;
		
		this.vue.imgLabel.setIcon(this.image.icon);
		
		this.vue.pane1.setSize(this.image.getSize());
		this.vue.frame1.setVisible(true);
	}
	

	@Override
	public void update(Observable o, Object arg) {	
	}
	
	
	
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
	
	public void onItemQuitterClicked() {	
		vue.frame1.dispose();		
	}
	
	public void onItemConfigClicked() {
		vue.conf.setVisible(true);
	}
	
	
	public void onButtonConfAnnulClicked() {
		vue.conf.dispose();
	}
	
	public void onButtonConfOkClicked() {
		image.nbThread = (int) vue.threadSpin.getValue();
		vue.conf.dispose();
	}
	
	
	public void onButtonGreenClicked() {
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreRGB []t = new FiltreRGB[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			t[i] = new FiltreRGB(image.img, imageDest, image.nbThread, minWidth, minHeight, maxWidth, maxHeight, 0xFF00FF00);
	
			
			t[i].start();
			
			try {
				t[i].join();
				isFiltrer = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			minHeight = maxHeight;
			nbFois--;
		}
		
		vue.imgLabel.setIcon(new ImageIcon(imageDest));
		imagesave = new LoadImage(imageDest, image.nbThread);
	}
	
	public void onButtonRedClicked() {		
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreRGB []t = new FiltreRGB[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			t[i] = new FiltreRGB(image.img, imageDest, image.nbThread, minWidth, minHeight, maxWidth, maxHeight, 0xFFFF0000);
	
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

	public void onButtonBlueClicked() {
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreRGB []t = new FiltreRGB[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			
			t[i] = new FiltreRGB(image.img, imageDest, image.nbThread, minWidth, minHeight, maxWidth, maxHeight, 0xFF0000FF);
		
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
	
	public void onButtonGrisClicked() {
		
		BufferedImage imageDest = new BufferedImage(image.img.getWidth(), image.img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int nbFois = 0;
		nbFois =  image.nbThread;
		FiltreGris []t = new FiltreGris[image.nbThread];
		
		int minWidth=0, minHeight=0, maxWidth=0, maxHeight=0;

		for(int i = 0; i< image.nbThread; i++) {
			
			maxWidth = image.img.getWidth();
			maxHeight = image.img.getHeight() - (image.img.getHeight() - image.img.getHeight()/nbFois);
			t[i] = new FiltreGris(image.img, imageDest, image.nbThread, minWidth, minHeight, maxWidth, maxHeight);
	
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
	
	public void onButtonOriginalClicked() {
		vue.imgLabel.setIcon(new ImageIcon(image.img));
	}
}
