import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    // Блок объявления констант
    public static final String DB_URL = "jdbc:sqlite:/d:/dev/StepikLesson_3_10/db/My_cats.db";
    public static final String DB_Driver = "org.sqlite.JDBC";
    public static Connection connection;
    public static Statement statement;

    public static void main(String[] args) {
        connectDB();
        insert_type("Абиссинская кошка");
        insert_type("Австралийский мист");
        insert_type("Американская жесткошерстная");
        disconnectDB();
    }

    public static void insert_type(String type) {
        try {
            System.out.println("INSERT into types (type) values ('" + type + "')");
            statement.execute("INSERT into types (type) values ('" + type + "')");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    public static void connectDB() {
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection(DB_URL);//создание БД
            System.out.println("Соединение с СУБД выполнено.");
            statement = connection.createStatement();
            System.out.println("Готов к выполнению SQL-запросов");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    public static void disconnectDB() {
        try {
            connection.close();
            System.out.println("Отключение от СУБД выполнено.");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }
}

