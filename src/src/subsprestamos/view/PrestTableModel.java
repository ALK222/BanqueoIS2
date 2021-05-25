package subsprestamos.view;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Prestamo;
import prestamosdao.control.PrestamoJSON;

/**
 * Tabla para los datos de prestamos
 * 
 * @see AbstractTableModel
 */
public class PrestTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Prestamo> _prestamos;
    private String[] labels = { "Numero de Prestamo", "Numero De Referencia" };

    /**
     * Constructor vacío para que busque la lista de cuentas entera
     * 
     * @throws FileNotFoundException Si no puede contactar con la base de datos
     */
    public PrestTableModel() throws FileNotFoundException {
        _prestamos = PrestamoJSON.leerListaPrestamos();
    }

    /**
     * Constructor con una lista ya dada
     * 
     * @param listaFiltrada lista a la que se le ha aplicado un filtro
     */
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
