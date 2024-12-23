package Testerlapplication;

import AggregateProcessor.AggregateProcessor;
import DataManager.DataManager;
import FilterProcessor.FilterProcessor;
import TransformProcessor.TransformProcessor;

public class Testerlapplication  {
    public static void main(String[] args) throws InterruptedException {
        DataManager dataManager = new DataManager();

        // Enregistrer les processeurs
        dataManager.registerDataProcessor(new FilterProcessor());
        dataManager.registerDataProcessor(new TransformProcessor());
        dataManager.registerDataProcessor(new AggregateProcessor());

        // Charger les données
        dataManager.loadData("source.txt");

        // Traiter les données
        dataManager.processData();

        // Enregistrer les données
        dataManager.saveData("destination.txt");
    }
}

