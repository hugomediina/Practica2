package uji.al415648.datos;

import uji.al415648.datos.RowWithLabel;
import uji.al415648.datos.Table;

import java.util.HashMap;
import java.util.Map;

public class TableWithLabels extends Table {
    private Map<String,Integer> labelsToIndex;

    public TableWithLabels(){
        super();
        this.labelsToIndex=new HashMap<>();
    }
    @Override
    public RowWithLabel getRowAt(int n) {
        return (RowWithLabel) super.getRowAt(n);
    }
    public void addLabelsToIndex(String label, int numberClass){
        labelsToIndex.put(label,numberClass);
    }
    public Map<String, Integer> getLabelsToIndex() {
        return labelsToIndex;
    }
}