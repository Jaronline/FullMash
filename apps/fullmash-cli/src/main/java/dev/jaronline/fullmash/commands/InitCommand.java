package dev.jaronline.fullmash.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.jaronline.fullmash.FullMashApp;
import dev.jaronline.fullmash.data.Game;
import dev.jaronline.fullmash.data.Modloader;
import dev.jaronline.fullmash.data.Modpack;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Spec;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

@Command(name = "init", aliases = "create", description = "Set up a new modpack.")
public class InitCommand implements Callable<Integer> {
    private static final Logger LOGGER = Logger.getLogger(InitCommand.class.getName());
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Scanner scanner = new Scanner(System.in);

    @ParentCommand
    FullMashApp fullMash;

    @Spec
    CommandSpec spec;

    @Option(names = {"--yes", "-y"},
            description = "Automatically answer \"yes\" to any prompts that " + FullMashApp.NAME + " might print on the command line.")
    boolean withDefaults;

    @Override
    public Integer call() {
        try (FileWriter fw = new FileWriter(fullMash.getConfigFile())) {
            Modpack modpack = getModpack();
            fw.write(gson.toJson(modpack));
            return 0;
        } catch (IOException e) {
            LOGGER.warning("Unable to initialize modpack: " + e);
            spec.commandLine().getErr().println("Unable to initialize modpack");
            return 1;
        }
    }

    private Modpack getModpack() {
        Modpack defaultModpack = new Modpack.Builder()
                .withDefaults()
                .build();

        if (withDefaults) {
            return defaultModpack;
        }

        return new Modpack.Builder()
                .setName(askInput("modpack name", defaultModpack.name()))
                .setVersion(askInput("version", defaultModpack.version()))
                .setAuthor(askInput("author", defaultModpack.author()))
                .addModloader(getModloader())
                .setMinecraft(getMinecraft())
                .build();
    }

    private Modloader getModloader() {
        return new Modloader.Builder()
                .setName(askInput("modloader name", ""))
                .setVersion(askInput("modloader version", ""))
                .build();
    }

    private Game getMinecraft() {
        return new Game.Builder()
                .setVersion(askInput("minecraft version", ""))
                .build();
    }

    private String askInput(String name, String defaultValue) {
        spec.commandLine().getOut().print(name + ": ");
        if (defaultValue != null && !defaultValue.isEmpty()) {
            spec.commandLine().getOut().print("(" + defaultValue + ") ");
        }

        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return defaultValue;
        }
        return input;
    }
}
