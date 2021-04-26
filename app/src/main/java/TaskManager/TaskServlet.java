package TaskManager;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    List<Task> tasks = new ArrayList<Task>();
    List<Task> completedTasks = new ArrayList<Task>();
    HashMap<Integer, String> types = new HashMap<Integer, String>();

    private int getTypeId(HashMap<Integer, String> types, String task){
        for (int id : types.keySet()) {
            if (task.equals(types.get(id))){
                return id;
            }
        }
        return 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect("index.html");
        } else {
            TaskDao taskDao = new TaskDao();
            TypeDao typeDao = new TypeDao();
            types = typeDao.getAllTypes();
            tasks = taskDao.getAllTasks(types, (int) req.getSession().getAttribute("user"));
            System.out.println(req.getSession().getAttribute("user"));
            String jsonList = mapper.writeValueAsString(tasks);
            res.getWriter().println(jsonList);
            taskDao.closeConnection();
            typeDao.closeConnection();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect("index.html");
        } else {
            int typeId;
            Date date = Date.valueOf(req.getParameter("date"));
            String task = req.getParameter("task");
            String type = req.getParameter("type");
            System.out.println(task);
            TaskDao taskDao = new TaskDao();
            if (!types.containsValue(type)){
                Type taskType = new Type(type);
                TypeDao typeDao = new TypeDao();
                typeId = typeDao.insertType(taskType);
                typeDao.closeConnection();
            } else{
                typeId = getTypeId(types, type);
            }
            taskDao.insertTask(date, task, typeId, (int) req.getSession().getAttribute("user"));
            res.sendRedirect("task.html");
        }
    }
}