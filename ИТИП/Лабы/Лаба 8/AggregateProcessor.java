package AggregateProcessor;

import java.util.List;

import DataProcessor.DataProcessor;

public class AggregateProcessor {
    @DataProcessor
    public List<String> aggregateData(List<String> data) {
        return List.of(String.join(", ", data));
    }
}

