package TaskManager;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App {

    public static void main(String[] args) throws LifecycleException {
        final int PORT = 8080;
        Tomcat server = new Tomcat();
        server.getConnector();
        server.setPort(PORT);
        Context context = server.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        WebResourceRoot resource = new StandardRoot(context);
        resource.addPreResources(new DirResourceSet(resource, "/WEB-INF/classes", new File("build/classes/java/main").getAbsolutePath(), "/"));
        context.setResources(resource);
        
        server.addServlet("", "task", new HttpServlet(){
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                HttpSession session = req.getSession();
                String user = (String) session.getAttribute("user");
                Task task = new Task();
                Manager manager = new Manager();
                // if (user != null && user.equals("admin")) {
                //     res.getWriter().println("Welcome admin");
                // } else {
                //     res.getWriter().println("Welcome " + user);
                // }
                // task.setId(Integer.parseInt(req.getParameter("taskId")));
                // task.setDate(Date.valueOf(req.getParameter("taskDate")));
                // task.setTask(req.getParameter("task"));
                // task.setTypeId(Integer.parseInt(req.getParameter("typeId")));
                // task.setComplete(Boolean.parseBoolean(req.getParameter("complete")));
            }
        });
        context.addServletMappingDecoded("/home", "task");

        //Login servlet
        //work on user authentication here
        server.addServlet("", "login", new HttpServlet(){
            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                String user, pass;
                System.out.println("Request: " + req.getQueryString());
                if ((user = req.getParameter("user")) != null && ((pass = req.getParameter("pass")) != null)) {
                    if (user.equals("admin") && pass.equals("admin")){
                        // HttpSession session = req.getSession();
                        // session.setAttribute("user", user);
                        res.getWriter().println("Successful Login");
                    } else {
                        res.sendRedirect("/");
                    }
                }
            }
        });
        context.addServletMappingDecoded("/login", "login");

        server.start();
        server.getServer().await();
    }
}
