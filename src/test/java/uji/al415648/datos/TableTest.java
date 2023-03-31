package uji.al415648.datos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uji.al415648.datos.Table;
import uji.al415648.lectura.CSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    CSV myCSV;
    Table table;
    List<String> header;
    List<Double> line0;
    List<Double> line1;
    List<Double> line2;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        myCSV=new CSV();
        table=myCSV.readTable("miles_dollars.csv");

        header=new ArrayList<>();
        line0=new ArrayList<>();
        line1=new ArrayList<>();
        line2=new ArrayList<>();

        line0.add(1849.0);
        line0.add(2332.0);
        line1.add(2468.0);
        line1.add(3694.0);
        line2.add(3466.0);
        line2.add(4244.0);
        header.add("Miles");
        header.add("Dollars");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRowAt() { //El número que se le asigna a cada fila es correcto
        assertEquals( line0,table.getRowAt(4).getData());
        assertEquals( line1,table.getRowAt(9).getData());
        assertEquals( line2,table.getRowAt(14).getData());
    }

    @Test
    void getRows() {
        assertEquals(25,table.getRows().size());
    }

    @Test
    void getColumns() {
        assertEquals(2,table.getHeaders().size());
    }
    @Test
    void checkHeaders() {
        assertEquals(header, table.getHeaders());
    }
}