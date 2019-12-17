import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/test","op","OpPass123");

            int i;
            try (PreparedStatement ps = con.prepareStatement
                    ("insert into users values(?,?,?,?)")) {

                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, userName);
                ps.setString(4, password);

                i = ps.executeUpdate();
            }

            if(i > 0) {
                out.println("You are successfully registered. Please login to start quest"
                + "<a href=\"dblogin\">Login</a>");
            }

        }
        catch(Exception se) {
            se.printStackTrace();
        }

    }
}
