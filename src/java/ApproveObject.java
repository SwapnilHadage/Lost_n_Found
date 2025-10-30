import jakarta.servlet.RequestDispatcher;
import utils.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.Connection;


@WebServlet("/ApproveObject")
public class ApproveObject extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        PrintWriter out = response.getWriter();
        try(Connection con = DB.getConnection()){
            String id = request.getParameter("id");
            String deleteQuery = "UPDATE lost_objects SET status=? WHERE id=? ";
            
            
            PreparedStatement ps = con.prepareStatement(deleteQuery);
            ps.setString(1,"approved");
            ps.setString(2,id);
            
            
            int rows = ps.executeUpdate();
            
            if(rows>0){
                out.println("<p>Delete Successfully!</p>");
            }else{
                out.println("<p>No Record Found</p>");
            }
                
            RequestDispatcher rd = request.getRequestDispatcher("staffUi.jsp");
            rd.include(request, response);
        }catch(Exception e){
            out.println(e);
        }
            
            
    }
    
}
