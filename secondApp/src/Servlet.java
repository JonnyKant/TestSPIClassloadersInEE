import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceLoader;

@WebServlet(name = "Servlet", urlPatterns = "/hello")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
//
        try {
            Class asd = ClassLoader.getSystemClassLoader().loadClass("com.mysql.cj.jdbc.Driver");
            //Не может найти класс, так как библиотека загружена пользовательским загрузчиком, также этот метод
            // не запускает инициализацию, которая была необходима до JDBC 4.0, хотя перегруженный приватный метод loadclass имеет
            // второй аргумент предназначенный для разрешения установки ссылок на класс, что все равно не дает инициализации.
            Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
            // Получает пользовательский загрузчик через рефлексию, и запускает статистическую инициализацию, которая
            // которая в свою очередь запускает статический блок в классе Driver и позволяет загрузить драйвер
        } catch (ClassNotFoundException e) {
            System.out.println("Error, not found" + e.getMessage());
        }
        System.out.println(Thread.currentThread().getContextClassLoader()); // Проверка CL
        System.out.println(this.getClass().getClassLoader()); // Проверка CL
        try {
            System.out.println(Arrays.toString(DriverManager.drivers().toArray()));
            // Дажае в методе drivers происходит попытка инициализации механизма SPI и сканирование system property jdbc.drivers
            // после такой попытки, т.е. при вызове практически любого метода из DriverManager происходит выставление флага,
            // которой не дает больше проихозайти инициализации с помощью вышеописаннных механизмов, это сделано потому что
            // ServiceLoader является дорогим мероприятием и в дальнейщем регистрация происходит вручную, с помощью
            // DriverManager.registerDriver() или через ClassForName() что по сути одно и тоже
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/diplom?user=root&password=s43323434&ssl=true");
        } catch (SQLException e) {
            System.out.println("Error connection" + e.getMessage());
        }
        Iterator<Driver> ps = ServiceLoader.load(Driver.class).iterator(); // Можно считать альтернативой classForName()
            while (ps.hasNext()) {
                System.out.println(ps.next());
            }
//

        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>Hello from HelloServlet2</h2>");
        } finally {
            writer.close();
        }
    }
    }

