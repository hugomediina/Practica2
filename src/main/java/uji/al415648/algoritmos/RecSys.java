package uji.al415648.algoritmos;
import uji.al415648.datos.Row;
import uji.al415648.datos.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecSys {
    private Algorithm algorithm;
    private Map<Integer,List<String>> groups;
    public RecSys(Algorithm algorithm){
        this.algorithm=algorithm;
        this.groups=new HashMap<>();
    }
    public void train(Table trainData) throws TooMuchGroupsException {
        algorithm.train(trainData);
    }
    public void run(Table testData, List<String> testItemNames){
        for(int i=0;i<testData.getRows().size();i++){
            int n=algorithm.estimate(testData.getRows().get(i).getData());
            if(!groups.containsKey(n)){
                groups.put(n,new ArrayList<>());
            }
            groups.get(n).add(testItemNames.get(i));
        }
    }
    public List<String> recommend(String nameLikedItem, int numRecommendations){
        int idx=idx(nameLikedItem);
        if(idx!=-1){
            List<String> list=new ArrayList<>();
            for(int i=0;i<groups.get(idx).size() && i<numRecommendations;i++){
                if(groups.get(idx).get(i).compareTo(nameLikedItem)!=0){
                    list.add(groups.get(idx).get(i));
                }else{
                    i--;
                }
            }
            return list;
        }else
            return null;
    }
    public Integer idx(String nameLikedItem){
        for (Integer key:groups.keySet()){
            if(groups.get(key).contains(nameLikedItem)){
                return key;
            }
        }
        return -1;
    }
}
