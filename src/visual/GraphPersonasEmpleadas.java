package visual;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logico.BolsaTrabajo;
import logico.Persona;

public class GraphPersonasEmpleadas extends JPanel {
	/**
	 * Create the panel.
	 */
	public GraphPersonasEmpleadas() {
		DefaultPieDataset data = new DefaultPieDataset();

		int total = BolsaTrabajo.getInstance().getPersonas().size();
		data.setValue("Empleadas", getCantPersonesEmpleadas());
		data.setValue("No empleadas", total - getCantPersonesEmpleadas());

		JFreeChart chart = ChartFactory.createPieChart("Personas empleadas", data);
		ChartPanel panel = new ChartPanel(chart);
		this.add(panel);
	}

	private int getCantPersonesEmpleadas() {
		int total = 0;

		for (Persona actual : BolsaTrabajo.getInstance().getPersonas())
			if (actual.isEmpleado())
				total++;

		return total;
	}

}
