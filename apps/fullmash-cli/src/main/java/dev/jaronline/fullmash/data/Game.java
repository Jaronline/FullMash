package dev.jaronline.fullmash.data;

public record Game(String version) {
    public static class Builder {
        private String version;

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder withDefaults() {
            this.version = "";
            return this;
        }

        public Game build() {
            return new Game(version);
        }
    }
}
