package uji.al415648.algoritmos;

import uji.al415648.datos.Table;

import java.util.List;

public interface Algorithm<T extends Table> {
    void train(T datos) throws TooMuchGroupsException;
    Integer estimate(List<Double> dato);
}
