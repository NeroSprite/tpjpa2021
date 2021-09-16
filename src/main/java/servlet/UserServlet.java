package servlet;
import jpa.EntityManagerHelper;
import jpa.dao.AppointmentDao;
import jpa.dao.UserDao;
import jpa.domain.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="userservlet",
        urlPatterns={"/UserServlet"})
public class UserServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        //Check si si l'email n'est pas déjà présent
        EntityManager manager = EntityManagerHelper.getEntityManager();
        UserDao userDao = new UserDao(manager);
        AppointmentDao appointmentDao = new AppointmentDao(manager);
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        if(!userDao.findByEmail(request.getParameter("mail")).isEmpty()){
            out.println("<HTML>\n<BODY>\n" +
                    "<H1>Email <FONT COLOR=\"red\" >" + mail + " </FONT>already exist</H1>" +
                    "</BODY></HTML>");
        }
        else {
            User userToAdd = new User(name,mail,password);
            userDao.save(userToAdd);
            out.println("<HTML>\n<BODY>\n" +
                    "<H1>Registration complete !</H1>\n" +
                    "<UL>\n" +
                    " <LI>Name: "
                    + name + "\n" +
                    " <LI>Mail: "
                    + mail + "\n" +
                    " <LI>Password: "
                    + password + "\n" +
                    "</UL>\n" +
                    "</BODY></HTML>");
        }
    }

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        //Check si si l'email n'est pas déjà présent
        EntityManager manager = EntityManagerHelper.getEntityManager();
        UserDao userDao = new UserDao(manager);
        AppointmentDao appointmentDao = new AppointmentDao(manager);
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        List<User> userSearched = userDao.findByEmail(request.getParameter("mail"));


        if(userSearched.isEmpty()){
            out.println("<HTML>\n<BODY>\n" +
                    "<H1>No user with the this mail adress: <FONT COLOR=\"red\" >" + mail + " </FONT></H1>" +
                    "</BODY></HTML>");
        }
        else {
            out.println("<HTML>\n<BODY>\n" +
                    "<H1>Registration complete !</H1>\n" +
                    "<UL>\n" +
                    " <LI>Name: "
                    + userSearched.get(0).getName() + "\n" +
                    " <LI>Mail: "
                    + userSearched.get(0).getMail() + "\n" +
                    " <LI>Password: "
                    + userSearched.get(0).getPassword() + "\n" +
                    "</UL>\n" +
                    "</BODY></HTML>");
        }
    }
}
