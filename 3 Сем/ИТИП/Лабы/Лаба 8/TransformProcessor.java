package TransformProcessor;

import java.util.List;
import java.util.stream.Collectors;
import DataProcessor.DataProcessor;

public class TransformProcessor {
    @DataProcessor
    public List<String> transformToUpperCase(List<String> data) {
        return data.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}

