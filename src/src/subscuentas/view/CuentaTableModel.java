package subscuentas.view;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dominio.Cuenta;
import cuentadao.control.CuentasJSON;

public class CuentaTableModel extends AbstractTableModel{
    private static final long serialVersionUID = 1L;
    private String _columnas[] = {"DNI", "Titular","Saldo", "Firma digital", "Numero de cuenta"};
    private  List<Cuenta>_listaCuentas;
    
    public CuentaTableModel() throws FileNotFoundException{
        _listaCuentas = CuentasJSON.leerListaCuentas();
    }


    @Override
    public String getColumnName(int c) {
        return _columnas[c];
    }


    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }

    @Override
    public int getRowCount() {
      
        return _listaCuentas.size();
    }

    @Override
    public int getColumnCount() {
        return _columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        switch(columnIndex){

            case 0:
            return _listaCuentas.get(rowIndex).getTitularCuenta().getFirst();
            case 1: 
            return  _listaCuentas.get(rowIndex).getTitularCuenta().getSecond();
            case 2: 
            return  _listaCuentas.get(rowIndex).getSaldo(); 
            case 3: 
            return _listaCuentas.get(rowIndex).getFirmaDigital(); 
            case 4:
            return _listaCuentas.get(rowIndex).getNumeroCuenta(); 
            default:
            return null; 
        }
    }
    
}
