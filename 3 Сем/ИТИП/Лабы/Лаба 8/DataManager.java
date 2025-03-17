package DataManager;

import DataProcessor.DataProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data;

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) {
        try {
            data = Files.readAllLines(Paths.get(source));
            System.out.println("Данные загружены из " + source + " : " + data);
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных из файла " + source + ": " + e.getMessage());
            data = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public void processData() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (Object processor : processors) {
            for (Method method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    executorService.submit(() -> {
                        try {
                            data = (List<String>) method.invoke(processor, data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Данные после обработки: " + data);
    }

    public void saveData(String destination) {
        try {
            Files.write(Paths.get(destination), data);
            System.out.println("Данные сохранены в " + destination + " : " + data);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных в файл " + destination + ": " + e.getMessage());
        }
    }
}
