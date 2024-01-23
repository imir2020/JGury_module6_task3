package servlets;

import dto.UserDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        var session = req.getSession();

        System.out.println(session.isNew());
        var user = session.getAttribute(USER);
        if (user == null)
            user = UserDto.builder()
                    .id(2L)
                    .name("Ivan")
                    .build();
        session.setAttribute(USER, user);
    }
}