package main.java.com.biejas.photosorter;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Categories {
    private static final Map<String, Category> registry = new HashMap<>();

    private Categories() {
    }

    public static Category getCategory(String name) {
        Category found = registry.get(name);
        if(found != null) {
            return found;
        }else {
            Category newCategory = new Category(name);
            registry.put(name,newCategory);
            return newCategory;
        }
    }

    public static void annotatePhotosInBatch(File[] files, Integer threshold, List<CategorizedFile> categorized){
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
            Double th = Double.valueOf(threshold/100.0);
            List<AnnotateImageRequest> requests = new ArrayList<>();
            for(File file : files){
                System.out.println("Annotating file " + file.getName() + " ...");
                // The path to the image file to annotate
                String fileName = file.getCanonicalPath();

                // Reads the image file into memory
                Path path = Paths.get(fileName);
                byte[] data = Files.readAllBytes(path);
                ByteString imgBytes = ByteString.copyFrom(data);

                // Builds the image annotation request
                Image img = Image.newBuilder().setContent(imgBytes).build();
                Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                        .addFeatures(feat)
                        .setImage(img)
                        .build();
                requests.add(request);
            }

            // Performs label detection on the image file
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            int count = 0;
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return;
                }

                System.out.println("---");

                CategorizedFile cf = new CategorizedFile(files[count]);

                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                    annotation.getAllFields()
                            .forEach((k, v) ->
                            System.out.printf("%s : %s\n", k, v.toString()));

                    if(annotation.getScore()>=th){
                        cf.addCategory(Categories.getCategory(annotation.getDescription()));
                    }
                }

                categorized.add(cf);
                cf.setTopCategory();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
