package uji.al415648.algoritmos;

import uji.al415648.datos.Row;
import uji.al415648.datos.Table;

import java.util.*;

public class Kmeans implements Algorithm<Table,Double>{
    private int numClusters;
    private int numIterations;
    private long seed;
    private Map<Integer,List<Row>> groups;
    private List<Row> points;

    public Kmeans(int numClusters,int numIterations, long seed){
        this.numClusters=numClusters;
        this.numIterations=numIterations;
        this.seed=seed;
        this.groups=new HashMap<>();
        this.points=new ArrayList<>();
    }

    @Override
    public void train(Table datos) throws TooMuchGroupsException {
        if(numClusters>numIterations)
            throw new TooMuchGroupsException();
        makeCentroide(datos);
        for(int j=0;j<numIterations;j++) {
            for (int i = 0; i < datos.getRows().size(); i++) {
                int id = estimate(datos.getRowAt(i).getData());
                groups.get(id).add(datos.getRowAt(i));
            }
            points.clear();
            for (int i = 0; i < numClusters; i++) {
                points.add(meanCentroide(groups.get(i)));
            }
        }
    }



    @Override
    public Integer estimate(List<Double> dato){
        int id=-1,count=0;
        double distMin=Double.MIN_VALUE;
        for(Row element:points){
            double distActual=distance(dato,element.getData());
            if(distMin > distActual){
                distMin=distActual;
                id=count;
            }
            count++;
        }
        return id;
    }
    public double distance(List<Double> dataSource, List<Double> data){
        double amount=0;
        for(int i=0;i<dataSource.size();i++){
            amount+=Math.abs(dataSource.get(i)-data.get(i));
        }
        return amount;
    }
    private void makeCentroide(Table datos){
        Random random=new Random(seed);
        for(int i=0;i<numClusters;i++){
            int numRandom=random.nextInt(datos.getRows().size()-1);
            if(!points.contains(datos.getRowAt(numRandom))){
                points.add(datos.getRowAt(numRandom));
                groups.put(i,new ArrayList<>());
            }
            else{
                i--;
            }
        }
    }
    private Row meanCentroide(List<Row> rows) { //REVISAR OPTIMIZACIÓN
        Row centroide=new Row();
        List<Double> auxiliar=new ArrayList<>();
        for(int i=0;i<rows.get(0).getColumns();i++){
            auxiliar.add(0.0);
        }
        for(Row element:rows){
            List<Double> auxiliar2=element.getData();
            for(int i=0;i<auxiliar2.size();i++){
                auxiliar.set(i,auxiliar2.get(i)+auxiliar.get(i));
            }
        }
        for(int i=0;i<auxiliar.size();i++){
            auxiliar.set(i,auxiliar.get(i)/rows.size()-1);
        }
        centroide.addRowList(auxiliar);
        return centroide;
    }
}
