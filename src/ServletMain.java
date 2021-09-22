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
public class ServletMain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
//            ClassLoader.getSystemClassLoader().loadClass("com.mysql.cj.jdbc.Driver"); //Не может найти класс, так как библиотека загружена пользовательским загрузчиком
            Class clazz = Class.forName("com.mysql.cj.jdbc.Driver"); // Получает пользовательский загрузчик через рефлексию, и запускает статистическую
            // инициализацию, способ выше не дает этого, так как второй параметр в методе loadClass неявно указан как false, он запрещает установление ссыллок на класс
//            System.out.println(clazz.getClassLoader().getName());
//            System.out.println(clazz);
//       System.out.println(clazz.getClassLoader());
//            System.out.println(DriverManager);
        } catch (ClassNotFoundException e) {
            System.out.println("Error not found");
        }

        try {
            System.out.println(Arrays.toString(DriverManager.drivers().toArray())); // ensureDriversInitialized
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/diplom?user=root&password=s4791978234&ssl=true"); // Практически
            // любой метод DriverManager вызывает метод ensureDriversInitialized, который инициализирует прогрузку ServiceLoader
        } catch (SQLException e) {
            System.out.println("Error Connection");
        }

//        System.out.println("LOOOOG" + this.getClass().getClassLoader());
//        log("LLLLLOOOOG");
//        System.out.println(Arrays.toString(DriverManager.drivers().toArray()));
//        Iterator<Driver> ps = ServiceLoader.load(Driver.class).iterator();
//        System.out.println(ps.hasNext());
//
//            while (ps.hasNext()) {
//                System.out.println(ps.next());
//            }
//

        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>Hello from HelloServlet</h2>");
        } finally {
            writer.close();
        }
        }
}

