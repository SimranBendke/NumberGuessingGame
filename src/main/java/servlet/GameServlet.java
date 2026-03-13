package servlet;

import dao.GameDAO;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        GameDAO dao = new GameDAO();

        if ("deleteHistory".equals(action)) {
            dao.deleteAllHistory();
            request.setAttribute("message", "Game history deleted successfully.");
            request.getRequestDispatcher("game.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        Integer target = (Integer) session.getAttribute("target");
        Integer attempts = (Integer) session.getAttribute("attempts");

        if (target == null) {
            target = new Random().nextInt(100) + 1;
            attempts = 0;
        }

        int guess = Integer.parseInt(request.getParameter("guess"));
        attempts++;

        String message;

        if (guess > target) {
            message = "Too High!";
        } else if (guess < target) {
            message = "Too Low!";
        } else {
            message = "Correct! You guessed it in " + attempts + " attempts.";

            String username = (String) session.getAttribute("username");
            if (username == null) {
                username = "Guest";
            }

            dao.saveResult(username, attempts, "WIN");

            request.setAttribute("gameOver", true);
            request.setAttribute("message", message);

            session.invalidate();
            request.getRequestDispatcher("game.jsp").forward(request, response);
            return;
        }

        session.setAttribute("target", target);
        session.setAttribute("attempts", attempts);

        request.setAttribute("message", message);
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
}
