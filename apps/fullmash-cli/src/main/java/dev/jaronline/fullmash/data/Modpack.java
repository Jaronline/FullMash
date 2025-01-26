package dev.jaronline.fullmash.data;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public record Modpack(
        String name, String version, String author, List<Modloader> modloaders, Game minecraft) {
    public static class Builder {
        private String name;
        private String version;
        private String author;
        private List<Modloader> modloaders = new ArrayList<>();
        private Game minecraft;

        public Builder() {}

        public Builder(Modpack modpack) {
            this.name = modpack.name;
            this.version = modpack.version;
            this.author = modpack.author;
            this.modloaders = modpack.modloaders;
            this.minecraft = modpack.minecraft;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setModloaders(List<Modloader> modloaders) {
            this.modloaders = modloaders;
            return this;
        }

        public Builder addModloader(Modloader modloader) {
            this.modloaders.add(modloader);
            return this;
        }

        public Builder setMinecraft(Game minecraft) {
            this.minecraft = minecraft;
            return this;
        }

        public Builder withDefaults() {
            this.name = Paths.get("").toAbsolutePath().getFileName().toString();
            this.version = "1.0.0";
            this.author = "";
            this.minecraft = new Game.Builder().withDefaults().build();
            return this;
        }

        public Modpack build() {
            return new Modpack(name, version, author, modloaders, minecraft);
        }
    }
}
