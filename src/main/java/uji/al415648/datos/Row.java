package uji.al415648.datos;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Double> data;
    public Row(){
        data=new ArrayList<>();
    }
    public List<Double> getData() {
        return data;
    }
    public void addRow(Double n){
        data.add(n);
    }
    public int getColumns(){
        return data.size();
    }
    public void addRowList(List<Double> data){
        this.data=data;
    }
}
