package visual;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logico.BolsaTrabajo;
import logico.Persona;
import logico.Tecnico;

public class GraphPersonasTecUni extends JPanel {
	/**
	 * Create the panel.
	 */
	public GraphPersonasTecUni() {
		DefaultPieDataset data = new DefaultPieDataset();

		int total = BolsaTrabajo.getInstance().getPersonas().size();
		data.setValue("Universitarios", total - getPersonasTec());
		data.setValue("Tecnicos", getPersonasTec());

		JFreeChart chart = ChartFactory.createPieChart("Personas tecnicos & universitarias", data);
		ChartPanel panel = new ChartPanel(chart);
		this.add(panel);
	}

	private int getPersonasTec() {
		int total = 0;
		for (Persona actual : BolsaTrabajo.getInstance().getPersonas())
			if (actual instanceof Tecnico)
				total++;

		return total;
	}
}
