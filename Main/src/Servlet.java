import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Henry
 * Date: 10/6/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("test");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("test2");
        PrintWriter writer = response.getWriter();
        if (request.getParameter("option") != null) {
            writer.append("<HTML><BODY>A checkbox was pressed</BODY></HTML>");
        } else {
            writer.append("<HTML><BODY>A checkbox was not pressed</BODY></HTML>");
        }
    }
}
