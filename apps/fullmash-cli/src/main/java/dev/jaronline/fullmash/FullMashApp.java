package dev.jaronline.fullmash;

import dev.jaronline.fullmash.commands.AddCommand;
import dev.jaronline.fullmash.commands.InitCommand;
import picocli.CommandLine;
import picocli.CommandLine.PropertiesDefaultProvider;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = FullMashApp.NAME, aliases = {"fm"}, mixinStandardHelpOptions = true,
        version = FullMashApp.NAME + "-cli 0.1",
        defaultValueProvider = PropertiesDefaultProvider.class,
        subcommands = {
                HelpCommand.class,
                InitCommand.class,
                AddCommand.class
        })
public class FullMashApp implements Callable<Integer> {
    public static final String NAME = "fullmash";

    @Override
    public Integer call() {
        return new CommandLine(this).execute("--help");
    }

    public String getConfigFile() {
        return "modpack.json";
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FullMashApp())
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(args);
        System.exit(exitCode);
    }
}
