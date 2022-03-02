package Pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet{
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter pw = response.getWriter();
      // 1. 파라미터로 전송된 값을 얻어오기.
      request.setCharacterEncoding("UTF-8");
      String id = request.getParameter("id");
      String pwd= request.getParameter("pwd");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");      

      int n=0;
      PreparedStatement pstmt2 = null;
      PreparedStatement pstmt = null;
      Connection con = null;
      ResultSet rs = null;

      try{
         // 2. 전송된 값을 db에 저장.
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://18.205.188.103:3306/test?&useSSL=false";
	     con = DriverManager.getConnection(url, "lion", "1234");
         
//			String url = "jdbc:mysql://localhost:3306/test?&useSSL=false";
//			con = DriverManager.getConnection(url, "root", "1234");
         
         String checksql = "select * from members where id=?";
         pstmt2 = con.prepareStatement(checksql);
         pstmt2.setString(1, id);
         rs = pstmt2.executeQuery();
         while(rs.next()) {
            if(rs.getString(1).equals(id) == true) {
               pw.println("<html>");
               pw.println("<head></head>");
               pw.println("<body>");
               pw.println("아이디중복 인해 가입에 실패했습니다.<br/>");
               pw.println("<a href='javascript:history.go(-1)'>이전페이지로 가기</a>");
               pw.println("</body>");
               pw.println("</html>");
               pstmt2.close();
               rs.close();
               return;
            }
            pstmt2.close();
            rs.close();
         }
         
         String sql = "insert into members values( ?,?,?,?, now() )";
            
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, pwd);
         pstmt.setString(3, email);
         pstmt.setString(4, phone);

         //sql구문 실행하기
         n=pstmt.executeUpdate();


      }catch(ClassNotFoundException ce){
         System.out.println(ce.getMessage());
      }catch(SQLException se){
         System.out.println(se.getMessage());
      }finally{
         try{
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
         }catch(SQLException se){
            System.out.println(se.getMessage());
         }
      }

      // 3. 사용자(클라이언트)에 결과를 응답하기.
      pw.println("<html>");
      pw.println("<head></head>");
      pw.println("<body>");

      if(n>0){
         pw.println( id + "님! 성공적으로 가입되었습니다.<br/>");
         pw.println("<a href='index.html'>메인페이지 이동</a>");

      }else{
         pw.println("오류로 인해 가입에 실패했습니다.<br/>");
         pw.println("<a href='javascript:history.go(-1)'>이전페이지로 가기</a>");
      }   
      pw.println("</body>");
      pw.println("</html>");
   }

}