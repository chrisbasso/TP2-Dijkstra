package InterfazGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;


import org.openstreetmap.gui.jmapviewer.Coordinate;

import DataModel.Persistencia;
import PartesMina.Camino;
import PartesMina.Mina;
import PartesMina.POI;
import PartesMina.Tunel;
import Solver.CapaVectorial;
import Solver.Haversine;
import Solver.InteligenciaMapa;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;

public class PanelInferior extends JPanel {

	private static final long serialVersionUID = -3206661824793818913L;
	private JTextField textLong;
	private JTextField textLat;
	private Mina mina;
	private JComboBox<String> comboBoxOrigen = new JComboBox<String>();
	private JComboBox<String> comboBoxDestino = new JComboBox<String>();
	private JComboBox<String> comboBoxEliminarPOI = new JComboBox<String>();
	private JComboBox<String> comboBoxCaminoHasta = new JComboBox<String>();
	private JComboBox<String> comboBoxCaminoDesde = new JComboBox<String>();
	private JComboBox<String> comboBoxExtraerDesde = new JComboBox<String>();
	private JComboBox<String> comboBoxVel = new JComboBox<String>();
	private JLabel lblCantCarbon = new JLabel("0");
	private JLabel labelVel = new JLabel("");
	private JLabel lblValorDistancia = new JLabel("");
	private CapaVectorial capaVectorial;
	private InteligenciaMapa inteligenciaMapa;
	private int carbonAcumulado = 0;

