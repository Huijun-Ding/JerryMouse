/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.ShoppingListDAO;
import com.jms.model.Client;
import com.jms.model.PostIt;
import com.jms.model.ShoppingList;
import com.jms.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author axelt
 */
@MultipartConfig
public class UploadShoppingFileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");
                
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Create path components to save the file
        final Part filePart = request.getPart("file");
        
        String fileName = null;
        if(filePart != null) fileName = filePart.getSubmittedFileName();

        try ( PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\"?>");
            out.println("<shoppingList>");
                
            String title = request.getParameter("title");
            String[] postItsParam = request.getParameterValues("postIts");
            
            if(title != null && postItsParam != null) {
                Set<PostIt> postIts = new HashSet<>(0);
                
                for(String postIt : postItsParam)
                    postIts.add(new PostIt(postIt));
                
                out.println("<msg_success>\n");
                out.println("   <title><![CDATA[Ajout de la liste " + title + " réussi]]></title>\n");
                out.println("   <content><![CDATA[Votre liste de courses a bien été enregistrée.]]></content>\n");
                out.println("</msg_success>");
                
                ShoppingListDAO.create(title, postIts, client);
            } else if (fileName != null && !fileName.endsWith("txt") && !fileName.endsWith("csv")) {
                out.println("<msg_error>\n");
                out.println("   <title><![CDATA[Erreur de format !]]></title>\n");
                out.println("   <content><![CDATA[Ce type de fichier n\'est pas pris en charge.]]></content>\n");
                out.println("</msg_error>");
            } else {
                final File file = new File(fileName);

                // commons-io
                FileUtils.copyInputStreamToFile(filePart.getInputStream(), file);

                String nameList = request.getParameter("nameList");

                out.println("   <nameList><![CDATA[" + nameList + "]]></nameList>");

                Set<PostIt> postIts = FileUtil.importPostItsFromFile(file);

                for (PostIt p : postIts) {
                    out.println("   <postIt>");
                    out.println("       <wording><![CDATA[" + URLDecoder.decode(p.getWording(), Charset.forName("UTF-8"))
                            + "]]></wording>");
                    out.println("   </postIt>");
                }
            }
            
            out.println("</shoppingList>");
        } catch (Exception ex) {
            try ( PrintWriter out = response.getWriter()) {
                out.println("<msg_error>" + ex.getMessage() + "</msg_error>");
                out.println("</shoppingList>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
