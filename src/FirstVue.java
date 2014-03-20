//THE VUE

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;

/**
 * @author Dufour Mathieu
 * @version 1
 * Classe FirstVue implémentant l'interface graphique
 */

class FirstVue implements ActionListener{
	/**
	 * Controlleur
	 */
	public Controller controller;
	/**
	 * Frame et Dialog
	 */
	public JFrame frame1;
	public JDialog conf;
	/**
	 * Panel
	 */
	public JPanel pane1;
	public JPanel pane2;
	public JPanel pane3;
	/**
	 * FileCHooser
	 */
	public JFileChooser fileChooser;
	/**
	 * Menu et MenuItem
	 */
	public JMenuBar menuBar;
	public JMenu menu;
	public JMenu menu2;
	public JMenuItem itemOuvrirImage;
	public JMenuItem itemSaveImage;
	public JMenuItem itemQuitter;
	public JMenuItem itemConfig;
	/**
	 * Button
	 */
	public JButton redButton;
	public JButton greenButton;
	public JButton blueButton;
	public JButton grisButton;
	public JButton originalButton;
	public JButton buttonConfOk;
	public JButton buttonConfAnnul;
	/**
	 * Label
	 */
	public JLabel grisLabel;
	public JLabel imgLabel;
	public JLabel threadLabel;
	/**
	 * Spinner
	 */
	public JSpinner threadSpin;
	
	
	/**
	 * Fonction d'initialisation des elements de l'interface graphique
	 */
	private void initialiserElements() {
		//Frame
		frame1 = new JFrame("ImgWork");
		frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		conf = new JDialog(frame1, "Configuration");
		conf.setSize(300, 200);
		conf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//Panes
		pane1 = new JPanel(new BorderLayout());
		pane2 = new JPanel(new FlowLayout());
		pane3 = new JPanel(new GridLayout(2,2));
		
		
		//Menu
		menuBar = new JMenuBar();
		menu = new JMenu("Fichier");
		menu2 = new JMenu("Edit");
		
		menuBar.add(menu);
		menuBar.add(menu2);
		itemOuvrirImage = new JMenuItem("Ouvrir image");
		itemSaveImage = new JMenuItem("Enregistrer sous");
		itemQuitter = new JMenuItem("Quitter");
		itemConfig = new JMenuItem("Configuration");

		
		menu.add(itemOuvrirImage);
		menu.add(itemSaveImage);
		menu.addSeparator();
		menu.add(itemQuitter);
		
		menu2.add(itemConfig);
		
		frame1.setJMenuBar(menuBar);
		
		//File
		fileChooser = new JFileChooser();
		
		//Buttons
		redButton = new JButton("Rouge");
		greenButton = new JButton("Vert");
		blueButton = new JButton("Bleu");
		grisButton = new JButton("Noir et blanc");
		originalButton = new JButton("Originale");
		
		buttonConfAnnul = new JButton("Annuler");
		buttonConfOk = new JButton("Ok");
		
		//Slider
			
		//Label
		grisLabel = new JLabel("Niveau de gris :");
		imgLabel = new JLabel();
		threadLabel = new JLabel("Nombre de threads :");
		
		//Spinner
		threadSpin = new JSpinner();
	}
	
	/**
	 * Fonction de placement des éléments de l'interface graphique
	 */
	public void placeContents() {
		frame1.add(pane1, BorderLayout.CENTER);
		frame1.add(pane2, BorderLayout.SOUTH);
		
		pane1.add(imgLabel);
		
		pane2.add(redButton);
		pane2.add(greenButton);
		pane2.add(blueButton);
		pane2.add(grisButton);
		pane2.add(originalButton);
		
		pane3.add(threadLabel);
		pane3.add(threadSpin);
		pane3.add(buttonConfAnnul);
		pane3.add(buttonConfOk);
		
		conf.setContentPane(pane3);
		conf.setVisible(false);
	}
	
	/**
	 * Fonction de creation des actions associées à chaque éléments
	 */
	public void createActions() {
		itemOuvrirImage.addActionListener(this);
		itemSaveImage.addActionListener(this);
		itemQuitter.addActionListener(this);
		
		itemConfig.addActionListener(this);
		
		buttonConfAnnul.addActionListener(this);
		buttonConfOk.addActionListener(this);
		
		redButton.addActionListener(this);
		greenButton.addActionListener(this);
		blueButton.addActionListener(this);
		grisButton.addActionListener(this);
		originalButton.addActionListener(this);
	}
	
	/**
	 * Constructeur de la classe FirstVue
	 * @param controller Controller associé à la classe FirstVue
	 * @see Controller
	 */
	public FirstVue(Controller controller) {
		
		this.controller = controller;

		//Elements of the view
		initialiserElements();
			
		//Disposition of the panels
		placeContents();
		
		//Create actionlistener on elements
		createActions();	
		
		frame1.pack();
		frame1.setVisible(true);
	}

	/**
	 * Fonction d'affichage de la classe FirstVue
	 */
	public void affiche() {
		controller.image.repaint();
	}

	@Override
	/**
	 * Fonction faisant l'interaction entre un élément et notre controlleur
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == itemOuvrirImage)
			controller.onItemOuvrirImageClicked();
		else if(e.getSource() == itemSaveImage)
			controller.onItemSaveImageClicked();
		else if(e.getSource() == itemQuitter)
			controller.onItemQuitterClicked();
		else if(e.getSource() == itemConfig)
			controller.onItemConfigClicked();
		else if(e.getSource() == buttonConfAnnul)
			controller.onButtonConfAnnulClicked();
		else if(e.getSource() == buttonConfOk)
			controller.onButtonConfOkClicked();
		else if(e.getSource() == redButton)
			controller.onButtonRedClicked();
		else if(e.getSource() == greenButton)
			controller.onButtonGreenClicked();
		else if(e.getSource() == blueButton)
			controller.onButtonBlueClicked();
		else if(e.getSource() == grisButton)
			controller.onButtonGrisClicked();
		else if(e.getSource() == originalButton)
			controller.onButtonOriginalClicked();
	}
}
