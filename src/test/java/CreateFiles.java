import org.testng.annotations.Test;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateFiles {


    @Test
    public void createFiles() throws SQLException, ClassNotFoundException {
        List<String> lines = getTableLines("data");
        for (int i = 0; i < lines.size(); i = i + 2) {
            writeFile(lines.get(i), lines.get(i + 1));
        }
    }


    public List<String> getTableLines(String tableName) throws SQLException, ClassNotFoundException {
        String id;
        String value;
        List<String> lines = new ArrayList();
        Connection con = getConnection();
        Statement stm = con.createStatement();
        ResultSet tableLines = stm.executeQuery("SELECT id, value FROM " + tableName);
        while (tableLines.next()) {
            id = tableLines.getString("ID");
            value = tableLines.getString("VALUE");
            lines.add(id);
            lines.add(value);
        }
        closeConnection(con);
        return lines;
    }


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager
                .getConnection(Config.LOCALHOST.getUrl(), Config.LOCALHOST.getUser(), Config.LOCALHOST.getPassword());
        System.out.println("Успешное подключение к БД " + Config.LOCALHOST.getUrl() );
        return connection;
    }


    protected void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Закрытие соединения с БД");
        } else {
            System.out.println("Закрытие соединения с БД  не требуется");
        }
    }


    protected void writeFile(String name, String value) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(Config.LOCALHOST.getPath() + name + ".txt"), "utf-8"));
            writer.write(value);
        } catch (IOException ex) {
            System.out.println("При записи файла, что то пошло не так");
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }
}

