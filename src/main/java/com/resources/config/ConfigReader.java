package com.resources.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * Reads settings from src/test/resources/config/qa.properties.
 * Pick the environment with -Denv=qa (default: qa).
 * Any value can be overridden from the command line, e.g. -Dheadless=true or -Dbrowser=firefox.
 */
public final class ConfigReader {

    private static final Properties PROPS = load();

    private ConfigReader() {
    }

    private static Properties load() {
        String env = System.getProperty("env", "qa");
        String path = "config/" + env + ".properties";
        Properties props = new Properties();
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("Config file not found on classpath: " + path);
            }
            props.load(in);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read " + path, e);
        }
        return props;
    }

    public static String getOrDefault(String key, String defaultValue) {
        String value = System.getProperty(key, PROPS.getProperty(key));
        return value == null ? defaultValue : value.trim();
    }

    public static String get(String key) {
        String value = System.getProperty(key, PROPS.getProperty(key));
        if (value == null) {
            throw new IllegalStateException("Missing config key: " + key);
        }
        return value.trim();
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static long getLong(String key) {
        return Long.parseLong(get(key));
    }
}
