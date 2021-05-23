package subsusuarios.view;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Persona;
import usuariosdao.control.UsuariosJSON;

public class UserTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Persona> _personas;
    private String[] labels = { "DNI", "Nombre" };

    public UserTableModel() throws FileNotFoundException {
        _personas = UsuariosJSON.leerListaUsuarios();
    }

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
