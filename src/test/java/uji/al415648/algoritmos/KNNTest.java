package uji.al415648.algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uji.al415648.datos.TableWithLabels;
import uji.al415648.lectura.CSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {
    CSV myCSV;
    KNN myKNN;
    TableWithLabels table;
    private List<Double> line0_source;
    private List<Double> line1_source;
    private List<Double> line0;
    private List<Double> line1;
    private List<Double> line2;
    private List<Double> line3;
    private List<Double> line4;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        myCSV=new CSV();
        myKNN=new KNN();

        line0_source=new ArrayList<>();
        line1_source=new ArrayList<>();
        line0=new ArrayList<>();
        line1=new ArrayList<>();
        line2=new ArrayList<>();
        line3=new ArrayList<>();
        line4=new ArrayList<>();

        table=myCSV.readTableWithLabels("iris.csv");
        myKNN.train(table);

        line0_source.add(5.4);
        line0_source.add(3.9);
        line0_source.add(1.3);
        line0_source.add(0.4);

        line1_source.add(6.7);
        line1_source.add(3.0);
        line1_source.add(5.0);
        line1_source.add(1.7);

        line0.add(5.0);
        line0.add(3.0);
        line0.add(10.0);
        line0.add(1.0);

        line1.add(8.0);
        line1.add(8.0);
        line1.add(8.0);
        line1.add(8.0);

        line2.add(4.6);
        line2.add(3.4);
        line2.add(1.4);
        line2.add(0.4);

        line3.add(-1.0);
        line3.add(-1.0);
        line3.add(-1.0);
        line3.add(-1.0);

        line4.add(0.0);
        line4.add(0.0);
        line4.add(0.0);
        line4.add(0.0);
    }
    @Test
    void estimate() {
        assertEquals(3,myKNN.estimate(line0));
        assertEquals(3,myKNN.estimate(line1));
        assertEquals(1,myKNN.estimate(line2));
        assertEquals(1,myKNN.estimate(line3));
        assertEquals(1,myKNN.estimate(line4));
    }

    @Test
    void distance() {
        assertEquals(8.719,myKNN.distance(line0_source,line0),0.001);
        assertEquals(5.233,myKNN.distance(line1_source,line0),0.001);
        assertEquals(11.19,myKNN.distance(line0_source,line1),0.001);
        assertEquals(8.624,myKNN.distance(line1_source,line1),0.001);
    }
}