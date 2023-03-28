package uji.al415648.datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    private List<String> headers;
    private List<Row> rows;
    public Table(){
        headers=new ArrayList<>();
        rows=new ArrayList<>();
    }
    public Row getRowAt(int n){
        return rows.get(n);
    }
    public void addHeaders(String[] choppedLine){
        List<String> line= Arrays.asList(choppedLine);
        headers.addAll(line);
    }
    public void addLine(Row newLine){
        rows.add(newLine);
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<Row> getRows() {
        return rows;
    }
}
