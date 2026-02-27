package com.example.jk01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/analyze")
public class TextAnalysisServlet extends HttpServlet {

    private static final String VOWELS_STR = "aeiouyауоыиэяюёе";
    private static final String PUNCTUATION_STR = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String text = req.getParameter("userText");
        if (text == null) text = "";

        List<Character> vowelsList = new ArrayList<>();
        List<Character> consonantsList = new ArrayList<>();
        List<Character> punctList = new ArrayList<>();

        for (char c : text.toCharArray())
        {
            if (Character.isWhitespace(c)) continue;

            char lowerC = Character.toLowerCase(c);

            if (PUNCTUATION_STR.indexOf(c) != -1)
            {
                punctList.add(c);
            }
            else if (Character.isLetter(c))
            {
                if (VOWELS_STR.indexOf(lowerC) != -1)
                {
                    vowelsList.add(c);
                }
                else
                {
                    consonantsList.add(c);
                }
            }
        }

        req.setAttribute("originalText", text);

        req.setAttribute("vowelsCount", vowelsList.size());
        req.setAttribute("vowelsData", vowelsList.toString());

        req.setAttribute("consonantsCount", consonantsList.size());
        req.setAttribute("consonantsData", consonantsList.toString());

        req.setAttribute("punctCount", punctList.size());
        req.setAttribute("punctData", punctList.toString());

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
