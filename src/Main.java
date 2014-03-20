
/**
 * @author Dufour Mathieu
 * @version 1
 * Classe Main lançant l'application et déclarant notre modèle et notre controlleur
 */
public class Main {

	//Main1
	public static void main(String[] param) {
		
		
		
		//create model
		LoadImage img = new LoadImage();
		
		//create controller
		Controller controller = new Controller(img);
		

		
	}
}
