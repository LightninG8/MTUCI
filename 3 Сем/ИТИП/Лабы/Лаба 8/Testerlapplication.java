package Testerlapplication;

import AggregateProcessor.AggregateProcessor;
import DataManager.DataManager;
import FilterProcessor.FilterProcessor;
import TransformProcessor.TransformProcessor;

public class Testerlapplication  {
    public static void main(String[] args) throws InterruptedException {
        DataManager dataManager = new DataManager();

        dataManager.registerDataProcessor(new FilterProcessor());
        dataManager.registerDataProcessor(new TransformProcessor());
        dataManager.registerDataProcessor(new AggregateProcessor());

        dataManager.loadData("source.txt");

        dataManager.processData();

        dataManager.saveData("destination.txt");
    }
}

