package Pack;



import java.io.IOException;


import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Timestamp;



import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



public class ListServlet extends HttpServlet{

	@Override

	protected void service(HttpServletRequest resquest, HttpServletResponse response)

			throws ServletException, IOException {

		Connection con=null;

		PreparedStatement pstmt=null;

		ResultSet rs = null;

		response.setContentType("text/html;charset=UTF-8");



		PrintWriter pw = response.getWriter();

		pw.println("<html>");

		pw.println("<head></head>");

		pw.println("<body>");



		try{

			Class.forName("com.mysql.cj.jdbc.Driver");

						String url = "jdbc:mysql://18.205.188.103:3306/test?&useSSL=false";
					     con = DriverManager.getConnection(url, "lion", "1234");

//			String url = "jdbc:mysql://localhost:3306/test?&useSSL=false";
//			con = DriverManager.getConnection(url, "root", "1234");

			String sql = "select * from members";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();



			pw.println("<div>");

			pw.println("<table border='1' width='1200'>");

			pw.println("<tr>");

			pw.println("<td>아이디</td>");

			pw.println("<td>비밀번호</td>");

			pw.println("<td>이메일</td>");

			pw.println("<td>전화번호</td>");

			pw.println("<td>등록일</td>");

			pw.println("<td>삭제</td>");

			pw.println("<td>수정</td>");

			pw.println("</tr>");

			while(rs.next()){

				System.out.println("lee");

				String id = rs.getString("id");

				String pwd = rs.getString("pwd");

				String email = rs.getString("email");

				String phone = rs.getString("phone");

				Timestamp regdate = rs.getTimestamp("regdate");

				pw.println("<tr>");

				pw.println("<td>" + id + "</td>");

				pw.println("<td>" + pwd + "</td>");

				pw.println("<td>" + email + "</td>");

				pw.println("<td>" + phone + "</td>");

				pw.println("<td>" + regdate + "</td>");

				pw.println("<td><a href='delete.do?id=" + id + "'>삭제</a></td>");

				pw.println("<td><a href='update.do?id=" + id + "'>수정</a></td>");

				pw.println("</tr>");



			}

			pw.println("</table>");

			pw.println("</div>");

			pw.println("<a href='main.html'>메인페이지로 이동</a>");

		}catch(ClassNotFoundException ce){

			System.out.println(ce.getMessage());

		}catch(SQLException se){

			System.out.println(se.getMessage());

		}finally{

			try{

				if(rs!=null) rs.close();

				if(pstmt!=null) pstmt.close();

				if(con!=null) con.close();

			}catch(SQLException se){

				System.out.println(se.getMessage());

			}

		}



		pw.println("</body>");

		pw.println("</html>");

	}

}

