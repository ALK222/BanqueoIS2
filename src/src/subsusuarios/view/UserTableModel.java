package subsusuarios.view;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Persona;
import usuariosdao.control.UsuariosJSON;

/**
 * Tabla para los datos de usuarios
 * 
 * @see AbstractTableModel
 */
public class UserTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Persona> _personas;
    private String[] labels = { "DNI", "Nombre" };

    /**
     * Constructor vacío para que busque la lista de usuarios total
     * 
     * @throws FileNotFoundException Si no puede contactar con la base de datos
     */
    public UserTableModel() throws FileNotFoundException {
        _personas = UsuariosJSON.leerListaUsuarios();
    }

    /**
     * Constructor con una lista ya dada
     * 
     * @param listaFiltrada lista a la que se le ha aplicado un filtro
     */
    public UserTableModel(List<Persona> listaFiltrada) {
        _personas = listaFiltrada;
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
        return _personas.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        String o = "";
        Persona aux = _personas.get(row);
        switch (col) {
            case 0:
                o = aux.getDni();
                break;
            case 1:
                o = aux.getNombre();
                break;
        }
        return o;
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }

}
