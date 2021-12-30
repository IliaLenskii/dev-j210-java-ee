package wsclients;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;


/**
 *
 * @author il.lenskii
 */
@WebServlet(name = "IndexRequest", urlPatterns = {"/index-request"})
@MultipartConfig
public class IndexRequest extends HttpServlet {

    @WebServiceRef /*(wsdlLocation="http://127.0.0.1:8080/DemoNewWebService/NewWebService?WSDL")*/
    private DemoNewWebService dWSer;

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
        
        response.setContentType("text/html;charset=UTF-8");

        boolean isPost = "POST".equals(request.getMethod());
        String mainPage = request.getContextPath();
        
        if(!isPost) {
            
            response.sendRedirect(mainPage);
            return;            
        }

        String postUser = request.getParameter("user");
        String postMsg = request.getParameter("msg");

        if(dWSer != null) {
            
            try {
                
                NewWebService ws = dWSer.getNewWebServicePort();
            
                ws.add(postUser, postMsg);
                
                
                System.out.println( ws.getAllMessage(postUser) );
                
            } catch(Exception ex) {
                
                System.out.println(ex.getMessage());
            }
        }

        response.sendRedirect( mainPage );
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
