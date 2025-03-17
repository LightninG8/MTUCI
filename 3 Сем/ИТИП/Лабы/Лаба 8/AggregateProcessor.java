package AggregateProcessor;

import java.util.Arrays;
import java.util.List;

import DataProcessor.DataProcessor;

public class AggregateProcessor {

    @DataProcessor
    public List<String> aggregateData(List<String> data) {
        return Arrays.asList(String.join(", ", data));
    }
}
