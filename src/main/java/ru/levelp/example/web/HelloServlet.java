package ru.levelp.example.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = { "/wiki/*" })
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAgent = req.getHeader("User-Agent");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();
        pw.println("<!DOCTYPE html>");
        pw.println("<html><head><title>webapp</title></head>");
        pw.print("<body>");
        pw.print("<h1>Привет, ");
        pw.print(userAgent);
        pw.print("!</h1>\n");
        pw.println("</body></html>");

//        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
