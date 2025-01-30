package dev.jaronline.fullmash;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class InitCommandE2ETest extends PicoE2ETest {
    @Test
    void testInitYesCreatesModpackSuccessfully() throws IOException {
        // Act
        int exitCode = cmd.execute("init", "--yes");

        // Assert
        assertEquals(0, exitCode);
        assertEquals("", swOut.toString());
        assertTrue(swErr.toString().isEmpty());

        assertTrue(new File("./", configFile).exists());
        assertEquals("""
                {
                  "name": "FullMash-CLI",
                  "version": "1.0.0",
                  "author": "",
                  "modloaders": [],
                  "minecraft": {
                    "version": ""
                  }
                }""", Files.readString(Paths.get(configFile)));
    }
}
