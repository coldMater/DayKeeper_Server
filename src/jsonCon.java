


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jsonCon")
public class jsonCon extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
         
         String returns = "";
         String type = request.getParameter("type");

         String before = request.getParameter("vision_write");
         System.out.println(before);
         String[] temp = before.split("`");
         System.out.println(temp[0]);
         System.out.println(temp[1]);
         String vision = temp[0];
         String selectedDate = temp[1];
         System.out.println(vision);
         System.out.println(selectedDate);
         
         String ID = "1525";
         
         response.setContentType("text/html; charset=utf-8");
                  
         if (type == null) {
               return;
         }else if (type.equals("vision_write")) {
        	   System.out.println("값을받았습니다."+vision);
         }
         
         System.out.println("파일 입력하기");
         String path = "c:\\DayKepper\\out_"+ID+".json";
         System.out.println(path);
         FileOutputStream fw = new FileOutputStream(path);
         fw.write(vision.getBytes());
         
         fw.close();
         Learner learner = new Learner();
         
         response.getWriter().print(learner.learn(path,selectedDate));
         
         

	}
}
