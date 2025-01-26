package dev.jaronline.fullmash.data;

public record Modloader(String name, String version) {
    public static class Builder {
        private String name;
        private String version;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Modloader build() {
            return new Modloader(name, version);
        }
    }
}
