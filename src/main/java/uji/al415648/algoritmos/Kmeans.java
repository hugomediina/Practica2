package uji.al415648.algoritmos;

import uji.al415648.datos.Row;
import uji.al415648.datos.Table;

import java.util.*;

public class Kmeans implements Algorithm<Table, Double> {

    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Row>> clases;


    public Kmeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.clases = crearClases();
    }

    public List<List<Row>> crearClases() { //Creo las clases
        List<List<Row>> aDevolver = new ArrayList<>();
        for (int i = 0; i < numClusters; i++) {
            aDevolver.add(new ArrayList<>());
        }
        return aDevolver;
    }

    @Override
    public void train(Table datos) throws TooMuchGroupsException {
        if (numClusters > datos.getHeaders().size()) {
            throw new TooMuchGroupsException();
        }

        List<Row> randoms = generarRandoms(datos);
        for (int i = 0; i < numIterations; i++) {
            asignarClases(randoms, datos);
            List<Row> centroides = calcularCentroides();
            randoms = centroides;
        }
    }

    public List<Row> calcularCentroides() {
        List<List<Double>> aux = new ArrayList<>();
        List<List<Double>> aux_2 = new ArrayList<>();
        List<Row> aDevolver = new ArrayList<>();
        for (int i = 0; i < numClusters; i++) {
            aux.add(new ArrayList<>());
            aux_2.add(new ArrayList<>());
            for (int t = 0; t < clases.get(i).get(0).cantColumnas(); t++) {
                aux.get(i).add(0.0);
                aux_2.get(i).add(0.0);
            }
        }
        for (int i = 0; i < numClusters; i++) {
            for (int j = 0; j < clases.get(i).size(); j++) {
                List<Double> lista_row = clases.get(i).get(j).getData();
                for (int t = 0; t < lista_row.size(); t++) {
                    aux_2.get(i).set(t, aux_2.get(i).get(t) + lista_row.get(t));
                    aux.get(i).set(t, aux.get(i).get(t) + lista_row.get(t));
                }
            }
        }
        for (int i = 0; i < aux.size(); i++) {
            List<Double> auxiliar = new ArrayList<>();
            for (int j = 0; j < aux.get(i).size(); j++) {
                auxiliar.add(aux.get(i).get(j) / clases.get(i).size());
            }
            Row valor = new Row(auxiliar);
            aDevolver.add(valor);
        }
        return aDevolver;
    }

    public List<Row> generarRandoms(Table datos) { //Genero numeros aleatorios, y guardo en una lista la fila correspondiente a esevalor random
        List<Row> a_devolver = new ArrayList<>();
        Random random = new Random(seed);
        int random_1 = random.nextInt(datos.cantFilas())%datos.cantFilas();
        a_devolver.add(datos.getRowAt(random_1));
        for (int i = 0; i < numClusters - 1; i++) {
            int random_2 = random.nextInt(datos.cantFilas())%datos.cantFilas();
            while (random_2 <= random_1)
                random_2 = random.nextInt(datos.cantFilas()%datos.cantFilas());

            a_devolver.add(datos.getRowAt(random_2));
            random_1 = random_2;

        }
        return a_devolver;
    }

    public void asignarClases(List<Row> centroides, Table datos) {
        for (int i = 0; i < centroides.size(); i++) {
            clases.get(i).add(centroides.get(i));
        } //Añadir los centroides (o primera vez randoms) a las clases

        DistanciaEuclidea valor = new DistanciaEuclidea();

        for (int i = 0; i < datos.cantFilas(); i++) { //Recorrer las filas
            double menor = -1;
            int posicion_menor = -1;
            for (int j = 0; j < centroides.size(); j++) {//Recorrer los randoms
                double dist_min = valor.calcularDistanciaEuclidea(datos.getRowAt(i).getData(), centroides.get(j).getData());
                if (menor == -1 || menor > dist_min) {
                    menor = dist_min; //Me guardo la dist menor
                    posicion_menor = j; //Me guardo la pos menor
                }
            }
            clases.get(posicion_menor).add(datos.getRowAt(i)); //Añado a la lista que esta en la posicion i con el que tengo la mn dist la Row
        }
    }

    @Override
    public Integer estimate(List<Double> dato) {
        DistanciaEuclidea valor = new DistanciaEuclidea();
        double menor = -1;
        Integer posicion_menor = -1;
        List<Row> centroides = calcularCentroides();
        for (int j = 0; j < centroides.size(); j++) {//Recorrer los randoms
            double dist_min = valor.calcularDistanciaEuclidea(centroides.get(j).getData(), dato);
            if (menor == -1 || menor > dist_min) {
                menor = dist_min; //Me guardo la dist menor
                posicion_menor = j; //Me guardo la pos menor
            }

        }
        return posicion_menor;
    }

    public List<List<Row>> getClases() {
        return clases;
    }
}



    /*
  Copiar y usar donde corresponda
    public void saveTable(Table t, String filename) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i=0; i<t.cantFilas(); i++)
            {
                Row row = t.getRowAt(i);
                List<Double> datos = row.getData();
                int j=0;
                for (; j<datos.size()-1; j++)
                {
                    fw.write(datos.get(j).toString());
                    fw.write(",");
                }
                fw.write(datos.get(j).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }

  Posible contexto de uso


    void savePredictions()
    {
        Table datos_out = new Table();
        for (int i=0; i<datos_out.cantFilas(); i++) {
            List<Double> data = datos_out.getRowAt(i).getData();
            data.add((double)Kmeans.estimate(data));
            Row a_anyadir =new Row(data);
            datos_out.anyadirRow(a_anyadir);;
        }
        try {
            saveTable(datos_out, dataset+ "_out.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/