	public PanelInferior(Mina mina, Mapa mapa) {

		this.mina = mina;
		this.capaVectorial = new CapaVectorial();
		this.inteligenciaMapa = new InteligenciaMapa();

		setLayout(new MigLayout("", "[97px][][grow][][grow]", "[23px][][][][][][]"));

		JButton btnAgregarPoi = new JButton("Agregar POI");
		btnAgregarPoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(btnAgregarPoi, "cell 0 0,growx,aligny center");

		btnAgregarPoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				agregaPOI(mina, mapa);

			}

		});

		JButton btnAgregarTunel = new JButton("Agregar Tunel");
		btnAgregarTunel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(btnAgregarTunel, "cell 0 1,growx,aligny top");

		btnAgregarTunel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				agregarTunel(mina, mapa);

			}

		});

		JButton btnEliminarPOI = new JButton("Eliminar POI");
		btnEliminarPOI.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(btnEliminarPOI, "cell 0 2,growx");

		btnEliminarPOI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				eliminarMina(mina, mapa);
			}

		});

		JButton btnCaminoMinimo = new JButton("Camino Minimo");
		btnCaminoMinimo.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(btnCaminoMinimo, "cell 0 3,growx");

		btnCaminoMinimo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				caminoMin(mina, mapa);

			}

		});

		JButton btnExtraerCarbn = new JButton("Extraer Carb\u00F3n");
		btnExtraerCarbn.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(btnExtraerCarbn, "cell 0 4,growx");
		btnExtraerCarbn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				extraerCarbon(mina, mapa);

			}

		});

		// Agrega el resto de los elementos
		add(comboBoxEliminarPOI, "cell 2 2,growx");
		JLabel lblLat = new JLabel("Lat:");
		add(lblLat, "cell 1 0,alignx trailing");

		textLat = new JTextField();
		add(textLat, "cell 2 0,growx");
		textLat.setColumns(10);

		JLabel lblLong = new JLabel("Long:");
		add(lblLong, "cell 3 0,alignx trailing");

		textLong = new JTextField();
		add(textLong, "cell 4 0,growx");
		textLong.setColumns(10);

		JLabel lblOrigen = new JLabel("Origen:");
		add(lblOrigen, "cell 1 1,alignx trailing");

		add(comboBoxOrigen, "cell 2 1,growx");

		JLabel lblDestino = new JLabel("Destino:");
		add(lblDestino, "cell 3 1,alignx trailing");

		add(comboBoxDestino, "cell 4 1,growx");

		JLabel lblNombre = new JLabel("Nombre:");
		add(lblNombre, "cell 1 2,alignx trailing");

		JLabel lblDesde = new JLabel("Desde:");
		add(lblDesde, "cell 1 3,alignx trailing");

		add(comboBoxCaminoDesde, "cell 2 3,growx");

		JLabel lblHasta = new JLabel("Hasta:");
		add(lblHasta, "cell 3 3,alignx trailing");

		add(comboBoxCaminoHasta, "cell 4 3,growx");

		JLabel lblDesde_1 = new JLabel("Desde:");
		add(lblDesde_1, "cell 1 4,alignx trailing");

		add(comboBoxExtraerDesde, "cell 2 4,growx");

		JLabel lblVel = new JLabel("Vel en KM/H:");
		add(lblVel, "cell 3 4,alignx trailing");

		add(comboBoxVel, "cell 4 4,growx");

		JLabel lblCarbnExtraido = new JLabel("Carb\u00F3n Extraido:");
		lblCarbnExtraido.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCarbnExtraido, "cell 0 5,alignx right");

		lblCantCarbon.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCantCarbon, "cell 1 5,alignx right");

		lblValorDistancia.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblValorDistancia, "cell 1 6,alignx right");

		JLabel lblDistancia = new JLabel("Distancia:");
		lblDistancia.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblDistancia, "cell 0 6,alignx right");

		JLabel lblNewLabel = new JLabel("KM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel, "cell 2 6");

		JLabel lblTiempoEnMin = new JLabel("Tiempo en min:");
		add(lblTiempoEnMin, "cell 3 6");

		add(labelVel, "cell 4 6");

		setComboBoxes();

	}

	// Toma las coordenadas del click y las escribe en el panel
	public void setCoordenadas(Coordinate coordenadas) {

		textLat.setText(String.valueOf(coordenadas.getLat()));
		textLong.setText(String.valueOf(coordenadas.getLon()));

	}

	// Actualiza los comboboxes para cuando se agregan o eliminan POIs
	public void setComboBoxes() {

		comboBoxOrigen.removeAllItems();
		comboBoxDestino.removeAllItems();
		comboBoxEliminarPOI.removeAllItems();
		comboBoxCaminoDesde.removeAllItems();
		comboBoxCaminoHasta.removeAllItems();
		comboBoxExtraerDesde.removeAllItems();
		comboBoxVel.removeAllItems();

		for (POI mina : mina.getPois()) {

			comboBoxOrigen.addItem(mina.getNombre());
			comboBoxDestino.addItem(mina.getNombre());
			comboBoxEliminarPOI.addItem(mina.getNombre());
			comboBoxCaminoDesde.addItem(mina.getNombre());
			comboBoxCaminoHasta.addItem(mina.getNombre());
			comboBoxExtraerDesde.addItem(mina.getNombre());

		}

		comboBoxVel.addItem("5");
		comboBoxVel.addItem("10");
		comboBoxVel.addItem("20");
		comboBoxVel.addItem("50");
		comboBoxVel.addItem("100");

	}

	public void agregaPOI(Mina mina, Mapa mapa) {

		String nombre = JOptionPane.showInputDialog("Ingrese nombre del POI");

		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre");

		} else {

			ArrayList<String> nombresPoi = new ArrayList<String>();
			for (POI poi : mina.getPois()) {
				nombresPoi.add(poi.getNombre());
			}

			if (nombresPoi.contains(nombre)) {
				JOptionPane.showMessageDialog(null, "Ya existe el punto de interes");
			} else {
				if (textLat.getText().equals("") || textLong.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Falta ingresar Coordenadas");
				} else {

					double cantCarbon = 0;
					boolean flag = false;
					do {
						try {
							cantCarbon = Double.parseDouble(JOptionPane.showInputDialog("Ingrese cantidad de Carbon"));
							flag = true;
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Solo se permiten numeros");
							flag = false;
						}
					} while (!flag);

					if (cantCarbon < 0) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un numero positivo");
					} else {
						POI poi = new POI(nombre, Double.parseDouble(textLat.getText()), Double.parseDouble(textLong.getText()), cantCarbon);
						mina.agregarPoi(poi);

						setComboBoxes();
						capaVectorial.dibujaPoi(poi, mapa);

						Persistencia.guardaObjeto(mina, Persistencia.ARCHIVO_DATOS);
					}

				}
			}

		}

	}

	public void eliminarMina(Mina mina, Mapa mapa) {

		int indice = comboBoxEliminarPOI.getSelectedIndex();
		mina.eliminarPoiYtunelesAnidados(indice);

		setComboBoxes();
		mapa.removeAllMapMarkers();
		mapa.removeAllMapPolygons();
		capaVectorial.dibujarTodosLosPois(mina, mapa);
		capaVectorial.dibujarTodosLosTuneles(mina, mapa, Color.BLUE);

		Persistencia.guardaObjeto(mina, Persistencia.ARCHIVO_DATOS);
	}

	public void agregarTunel(Mina mina, Mapa mapa) {

		String Origen = (String) comboBoxOrigen.getSelectedItem();
		String Destino = (String) comboBoxDestino.getSelectedItem();
		boolean flag = true;

		for (Tunel tunel : mina.getTuneles()) {

			if ((tunel.getOrigen().getNombre().equals(Origen)) && tunel.getDestino().getNombre().equals(Destino)) {
				flag = false;
			}

		}

		if (flag) {

			POI poiOrigen = new POI();
			POI poiDestino = new POI();

			for (POI poi : mina.getPois()) {
				if (poi.getNombre().equals(Origen))
					poiOrigen = poi;
			}

			for (POI poi : mina.getPois()) {
				if (poi.getNombre().equals(Destino))
					poiDestino = poi;
			}

			double distancia = Haversine.distance(poiOrigen.getLatitud(), poiOrigen.getLongitud(), poiDestino.getLatitud(), poiDestino.getLongitud());
			Tunel tunel = new Tunel(poiOrigen, poiDestino, distancia);
			mina.agregarTunel(tunel);
			Persistencia.guardaObjeto(mina, Persistencia.ARCHIVO_DATOS);

			capaVectorial.dibujaTunel(tunel, mapa, Color.BLUE);
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Ya existe el tunel");
		}
	}

	public void caminoMin(Mina mina, Mapa mapa) {
		
		Set<Integer> alcanzados = inteligenciaMapa.alcanzables(comboBoxCaminoDesde.getSelectedIndex(), mina);

		if (alcanzados.contains(comboBoxCaminoHasta.getSelectedIndex())) {

			Camino caminoMin = inteligenciaMapa.caminoMinimo(comboBoxCaminoDesde.getSelectedIndex(), comboBoxCaminoHasta.getSelectedIndex(), mina);
			capaVectorial.dibujarCaminoMinimo(caminoMin, mapa);
			lblValorDistancia.setText(String.valueOf(inteligenciaMapa.getKmCamino()));
			labelVel.setText(String.valueOf(inteligenciaMapa.getKmCamino() / (Double.parseDouble(comboBoxVel.getSelectedItem().toString())) * 60));
			
		} else {
			JOptionPane.showMessageDialog(null, "No existe un camino hasta el punto " + comboBoxCaminoHasta.getSelectedItem());
		}
	}

	public void extraerCarbon(Mina mina, Mapa mapa) {

		Camino caminoMin = inteligenciaMapa.irAlCarbon(mina, comboBoxExtraerDesde.getSelectedIndex());

		if (caminoMin.getCamino().isEmpty() && mina.getPois().get(comboBoxExtraerDesde.getSelectedIndex()).getCantCarbon()==0) {
			JOptionPane.showMessageDialog(null, "No se existe un camino hasta el carbón");

		} else {
			
			capaVectorial.dibujarCaminoMinimo(caminoMin, mapa);

			int indPoiAlcanzado = caminoMin.getUltimoTramo();

			lblValorDistancia.setText(String.valueOf(inteligenciaMapa.getKmCamino()));

			labelVel.setText(String.valueOf(inteligenciaMapa.getKmCamino() / (Double.parseDouble(comboBoxVel.getSelectedItem().toString())) * 60));

			

			int resp = JOptionPane.showConfirmDialog(null, "¿Desea extraer el carbón de éste punto?", "Alerta!", JOptionPane.YES_NO_OPTION);

			if (resp == 0) {

				carbonAcumulado += mina.getPois().get(indPoiAlcanzado).getCantCarbon();
				mina.getPois().get(indPoiAlcanzado).setCantCarbon(0);
				capaVectorial.dibujaPoi(mina.getPois().get(indPoiAlcanzado), mapa);
				Persistencia.guardaObjeto(mina, Persistencia.ARCHIVO_DATOS);
				lblCantCarbon.setText(String.valueOf(carbonAcumulado));
			}

		}

	}

}
