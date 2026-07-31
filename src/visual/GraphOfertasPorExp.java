package visual;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logico.BolsaTrabajo;
import logico.Oferta;

public class GraphOfertasPorExp extends JPanel {

	/**
	 * Create the panel.
	 */
	public GraphOfertasPorExp() {
		DefaultPieDataset data = new DefaultPieDataset();
		ArrayList<Integer> cantidades = getCantidadesExp();
		
		data.setValue("1 - 3 años", cantidades.get(0));
		data.setValue("4 - 6 años", cantidades.get(1));
		data.setValue("7+ años", cantidades.get(2));
		
		JFreeChart chart = ChartFactory.createPieChart("Ofertas por años de experiencia", data, true, true, false);
		ChartPanel panel = new ChartPanel(chart);
		this.add(panel);
	}
	
	private ArrayList<Integer> getCantidadesExp () {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Oferta> ofertas = BolsaTrabajo.getInstance().getOfertas();
		int junior = 0, mid = 0, senior = 0; 
		
		for (Oferta actual : ofertas) {
			int exp = actual.getAniosExperiencia();
			if (exp >= 1 && exp <= 3)
				junior++;
			if (exp >= 4 && exp <= 6)
				mid++;
			if (exp >= 7)
				senior++;				
		}
		
		result.add(junior);
		result.add(mid);
		result.add(senior);
		return result;
	}

}
