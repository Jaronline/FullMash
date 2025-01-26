package dev.jaronline.fullmash;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class PicoE2ETest {
    final String configFile = "modpack.json";

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
    }
}
