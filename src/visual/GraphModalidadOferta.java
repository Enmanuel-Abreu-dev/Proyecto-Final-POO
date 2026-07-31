package visual;

import java.util.ArrayList;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logico.BolsaTrabajo;
import logico.Oferta;

public class GraphModalidadOferta extends JPanel {

	/**
	 * Create the panel.
	 */
	public GraphModalidadOferta() {
		DefaultPieDataset data = new DefaultPieDataset();
		ArrayList<Integer> cantidades = getCantOfertasModalidad();
		
		data.setValue("Tiempo completo", cantidades.get(0));
		data.setValue("Medio tiempo", cantidades.get(1));
		data.setValue("Becas / practicas", cantidades.get(2));
		data.setValue("Por horas", cantidades.get(3));
		
		JFreeChart chart = ChartFactory.createPieChart("Ofertas por modalidad", data, true, true, false);
		ChartPanel panel = new ChartPanel(chart);
		this.add(panel);
	}
	
	private ArrayList<Integer> getCantOfertasModalidad() {
		ArrayList<Oferta> ofertas = BolsaTrabajo.getInstance().getOfertas();
		ArrayList<Integer> cantidades = new ArrayList<Integer>();
		int tiempoCompleto = 0, medioTiempo = 0, becas = 0, porHoras = 0; 
		
		
		for (Oferta actual : ofertas) {
			if (actual.getModalidad().equalsIgnoreCase("Tiempo completo")) 
				tiempoCompleto++;
			if (actual.getModalidad().equalsIgnoreCase("Medio completo"))
				medioTiempo++;
			if (actual.getModalidad().equalsIgnoreCase("Becas / Practicas"))
				becas++;
			if (actual.getModalidad().equalsIgnoreCase("Por horas"))
				porHoras++;
		}
		
		cantidades.add(tiempoCompleto);
		cantidades.add(medioTiempo);
		cantidades.add(becas);
		cantidades.add(porHoras);
		return cantidades;
	}

}
