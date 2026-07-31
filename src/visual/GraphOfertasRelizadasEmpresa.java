package visual;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import logico.BolsaTrabajo;
import logico.Institucion;

public class GraphOfertasRelizadasEmpresa extends JPanel {

	/**
	 * Create the panel.
	 */
	public GraphOfertasRelizadasEmpresa() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		ArrayList<Integer> cantidades = getCantidades();
		
		data.addValue(cantidades.get(0), "Ofertas", "Pequeñas empresas");
		data.addValue(cantidades.get(1), "Ofertas", "Medianas empresas");
		data.addValue(cantidades.get(2), "Ofertas", "Grandes empresas");
		
		JFreeChart chart = ChartFactory.createBarChart("Ofertas por tamaño de empresa", 
				"Tamaño de empresa", "Ofertas", data);
		ChartPanel panel = new ChartPanel(chart);
		this.add(panel);
	}
	
	private ArrayList<Integer> getCantidades() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int little = 0, mid = 0, big = 0;
		
		for (Institucion actual : BolsaTrabajo.getInstance().getInstituciones()) {
			int empleados = actual.getCantEmpleado();
			if (empleados >= 0 && empleados <= 50) 
				little += actual.getMyOfertas().size();
			if (empleados >= 60 && empleados <= 160)
				mid += actual.getMyOfertas().size();
			if (empleados >= 170)
				big += actual.getMyOfertas().size();
		}
		
		result.add(little);
		result.add(mid);
		result.add(big);
		return result;
	}

}
