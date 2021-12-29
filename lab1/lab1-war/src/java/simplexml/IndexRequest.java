package simplexml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author il.lenskii
 */
@WebServlet(name = "IndexRequest", urlPatterns = {"/index-request"})
@MultipartConfig
public class IndexRequest extends HttpServlet {    

    private DemoSAX demoSax = new DemoSAX();
    private DemoDOM demoDom = new DemoDOM();
    
    private final String fileXML = "note.xml";

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

        String postVal = request.getParameter("val");
        String postTypeXmlparser = request.getParameter("type-xmlparser");
        
        if(postVal == null || postTypeXmlparser == null) {

            response.sendRedirect(mainPage);
            return;
        }

        if(postVal.length() < 1 || postTypeXmlparser.length() < 1) {

            response.sendRedirect(mainPage);
            return;
        }
        
        String fullPathToXML = getServletContext().getRealPath(fileXML);
        
        File file = new File(fullPathToXML);
        
        if(!file.isFile()) {

            response.sendRedirect(mainPage);
            return;
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
        ArrayList<String> allRes = new ArrayList<>();

        if(postTypeXmlparser.equals("dom")){
            
            demoDom.setTagContFind( postVal );
            
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();
                
                demoDom.setDocument(doc);
                demoDom.findTag();
                
                allRes = demoDom.getResult();

            } catch (ParserConfigurationException ex) {
                Logger.getLogger(IndexRequest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(IndexRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            demoSax.setTagContFind( postVal );
            
            SAXParser saxParser;
            try {

                saxParser = factory.newSAXParser();
                saxParser.parse(file, demoSax);
                
                allRes = demoSax.getResult();

            } catch (ParserConfigurationException ex) {

                Logger.getLogger(IndexRequest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {

                Logger.getLogger(IndexRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(allRes.size() < 1) {

            mainPage += "?errcode=er1";
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
