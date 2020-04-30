package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String filename = request.getParameter("filename");
		String fileType = getServletContext().getMimeType(filename);
		response.addHeader("Content-Type", fileType);
		response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8"));
		String folder = "/download/";
		InputStream in = getServletContext().getResourceAsStream(folder+filename);
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = in.read(buffer))!=-1) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
