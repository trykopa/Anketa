import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "DBLogin")
public class DBLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String user = request.getParameter("user1");
        String pass = request.getParameter("password");

        try {

            // loading drivers for mysql com.mysql.cj.jdbc.Driver com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false","op","OpPass123");

            String i = " ";

            try (PreparedStatement ps = con.prepareStatement
                    ("select * from users where userName=" + "\"" + user + "\"")) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    i = rs.getString("password");
                }
            }

            if(i.equals(pass)) {
                //send username over session
                HttpSession httpSession = request.getSession(true);
                String login = (String) request.getParameter("user1");
                httpSession.setAttribute("login", login);
                //transfer to quest
                String path = "/anketa.html";
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
                requestDispatcher.forward(request, response);
            } else {
                String tmp = "<a href=\"/reg.html\">Register</a>";
                response.getWriter().println("Login or password is incorrect try to  " + tmp);

            }

        }
        catch(Exception se) {
            se.printStackTrace();
        }

    }


}
