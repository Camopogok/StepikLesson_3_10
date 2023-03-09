import java.sql.*;

public class Main {

    // Блок объявления констант
    public static final String DB_URL = "jdbc:sqlite:/d:/dev/StepikLesson_3_10/db/My_cats.db";
    public static final String DB_Driver = "org.sqlite.JDBC";
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
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
    static String[] names = {"Гарфилд",
            "Том",
            "Гудвин",
            "Рокки",
            "Ленивец",
            "Пушок",
            "Спорти",
            "Бегемот",
            "Пират",
            "Гудини",
            "Зорро",
            "Саймон",
            "Альбус",
            "Базилио",
            "Леопольд",
            "Нарцисс",
            "Атос",
            "Каспер",
            "Валлито",
            "Оксфорд",
            "Бисквит",
            "Соня",
            "Клеопатра",
            "Цунами",
            "Забияка",
            "Матильда",
            "Кнопка",
            "Масяня",
            "Царапка",
            "Серсея",
            "Ворсинка",
            "Амели",
            "Наоми",
            "Маркиза",
            "Изольда",
            "Вальс",
            "Несквик",
            "Златан",
            "Баскет",
            "Изюм",
            "Цукат",
            "Мокко",
            "Месси",
            "Кокос",
            "Адидас",
            "Бейлиз",
            "Тайгер",
            "Зефир",
            "Мохи",
            "Валенсия",
            "Баунти",
            "Свити",
            "Текила",
            "Ириска",
            "Карамель",
            "Виски",
            "Кукуруза",
            "Гренка",
            "Фасолька",
            "Льдинка",
            "Китана",
            "Офелия",
            "Дайкири",
            "Брусника",
            "Аватар",
            "Космос",
            "Призрак",
            "Изумруд",
            "Плинтус",
            "Яндекс",
            "Крисмас",
            "Метеор",
            "Оптимус",
            "Смайлик",
            "Цельсий",
            "Краска",
            "Дейзи",
            "Пенка",
            "Веста",
            "Астра",
            "Эйприл",
            "Среда",
            "Бусинка",
            "Гайка",
            "Елка",
            "Золушка",
            "Мята",
            "Радость",
            "Сиам",
            "Оскар",
            "Феликс",
            "Гарри",
            "Байрон",
            "Чарли",
            "Симба",
            "Тао",
            "Абу",
            "Ватсон",
            "Енисей",
            "Измир",
            "Кайзер",
            "Васаби",
            "Байкал",
            "Багира",
            "Айрис",
            "Диана",
            "Мими",
            "Сакура",
            "Индия",
            "Тиффани",
            "Скарлетт",
            "Пикси",
            "Лиззи",
            "Алиса",
            "Лило",
            "Ямайка",
            "Пэрис",
            "Мальта",
            "Аляска"
    };

    public static void main(String[] args) {
        connectDB();

        update_cat("type_id='8'", "id=2");

        disconnectDB();
    }

    public static void insert_type(String type) {
        try {
            statement.execute("INSERT into types (type) values ('" + type + "')");
            System.out.println("Добавлен тип кошки: " + type);
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

    public static void delete_type(int id) {
        try {
            statement.execute("delete from types where id=" + id);
            System.out.println("Тип кошки с id: " + id + " удалён");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void update_type(int id, String new_type) {
        try {
            statement.execute("update types set type='" + new_type + "' where id=" + id);
            System.out.println("Тип кошки с id: " + id + " изменён. Новое значение: " + new_type);
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static String get_type(int id) {
        try {
            resultSet = statement.executeQuery("select type from types where id=" + id);
            System.out.println("Тип кошки с id: " + id + " - " + resultSet.getString("type"));
            return resultSet.getString("type");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
        return null;
    }

    public static void get_type_where(String where) {
        try {
            resultSet = statement.executeQuery("select * from types where " + where);
            System.out.println("Под условия запроса попадают следующие породы:");
            while (resultSet.next()) {
                System.out.println("   - " + resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void get_all_types() {
        try {
            resultSet = statement.executeQuery("select * from types");
            System.out.println("В базе данных содержатся следующие породы:");
            while (resultSet.next()) {
                System.out.println("   - " + resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void insert_cat(String name, String type, int age, Double weight) {
        Integer type_id = checkType(type);
        if (type_id == null) {
            insert_type(type);
            type_id = checkType(type);
        }
        try {
            statement.execute("INSERT into cats (name, type_id, age, weight) values ('" + name + "', " + type_id + ", " + age + ", " + weight + ")");
            System.out.println("Добавлена кошка: " + name + ", " + type_id + ", " + age + ", " + weight);
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static Integer checkType (String type) {
        try {
            resultSet = statement.executeQuery("select * from types where type = '" + type + "'");
            if (resultSet.next()) {
                System.out.println("Порода есть в БД");
                return resultSet.getInt("id");
            } else {
                System.out.println("Породы нет в БД");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
        return null;
    }

    public static Cat randomCat () {
        Cat cat = new Cat();
        cat.name = names[(int)(Math.random()* names.length-1)];
        cat.type = types[(int)(Math.random()* types.length-1)];
        cat.age = (int) (Math.random()*12);
        cat.weight = Math.round((Math.random()*5 + 1.0)*100)/100.0;
        return cat;
    }

    public static void add_more_cats(int n) {
        for (int i = 0; i < n; i++) {
            Cat cat = randomCat();
            insert_cat(cat.name, cat.type, cat.age, cat.weight);
        }
    }

    public static void delete_cat(int id) {
        try {
            statement.execute("delete from cats where id=" + id);
            System.out.println("Кошка с id: " + id + " удалена");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void delete_cat(String where) {
        try {
            statement.execute("delete from cats where " + where);
            System.out.println("Удалены все кошки удовлетворяющие условиям: " + where);
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }

    public static void update_cat(String set, String where) {
        try {
            statement.execute("update cats set " + set + " where " + where);
            System.out.println("Кошки, удовлетворяющая условиям: " + where + " изменены. Новые значения: " + set);
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок SQL
            System.out.println("Ошибка SQL!");
        }
    }
}

class Cat {
    String name;
    String type;
    int age;
    Double weight;

    public Cat () {
        name = "";
        type = "";
        age = 0;
        weight = null;
    }
}