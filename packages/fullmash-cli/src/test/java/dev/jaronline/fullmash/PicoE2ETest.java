package dev.jaronline.fullmash;

import dev.jaronline.fullmash.cli.FullMashApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class PicoE2ETest {
    final String configFile = "modpack.json";
    final String modsDirectory = "mods";

    FullMashApp app;
    CommandLine cmd;
    StringWriter swOut;
    StringWriter swErr;

    @BeforeEach
    void setup() {
        app = new FullMashApp();
        cmd = new CommandLine(app);

        swOut = new StringWriter();
        cmd.setOut(new PrintWriter(swOut));

        swErr = new StringWriter();
        cmd.setErr(new PrintWriter(swErr));
    }

    @AfterEach
    void teardown() {
        cmd.getOut().close();
        cmd.getErr().close();
        try {
            deleteIfExists(new File("./" + modsDirectory));
            deleteIfExists(new File("./" + configFile));
        } catch (IOException e) {
            fail(e);
        }
    }

    private boolean deleteIfExists(File file) throws IOException {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                boolean success = deleteIfExists(child);
                if (!success) {
                    return false;
                }
            }
        }

        return file.delete();
    }
}
