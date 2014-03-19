import java.awt.image.BufferedImage;


class FiltreRGB extends FiltreBuilder {
	int masque;
	
	public FiltreRGB(BufferedImage imag, BufferedImage imagDest, int thread, int debutX, int debutY, int finX,
			int finY, int masque) {
		super(imag, imagDest, thread, debutX, debutY, finX, finY);
		this.masque = masque;
		// TODO Auto-generated constructor stub
	}

	@Override
	int setPixel(int value) {
		int pixel = 0;
		
		pixel = value & this.masque;
		
		return pixel;
	}

}
