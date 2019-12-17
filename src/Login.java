import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Login", urlPatterns = "/login")

public class Login extends HttpServlet {
        static final String TEMPLATE = "<html>" +
                "<head><title>Prog.kiev.ua</title></head>" +
                "<body><h1>%s</h1></body></html>";
        static final Map<String, String> cred = new HashMap<String, String>();
        // hardcode login credentials
        static {
            cred.put("user", "qwerty");
            cred.put("admin", "qazwsx");
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String login = request.getParameter("login");
            String pass = request.getParameter("password");
        String temp = cred.get(login);
        if (pass.equals(temp)){
            String path = "/anketa.html";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        } else {
            response.getWriter().println("Login or password is incorrect <a href= \"index.html\">Try Again</a>");
        }
    }
}
