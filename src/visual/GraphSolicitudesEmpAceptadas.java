package visual;

import java.util.ArrayList;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logico.BolsaTrabajo;
import logico.Solicitud;
import logico.SolicitudEmp;

public class GraphSolicitudesEmpAceptadas extends JPanel {

	/**
	 * Create the panel.
	 */
	public GraphSolicitudesEmpAceptadas() {
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Aceptadas", getCantidadSolicitudesEmpAceptadas());
		data.setValue("Rechazadas", getCantidadSolicitudesEmp() - getCantidadSolicitudesEmpAceptadas());
		
		JFreeChart chart = ChartFactory.createPieChart("Solicitudes de empleados", data, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		this.add(chartPanel);
	}

	private int getCantidadSolicitudesEmpAceptadas() {
		ArrayList<Solicitud> solicitudes = BolsaTrabajo.getInstance().getSolicitudes();
		int total = 0;
		
		for (Solicitud actual : solicitudes) {
			if ((actual instanceof SolicitudEmp) && actual.isEstado()) 
				total++;
		}
		
		return total;
	}
	
	private int getCantidadSolicitudesEmp() {
		ArrayList<Solicitud> solicitudes = BolsaTrabajo.getInstance().getSolicitudes();
		int total = 0;
		
		for (Solicitud actual : solicitudes) {
			if ((actual instanceof SolicitudEmp)) 
				total++;
		}
		
		return total;
	}
}
