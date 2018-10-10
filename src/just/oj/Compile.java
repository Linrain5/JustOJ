package just.oj;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Compile")
public class Compile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Compile() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String lang = request.getParameter("language");
		if(lang.equals("java")) {
		CompileAndRunJavaFile cr = new CompileAndRunJavaFile();
		cr.setCode(code);
		cr.compileAndRunJavaFile(cr.getCode());
		response.setCharacterEncoding("UTF-8");
		if(cr.isCompileAndRunOK()) {
			response.getWriter().append("运行时间").append(String.valueOf(cr.getUseTime()));
			response.getWriter().append("使用内存").append(String.valueOf(cr.getUseMemory()));
			response.getWriter().append("输出:").append(String.valueOf(cr.getOutMsg()));
		}else if(cr.isCompilerError()) {
			response.getWriter().append("编译错误").append(String.valueOf(cr.getCE()));
		}else if(cr.isRunningError()) {
			response.getWriter().append("运行错误:").append(String.valueOf(cr.getError()));
		}
		}
		
	}

}
