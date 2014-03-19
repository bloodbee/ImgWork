import java.awt.image.BufferedImage;


public abstract class FiltreBuilder extends Thread{

	int minX;
	int minY;
	int maxX;
	int maxY;
	BufferedImage imageBase;
	BufferedImage imageTmp;
	int nbThread = 0;
	int nbFois = 0;

	
	
	
	public FiltreBuilder(BufferedImage imag, BufferedImage imagDest, int thread, int minX,int minY, int maxX, int maxY){
		this.minX = minX; 
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		nbThread = thread;
		if(!(imag.equals(null))){
			imageBase = imag;
			imageTmp = imagDest;
		}
	}
	
	abstract int setPixel(int value); // pour rgb > fait un & logique 
									  // pour gris > la moyenne
	@Override
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
