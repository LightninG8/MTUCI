package DataManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import DataProcessor.DataProcessor;

public class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data;
    
    // Méthode pour enregistrer un processeur
    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    // Charger les données à partir d'une source
    public void loadData(String source) {
        // Exemple de chargement (lecture fictive d'un fichier ou d'une source)
        data = List.of("apple", "banana", "cherry", "date", "elderberry");
        System.out.println("Données chargées : " + data);
    }

    // Traiter les données
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
        System.out.println("Données après traitement : " + data);
    }

    // Enregistrer les données dans une destination
    public void saveData(String destination) {
        // Exemple d'enregistrement (écriture fictive dans une destination)
        System.out.println("Données enregistrées dans " + destination + " : " + data);
    }
}

