package VoteApp.controller;


import VoteApp.service.VoteService;
import VoteApp.storage.VoteStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "QuizServlet", urlPatterns = "/")
public class QuizServlet extends HttpServlet {

    private final VoteService service;
    private final VoteStorage storage;


   public QuizServlet(){
       this.service=VoteService.getInstance();
       this.storage=VoteStorage.getInstance();

   }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer=resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Голосование</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"./\" method=\"post\" name=\"voting\">\n" +
                "    <h2> Лучший исполнитель</h2>\n" +
                "    <select name=\"artist\" size=\"1\">\n" +
                "        <option value=\"1\">Modern Talking</option>\n" +
                "        <option selected=\"selected\" value=\"2\">Dima Bilan</option>\n" +
                "        <option value=\"3\">Eminem</option>\n" +
                "        <option value=\"4\">System of a down</option>\n" +
                "        </select>\n" +
                "\n" +
                "    <h2>Любимый жанр</h2>\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"1\"/>Поп<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"2\" /> Рок<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"3\" /> Рэп<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"4\" /> Хэви-метал<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"5\" /> r&b<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"6\" />Джаз<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"7\" />Техно<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"8\" />Диско<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"9\" />Классика<br />\n" +
                "    <input type=\"checkbox\" name=\"music\" value=\"10\" />Опера<br />\n" +
                "\n" +
                "    <h2>О себе</h2>\n" +
                "    <textarea name=\"about\" rows=\"10\" cols=\"20\"></textarea>\n" +
                "    <input type=\"submit\" value=\"отправить данные\"/>\n" +
                "</form>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String artist=req.getParameter("artist");
        String[]music= req.getParameterValues("music");
        String about=req.getParameter("about");

        this.service.addVote(artist,music,about);

        Map<String, Integer> artistResult = this.service.getArtistResult();
        Map<String, Integer> musicResult = this.service.getMusicResult();
        Map<String,String> aboutResult = this.service.getAboutResult();

        PrintWriter writer= resp.getWriter();


        writer.write("<h1>Результаты голосования!</h1>" +
                "<h2>Лучший исполнитель!</h2>");
        final Map<String, Integer> sortArtist = this.service.sort(artistResult);
        for(Map.Entry<String,Integer> entry:sortArtist.entrySet()){
            switch (entry.getKey()){
                case "1":
                    writer.write("<p>Modern Talking");
                    break;
                case "2":
                    writer.write("<p>Dima Bilan");
                    break;
                case "3":
                    writer.write("<p>Eminem");
                    break;
                case "4":
                    writer.write("<p>System of a Down");
                    break;
            }
            writer.write(" "+String.valueOf(entry.getValue())+"</p>");
        }
        writer.write("<h2>Любимый стиль музыки</h2>");
        final Map<String, Integer> sortMusic = this.service.sort(musicResult);
        for(Map.Entry<String,Integer>entry:sortMusic.entrySet()){
            switch (entry.getKey()){
                case "1":
                    writer.write("<p>Поп");
                    break;
                case "2":
                    writer.write("<p>Рок");
                    break;
                case "3":
                    writer.write("<p>Рэп");
                    break;
                case "4":
                    writer.write("<p>Хэви-метал");
                    break;
                case "5":
                    writer.write("<p>r&b");
                    break;
                case "6":
                    writer.write("<p>Джаз");
                    break;
                case "7":
                    writer.write("<p>Техно");
                    break;
                case "8":
                    writer.write("<p>Диско");
                    break;
                case "9":
                    writer.write("<p>Классика");
                    break;
                case "10":
                    writer.write("<p>Опера");
                    break;
            }
            writer.write(" "+String.valueOf(entry.getValue())+"</p>");
        }

        writer.write("<h2>О себе</h2>");
        for (Map.Entry<String,String> entry:aboutResult.entrySet()){
            writer.write("<p>"+entry.getKey()+" "+entry.getValue()+"</p>");
        }

        writer.write("<a href='./'> Голосовать повторно </a>");
    }
}
