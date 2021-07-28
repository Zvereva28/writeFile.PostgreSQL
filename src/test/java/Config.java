
public enum Config {

    LOCALHOST("jdbc:postgresql://0.0.0.0:5432/uni", "selectel", "selectel", "C:\\Users\\EZvereva\\Desktop\\111\\");

    /**
     * URL поключения к БД.
     */
    private String url;
    /**
     * Пользователь поключения к БД.
     */
    private String user;
    /**
     * Пароль поключения к БД.
     */
    private String password;
    /**
     * Папка где будут сохраняться файлы.
     */
    private String path;

    Config(String url, String user, String password, String path) {

        this.url = url;
        this.user = user;
        this.password = password;
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }

}







