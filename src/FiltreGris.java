import java.awt.image.BufferedImage;


class FiltreGris extends FiltreBuilder {

	public FiltreGris(BufferedImage imag, BufferedImage imagDest, int thread, int minX, int minY, int maxX, int maxY) {
		super(imag, imagDest, thread, minX, minY, maxX, maxY);
		// TODO Auto-generated constructor stub
	}

	@Override
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
