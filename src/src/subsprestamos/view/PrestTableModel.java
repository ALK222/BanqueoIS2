package subsprestamos.view;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Prestamo;
import dominio.Tarjeta;
import prestamosdao.control.PrestamoJSON;
import tarjetasdao.control.TarjetasJSON;

public class PrestTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Prestamo> _prestamos;
    private String[] labels = { "DNI", "Numero Tarjeta" };

    public PrestTableModel() throws FileNotFoundException {
    	_prestamos = PrestamoJSON.leerListaPrestamos();
    }

    public PrestTableModel(List<Prestamo> listaFiltrada) {
    	_prestamos = listaFiltrada;
    }

    @Override
    public int getColumnCount() {
        return labels.length;
    }

    @Override
    public String getColumnName(int c) {
        return labels[c];
    }

    @Override
    public int getRowCount() {
        return _prestamos.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        String o = "";
        Prestamo aux = _prestamos.get(row);
        switch (col) {
            case 0:
                o = String.valueOf(aux.getNumReferencia());
                break;
            case 1:
                o = String.valueOf(aux.getCuentaAsociada());
                break;
        }
        return o;
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }

}


