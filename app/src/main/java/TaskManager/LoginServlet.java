package TaskManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("password");

        if (username != null && password != null) {
            User user = new User(username, password);
            UserDao dao = new UserDao();
            if (dao.authenticateUser(user) != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user.getUserId());
                res.sendRedirect("task.html");
            } else {
                res.sendRedirect("index.html");
            }
        }
    }
}
