/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.HaveDAO;
import com.jms.model.Have;
import com.jms.model.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RAKOTOARISOA
 */
public class ChooseTimeSlotServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try ( PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            
            HttpSession session = request.getSession();
            Store store = (Store) session.getAttribute("store");
            try{
                
                SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
                Date datePickUp = DF.parse(request.getParameter("date"));

                response.setContentType("application/xml;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                out.println("<?xml version=\"1.0\"?>");
                out.println("<timeSlots>");

                int storeId;
                if (store != null && datePickUp!=null ) {
                    storeId = store.getId();
                    
                    for (Have h : HaveDAO.getTimeSlotsByStoreId(storeId, datePickUp)) {
                        out.println("   <timeSlot>");
                        out.println("       <starTime><![CDATA[" + h.getTimeSlot().getStartTime() + "]]></startTime>");
                        out.println("       <endTime><![CDATA[" + h.getTimeSlot().getEndTime() + "]]></endTime>");
                        out.println("       <capacity><![CDATA[" + h.getCapacity() + "]]></capacity>");
                        out.println("   </timeSlot>");
                    }
                }
                out.println("</timeSlots>");
                
                Have have = new Have();
                have.setDate(datePickUp);
                session.setAttribute("have", have);
                
            }catch( ParseException ex){
                ex.getMessage();
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
     * @throws java.text.ParseException
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