package dev.jaronline.fullmash.cli.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jaronline.fullmash.cli.candidates.ModSideCandidates;
import dev.jaronline.fullmash.data.Mod;
import dev.jaronline.fullmash.cli.defaults.FMDefaultPropertiesProvider;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "add", description = "Add a mod to the modpack.",
        defaultValueProvider = FMDefaultPropertiesProvider.class)
public class AddCommand implements Callable<Integer> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Option(names = {"--source", "-src"}, required = true)
    Mod.Source source;

    @Option(names = {"--side", "-s"}, description = """
            The side the mod will be used for. (default: ${DEFAULT-VALUE})
            Possible values: ${COMPLETION-CANDIDATES}""",
            completionCandidates = ModSideCandidates.class)
    Mod.Side side;

    @Option(names = {"--required"}, negatable = true,
            defaultValue = "true", fallbackValue = "true",
            description = "Whether the mod is required for the modpack. (default: ${DEFAULT-VALUE})")
    boolean required;

    @ArgGroup(multiplicity = "1", heading = "Search for a mod by URL, ID, slug or query.\n")
    Search search;

    static class Search {
        @Parameters(index = "0", paramLabel = "url", description = "The URL to use to find the mod.")
        private String url;
        @Parameters(index = "0", paramLabel = "id", description = "The project id to use to find the mod.")
        private String id;
        @Parameters(index = "0", paramLabel = "slug", description = "The slug to use to find the mod.")
        private String slug;
        @Parameters(index = "0", paramLabel = "search", description = "The search query to use to find the mod.")
        private String search;

        @Override
        public String toString() {
            return "Search{" +
                    "slug='" + slug + '\'' +
                    ", id='" + id + '\'' +
                    ", search='" + search + '\'' +
                    ", url=" + url +
                    '}';
        }
    }

    @Override
    public Integer call() throws IOException {
        createModsDirectory();
        Mod mod = getMod();
        createModFile("temp-mod", mod);
        return 0;
    }

    private void createModsDirectory() {
        File modsDir = new File("./mods");
        if (!modsDir.exists()) {
            modsDir.mkdir();
        }
    }

    private Mod getMod() {
        return new Mod.Builder()
                .setSide(side)
                .setSource(source)
                .setRequired(required)
                .build();
    }

    private void createModFile(String slug, Mod mod) throws IOException {
        try (FileWriter fw = new FileWriter("./mods/" + slug + ".json")) {
            fw.write(gson.toJson(mod));
        }
    }
}
