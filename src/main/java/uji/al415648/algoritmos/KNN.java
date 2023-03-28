package uji.al415648.algoritmos;

import uji.al415648.datos.RowWithLabel;
import uji.al415648.datos.TableWithLabels;

import java.util.List;

public class KNN {
    private TableWithLabels table;
    public void train(TableWithLabels data){
        this.table=data;
    }
    public Integer estimate(List<Double> data){
        double euclidea;
        double distMin=-1;
        int numberClass=-1;
        for(int i=0;i<table.getRows().size();i++){
            RowWithLabel row=(RowWithLabel) table.getRows().get(i);
             euclidea=distance(row.getData(),data);
             if(euclidea<distMin || distMin==-1){
                 distMin=euclidea;
                 numberClass=table.getRowAt(i).getNumberClass();
             }
        }
        return numberClass;
    }
    public double distance(List<Double> data_source, List<Double> data){
        double amount=-1;
        for(int i=0;i<data_source.size();i++){
            amount+=Math.pow((data_source.get(i)-data.get(i)),2);
        }
        return Math.sqrt(amount);
    }
}
