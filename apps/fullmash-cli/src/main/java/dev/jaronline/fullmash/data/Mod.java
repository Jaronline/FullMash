package dev.jaronline.fullmash.data;

import java.util.Arrays;

public record Mod(String name, String source, String projectID, String fileID, String side, boolean required) {
    public static class Builder {
        private String name;
        private Source source;
        private String projectID;
        private String fileID;
        private Side side;
        private boolean required;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSource(Source source) {
            this.source = source;
            return this;
        }

        public Builder setProjectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public Builder setFileID(String fileID) {
            this.fileID = fileID;
            return this;
        }

        public Builder setSide(Side side) {
            this.side = side;
            return this;
        }

        public Builder setRequired(boolean required) {
            this.required = required;
            return this;
        }

        public Mod build() {
            return new Mod(name, source.name, projectID, fileID, side.name, required);
        }
    }

    public enum Source {
        CURSEFORGE("curseforge"),
        MODRINTH("modrinth")
        ;

        private final String name;

        Source(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Side {
        CLIENT("client"),
        SERVER("server"),
        BOTH("both")
        ;

        private final String name;

        Side(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static String[] stringValues() {
            return Arrays.stream(values())
                    .map(side -> side.name)
                    .toArray(String[]::new);
        }
    }
}
