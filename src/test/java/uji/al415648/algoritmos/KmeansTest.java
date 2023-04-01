package uji.al415648.algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uji.al415648.datos.Table;
import uji.al415648.lectura.CSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KmeansTest {
    private Kmeans myKmeans;
    private Kmeans myKmeans2;
    private Table table ;
    private List<Double> list0;
    private List<Double> list1;
    private List<Double> list2;
    private List<Double> list3;
    private List<Double> list4;
    private List<Double> list5;
    private List<Double> list6;
    private List<Double> list7;
    @BeforeEach
    void setUp() throws FileNotFoundException, TooMuchGroupsException {
        CSV myCSV = new CSV();
        table = myCSV.readTable("iris_without_labels.csv");
        myKmeans=new Kmeans(3,3,23456);
        myKmeans.train(table);

        list0=new ArrayList<>();
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        list4=new ArrayList<>();
        list5=new ArrayList<>();
        list6=new ArrayList<>();
        list7=new ArrayList<>();

        list0.add(4.6);
        list0.add(3.6);
        list0.add(1.0);
        list0.add(0.2);

        list1.add(5.8);
        list1.add(2.7);
        list1.add(3.9);
        list1.add(1.2);

        list2.add(5.9);
        list2.add(3.0);
        list2.add(5.1);
        list2.add(1.8);

        list3.add(5.5);
        list3.add(2.5);
        list3.add(4.0);
        list3.add(1.3);

        list4.add(0.0);
        list4.add(0.0);
        list4.add(0.0);
        list4.add(0.0);

        list5.add(5.8);
        list5.add(2.6);
        list5.add(4.0);
        list5.add(1.2);

        list6.add(5.0);
        list6.add(2.3);
        list6.add(3.3);
        list6.add(1.0);

        list7.add(-5.0);
        list7.add(-2.3);
        list7.add(-3.3);
        list7.add(-1.0);

    }

    @Test
    void train() {
        assertEquals(3, myKmeans.getGroups().size());
        assertEquals(3, myKmeans.getPoints().size());
        assertEquals(150, myKmeans.getGroups().get(0).size()+myKmeans.getGroups().get(1).size()+myKmeans.getGroups().get(2).size());

    }

    @Test
    void estimate() {
        assertEquals(1,myKmeans.estimate(list0));
        assertEquals(1,myKmeans.estimate(list1));
        assertEquals(0,myKmeans.estimate(list2));
        assertEquals(1,myKmeans.estimate(list3));
        assertEquals(2,myKmeans.estimate(list4));
        assertEquals(1,myKmeans.estimate(list5));   //ESTAS DOS LINEAS HAN DE SER DE LA MISMA CLASE YA QUE SON CONSECUTIVAS
        assertEquals(1,myKmeans.estimate(list6));   //ESTAS DOS LINEAS HAN DE SER DE LA MISMA CLASE YA QUE SON CONSECUTIVAS
        assertEquals(2,myKmeans.estimate(list7));
    }
    @Test
    void exceptionTest(){
        myKmeans2=new Kmeans(5,3,23456);
        assertThrows(TooMuchGroupsException.class,() -> myKmeans2.train(table));
    }
}