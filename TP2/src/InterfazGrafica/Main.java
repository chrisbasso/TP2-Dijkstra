package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import DataModel.Persistencia;
import PartesMina.Mina;
import Solver.CapaVectorial;

public class Main {

	private JFrame frame;
	public static Mapa elMapa;
	private PanelInferior panelInferior;
	private Mina mina;
	private CapaVectorial capaVectorial;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		initialize();
	}


	private void initialize() {

		frame = new JFrame();
		frame.setTitle("EN BUSCA DE LA MINA PERDIDA");
		frame.setBounds(100, 100, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		try {
			// Lee los datos de la mina guardados
			mina = (Mina) Persistencia.leeObjeto(Persistencia.ARCHIVO_DATOS);
			//El look and Feel
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//mina = new Mina();
			
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		//Crea el Mapa y lo muestra
		elMapa = new Mapa(); 
		frame.getContentPane().add(elMapa, BorderLayout.CENTER);
		
		//Crea el dibujador y dibuja los elementos de la mina
		capaVectorial = new CapaVectorial();
		capaVectorial.dibujarTodosLosPois(mina, elMapa);
		capaVectorial.dibujarTodosLosTuneles(mina, elMapa, Color.BLUE);
		
		//Crea el panel Inferior y lo muestra
		panelInferior = new PanelInferior(mina, elMapa);
		frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);

		//Toma el click del Mouse para setear coordenadas
		new DefaultMapController(elMapa) {

			@Override
			public void mouseClicked(MouseEvent e) {
				panelInferior.setCoordenadas(elMapa.getPosition(e.getPoint()));
				
				
			}
		};

	}
}
