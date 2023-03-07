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
    static String[] types = new String[]{"Абиссинская кошка",
            "Австралийский мист",
            "Американская жесткошерстная",
            "Американская короткошерстная",
            "Американский бобтейл",
            "Американский кёрл",
            "Балинезийская кошка",
            "Бенгальская кошка",
            "Бирманская кошка",
            "Бомбейская кошка",
            "Бразильская короткошёрстная",
            "Британская длинношерстная",
            "Британская короткошерстная",
            "Бурманская кошка",
            "Бурмилла кошка",
            "Гавана",
            "Гималайская кошка",
            "Девон-рекс",
            "Донской сфинкс",
            "Европейская короткошерстная",
            "Египетская мау",
            "Канадский сфинкс",
            "Кимрик",
            "Корат",
            "Корниш-рекс",
            "Курильский бобтейл",
            "Лаперм",
            "Манчкин",
            "Мейн-ку́н",
            "Меконгский бобтейл",
            "Мэнкс кошка",
            "Наполеон",
            "Немецкий рекс",
            "Нибелунг",
            "Норвежская лесная кошка",
            "Ориентальная кошка",
            "Оцикет",
            "Персидская кошка",
            "Петерболд",
            "Пиксибоб",
            "Рагамаффин",
            "Русская голубая кошка",
            "Рэгдолл",
            "Саванна",
            "Селкирк-рекс",
            "Сиамская кошка",
            "Сибирская кошка",
            "Сингапурская кошка",
            "Скоттиш-фолд",
            "Сноу-шу",
            "Сомалийская кошка",
            "Тайская кошка",
            "Тойгер",
            "Тонкинская кошка",
            "Турецкая ангорская кошка",
            "Турецкий ван",
            "Украинский левкой",
            "Чаузи",
            "Шартрез",
            "Экзотическая короткошерстная",
            "Японский бобтейл"
    };

    public static void main(String[] args) {
        connectDB();
        add_all_types();
        disconnectDB();
    }

    public static void insert_type(String type) {
        try {
            System.out.println("Добавлен тип кошки: " + type);
            statement.execute("INSERT into types (type) values ('" + type + "')");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void connectDB() {
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection(DB_URL); //соединение с БД
            System.out.println("Соединение с СУБД выполнено.");
            statement = connection.createStatement();
            System.out.println("Готов к выполнению SQL-запросов");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void disconnectDB() {
        try {
            connection.close();
            System.out.println("Отключение от СУБД выполнено.");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void add_all_types()  {
        for (int i = 0; i < types.length; i++) {
            insert_type(types[i]);
        }

    }
}

