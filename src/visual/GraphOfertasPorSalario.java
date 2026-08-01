package visual;

import java.util.ArrayList;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import logico.BolsaTrabajo;
import logico.Oferta;

public class GraphOfertasPorSalario extends JPanel {
	/**
	 * Create the panel.
	 */
	public GraphOfertasPorSalario() {
		ArrayList<Integer> cantidades = getCantidades();

		DefaultCategoryDataset data = new DefaultCategoryDataset();
		data.addValue(cantidades.get(0), "Ofertas", "$1000 a $5000");
		data.addValue(cantidades.get(1), "Ofertas", "$5000 a $10000");
		data.addValue(cantidades.get(2), "Ofertas", "$10000+");

		JFreeChart chart = ChartFactory.createBarChart("Ofertas por salario", "Rango", "Ofertas", data);
		ChartPanel panel = new ChartPanel(chart);
		this.add(panel);
	}

	private ArrayList<Integer> getCantidades() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int low = 0, mid = 0, high = 0;

		for (Oferta actual : BolsaTrabajo.getInstance().getOfertas()) {
			if (actual.getSalario() >= 1000 && actual.getSalario() <= 5000)
				low++;
			if (actual.getSalario() >= 5000 && actual.getSalario() <= 10000)
				mid++;
			if (actual.getSalario() > 10000)
				high++;
		}

		result.add(low);
		result.add(mid);
		result.add(high);
		return result;
	}
}
