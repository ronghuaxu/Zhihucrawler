package zhihu.crawler.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by huazi on 17/8/1.
 */
public class Config {
    private static Properties prop;

    static {
        prop = new Properties();
        String filepath = "/properties/config.properties";
        try {
            InputStream is = Config.class.getClass().getResourceAsStream(filepath);
            prop.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        String value = prop.getProperty(key);
        return value;
    }

    public static Integer getInt(String key) {
        String value = prop.getProperty(key);
        return Integer.valueOf(value);
    }
}
