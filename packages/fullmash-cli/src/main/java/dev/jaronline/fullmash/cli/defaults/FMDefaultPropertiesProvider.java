package dev.jaronline.fullmash.cli.defaults;

import picocli.CommandLine.Model.ArgSpec;
import picocli.CommandLine.IDefaultValueProvider;
import picocli.CommandLine.PropertiesDefaultProvider;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class FMDefaultPropertiesProvider implements IDefaultValueProvider {
    private static final Logger LOGGER = Logger.getLogger(FMDefaultPropertiesProvider.class.getName());

    private PropertiesDefaultProvider defaultUserPropertiesProvider;
    private PropertiesDefaultProvider defaultLocalPropertiesProvider;

    public FMDefaultPropertiesProvider() {
        try {
            defaultUserPropertiesProvider = new PropertiesDefaultProvider();
            defaultLocalPropertiesProvider = new PropertiesDefaultProvider(getLocalProperties());
        } catch (IOException e) {
            LOGGER.warning("Unable to load local default properties: " + e);
        }
    }

    @Override
    public String defaultValue(ArgSpec argSpec) throws Exception {
        String userValue = defaultUserPropertiesProvider.defaultValue(argSpec);
        return userValue != null
                ? userValue
                : defaultLocalPropertiesProvider.defaultValue(argSpec);
    }

    private Properties getLocalProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("defaults.properties"));
        return properties;
    }
}
