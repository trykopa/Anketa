import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Ank", urlPatterns = "/ank")
public class Ank extends HttpServlet {
    static final String TEMPLATE = "<html>" +
            "<head><title>Prog.kiev.ua</title></head>" +
            "<body><h1>%s</h1></body></html>";
    static final int QUESTION_1_YES = 0;
    static final int QUESTION_1_NO = 1;
    static final int QUESTION_2_YES = 2;
    static final int QUESTION_2_NO = 3;
    final HashMap<String, int[]> quest = new HashMap<>();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        final String q1 = request.getParameter("question1");
        final String q2 = request.getParameter("question2");
        final String q3 = request.getParameter("quest1");
        final String q4 = request.getParameter("quest2");

        HttpSession httpSession = request.getSession(true);
        String login = (String) httpSession.getAttribute("login");

            //save answers to hashmap with username
            final int idx1 = "yes".equals(q1) ? QUESTION_1_YES : QUESTION_1_NO;
            final int idx2 = "yes".equals(q2) ? QUESTION_2_YES : QUESTION_2_NO;
            quest.put(login, new int[]{idx1, idx2});

            String table = "<br />"+ "<table>\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td>User</td>\n" +
                    "      <td>" + q3 + "</td>\n" +
                    "      <td>" + q4 + "</td>\n" +
                    "    </tr>\n";

             for(String key : quest.keySet()){
                    table = table + "    <tr>\n" +
                            "      <td>" + key + "</td>\n" +
                            "      <td>" + (quest.get(key)[0] == 0 ? "yes" : "no") + "</td>\n" +
                            "      <td>" + (quest.get(key)[1] == 2 ? "yes" : "no") + "</td>\n" +
                            "    </tr>\n";
             }

              table = table +
                    "  </tbody>\n" +
                    "</table>";

            String all = "<p>General statistics for Quest:</p>";

            response.getWriter().println(String.format(TEMPLATE, all + table + "<br/>" + "Thank you! And good bye :)" ));


    }


}
