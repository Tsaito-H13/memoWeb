package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//DB接続情報をプロパティーファイルから取得するクラス　WEB-INF/classes配下に置かれているdb.propertiesを読み込む
public class DBProperties {
    private static Properties properties = new Properties();

    static {
        try (InputStream is = DBProperties.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * DBのURL
     * @return プロパティーファイルから取得したURL
     */
    public static String getUrl() {
        return properties.getProperty("url");
    }

    /**
     * DBのユーザー名
     * @return プロパティーファイルから取得したユーザー名
     */
    public static String getUser() {
        return properties.getProperty("user");
    }

    /**
     * DBのパスワード
     * @return プロパティーファイルから取得したパスワード
     */
    public static String getPassword() {
        return properties.getProperty("password");
    }
}