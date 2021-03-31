/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.StoreDAO;
import com.jms.model.PostIt;
import com.jms.model.ShoppingList;
import com.jms.model.Store;
import static com.jms.util.FileUtil.importShoppingListFromFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author axelt
 */
@MultipartConfig
public class UploadShoppingFileServlet extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(UploadShoppingFileServlet.class.getCanonicalName());

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
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Create path components to save the file
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);
        final File file = new File(fileName);

        try(PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\"?>");
            
            String nameList = request.getParameter("nameList");
            out.println("<shoppingList>");

            out.println("   <name><![CDATA[" + nameList + "]]></name>");
            
            ShoppingList s = importShoppingListFromFile(nameList, file);
            
            for (PostIt p : s.getPostIts()) {
                out.println("   <postIt>");
                out.println("       <libelle><![CDATA[" + p.getWording() + "]]></libelle>");
                out.println("       <product><![CDATA[" + p.getProduct() + "]]></product>");
                out.println("   </postIt>");
            }
            
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String ligne;
                while ((ligne = br.readLine()) != null) {
                    out.println("   <ligne><![CDATA[" + ligne + "]]></ligne>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            out.println("   <object><![CDATA[" + s + "]]></object>");

            out.println("</shoppingList>");
        } catch(Exception ex) {
            try(PrintWriter out = response.getWriter()) {
                out.println("<msg_error>" + ex.getMessage() + "</msg_error>");
            }
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try(PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\"?>");

            Part filePart = request.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            File file = new File(fileName);

            String nameList = request.getParameter("nameList");
            out.println("<shoppingList>");

            ShoppingList s = importShoppingListFromFile(nameList, file);
            out.println("   <name><![CDATA[" + nameList + "]]></name>");

            
            for (PostIt p : s.getPostIts()) {
                out.println("   <postIt>");
                out.println("       <libelle><![CDATA[" + p.getWording() + "]]></libelle>");
                out.println("       <product><![CDATA[" + p.getProduct() + "]]></product>");
                out.println("   </postIt>");
            }
            out.println("   <names><![CDATA[" + request.getParameterNames() + "]]></names>");

            out.println("</shoppingList>");
        } catch(Exception ex) {
            try(PrintWriter out = response.getWriter()) {
                out.println("<msg_error>" + ex.getMessage() + "</msg_error>");
            }
        }
    }*/
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
