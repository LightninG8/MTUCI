package FilterProcessor;

import java.util.List;
import java.util.stream.Collectors;

import DataProcessor.DataProcessor;

public class FilterProcessor {
    @DataProcessor
    public List<String> filterShortWords(List<String> data) {
        return data.stream()
                .filter(word -> word.length() > 5)
                .collect(Collectors.toList());
    }
}

