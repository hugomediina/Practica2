package uji.al415648.datos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uji.al415648.datos.TableWithLabels;
import uji.al415648.lectura.CSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableWithLabelsTest {
    CSV myCSV;
    TableWithLabels table;
    List<String> header;
    List<Double> line0;
    List<Double> line1;
    List<Double> line2;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        myCSV= new CSV();
        table=myCSV.readTableWithLabels("iris.csv");

        header=new ArrayList<>();
        header.add("sepal length");
        header.add("sepal width");
        header.add("petal length");
        header.add("petal width");
        header.add("class");

        line0=new ArrayList<>();
        line0.add(5.0);
        line0.add(3.6);
        line0.add(1.4);
        line0.add(0.2);

        line1=new ArrayList<>();
        line1.add(5.7);
        line1.add(4.4);
        line1.add(1.5);
        line1.add(0.4);

        line2=new ArrayList<>();
        line2.add(5.0);
        line2.add(3.4);
        line2.add(1.5);
        line2.add(0.2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRowAt() {
        assertEquals(line0,table.getRowAt(4).getData());
        assertEquals(line1,table.getRowAt(15).getData());
        assertEquals(line2,table.getRowAt(7).getData());
    }
    @Test
    void correctNumber() {
        assertEquals(1,table.getRowAt(4).getNumberClass());
        assertEquals(1,table.getRowAt(15).getNumberClass());
        assertEquals(1,table.getRowAt(7).getNumberClass());
        assertEquals(1, table.getRowAt(0).getNumberClass());
        assertEquals(3, table.getRowAt(148).getNumberClass());
        assertEquals(2, table.getRowAt(89).getNumberClass());
    }
    @Test //Número de filas leido
    void getRows() {
        assertEquals(150,table.getRows().size());
    }
    @Test //Número de columnas leido
    void getColumns() {
        assertEquals(5,table.getHeaders().size());
    }
    @Test
    void checkHeaders() {
        assertEquals(header,table.getHeaders());
    }
}