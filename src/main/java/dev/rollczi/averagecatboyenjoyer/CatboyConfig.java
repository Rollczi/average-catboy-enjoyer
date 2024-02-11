package dev.rollczi.averagecatboyenjoyer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.Nullable;

class CatboyConfig {

    int position = 0;
    List<String> images = new ArrayList<>();

    public void addImages(List<String> images) {
        List<String> imagesList = new ArrayList<>(this.images);
        imagesList.addAll(images);

        this.images = imagesList;
    }

    public List<String> getImages() {
        return images;
    }

    @Nullable
    public String popImage() {
        List<String> images = new ArrayList<>(this.images);

        try {
            String image = images.getFirst();
            images.removeFirst();

            this.images = images;
            return image;
        }
        catch (NoSuchElementException suchElementException) {
            return null;
        }
    }

}
