package substarjetas.view;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Tarjeta;
import tarjetasdao.control.TarjetasJSON;

/**
 * Tabla para los datos de tarjetas
 * 
 * @see AbstractTableModel
 */
public class TarjTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Tarjeta> _tarjetas;
    private String[] labels = { "DNI", "Numero Tarjeta" };

    /**
     * Constructor vacío para que busque la lista de tarjetas entera
     * 
     * @throws FileNotFoundException Si no puede contactar con la base de datos
     */
    public TarjTableModel() throws FileNotFoundException {
        _tarjetas = TarjetasJSON.leerListaTarjetas();
    }

    /**
     * Constructor con una lista ya dada
     * 
     * @param listaFiltrada lista a la que se le ha aplicado un filtro
     */
    public TarjTableModel(List<Tarjeta> listaFiltrada) {
        _tarjetas = listaFiltrada;
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
        return _tarjetas.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        String o = "";
        Tarjeta aux = _tarjetas.get(row);
        switch (col) {
            case 0:
                o = aux.get_dni();
                break;
            case 1:
                o = String.valueOf(aux.getNum_tarjeta());
                break;
        }
        return o;
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }

}
