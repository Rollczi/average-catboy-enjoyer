package dev.rollczi.averagecatboyenjoyer;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import net.dzikoysk.cdn.Cdn;
import net.dzikoysk.cdn.CdnFactory;
import net.dzikoysk.cdn.reflect.Visibility;
import net.dzikoysk.cdn.source.Source;

class CatboyService {

    private static final String API_URL = "https://serpapi.com/search.json?q=%s&engine=google_images&ijn=%s&api_key=%s";

    private final Cdn cdn = CdnFactory.createYamlLike().getSettings()
        .withMemberResolver(Visibility.PACKAGE_PRIVATE)
        .build();

    private final File file;
    private final CatboyConfig config;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public CatboyService(File file) {
        this.file = file;
        this.config = cdn.load(Source.of(file), CatboyConfig.class)
            .orThrow(e -> new RuntimeException("Cannot load config", e));
    }

    public String nextImage() {
        String image = config.popImage();
        saveConfig();

        if (image != null) {
            return image;
        }

        return fetchImages();
    }

    private String fetchImages() {
        try {
            HttpResponse<String> serpapiKey = httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create(
                    String.format(API_URL, "cute%20anime%20cat%20boy%20pfp%20anime", config.position, System.getenv("SERPAPI_KEY"))
                ))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

            List<String> list = gson.fromJson(serpapiKey.body(), ApiResult.class).images_results().stream()
                .map(ImagesResult::original)
                .toList();

            this.addImages(list);
            this.config.position++;
            saveConfig();

            return this.nextImage();
        }
        catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void addImages(List<String> images) {
        config.addImages(images);
        saveConfig();
    }

    private void saveConfig() {
        cdn.render(config, Source.of(file));
    }

}
