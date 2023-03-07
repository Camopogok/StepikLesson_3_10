import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

        // Блок объявления констант
        public static final String DB_URL = "jdbc:sqlite:/d:/dev/StepikLesson_3_10/db/My_cats.db";
        public static final String DB_Driver = "org.sqlite.JDBC";

        public static void main(String[] args) {
            try {
                Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
                Connection connection = DriverManager.getConnection(DB_URL);//создание БД
                System.out.println("Соединение с СУБД выполнено.");

                Statement statement = connection.createStatement();
                statement.execute("CREATE TABLE if not exists 'types' ('id' INTEGER PRIMARY KEY UNIQUE, 'type' varchar(100) NOT NULL)");

                connection.close();       // отключение от БД
                System.out.println("Отключение от СУБД выполнено.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace(); // обработка ошибки  Class.forName
                System.out.println("JDBC драйвер для СУБД не найден!");
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                System.out.println("Ошибка SQL !");
            }
        }
    }

