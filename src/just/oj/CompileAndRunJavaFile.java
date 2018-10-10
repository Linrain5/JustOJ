package just.oj;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class CompileAndRunJavaFile {
	private String WID="";
	public String getWID() {
		return this.WID;
	}
	public void setWID(String wid) {
		this.WID=wid;
	}
    private String abc = "";
   public void setabc(String abc) {
	   this.abc=abc;
   }
   public String getabc() {
	   return this.abc;
   }
    
    private String code = "";
    public void setCode(String code) {
    	this.code=code;
    }
    public String getCode() {
    	return this.code;
    }
    
    private StringBuilder ce = new StringBuilder();
    public String getCE(){
        return ce.toString();
    }
    
    private double useMemory = 0.0;
    public double getUseMemory(){
        return useMemory;
    }
   
    private long useTime = 0;
    public long getUseTime(){
        return useTime;
    }

    private StringBuilder outMsg = new StringBuilder();
    public String getOutMsg(){
        return outMsg.toString();
    }

    private String error = null;
    public String getError(){
        return error;
    }

    private boolean isCompileAndRunOK = false; 
    
    public boolean isCompileAndRunOK(){
        return isCompileAndRunOK;
    }
    
    private boolean isWrongAnswer = false;
    public boolean isWrongAnswer() {
    	return isWrongAnswer;
    }
    
    private int limitTime = 2000;

    private double limitMemory = 256000.0;
    
    public void setLimitTime(int limitTime){
        this.limitTime = limitTime;
    }
    
    public void setLimitMemory(double limitMemory){
        this.limitMemory = limitMemory;
    }
    

    private boolean isCompilerError = false;
    public boolean isCompilerError(){
        return isCompilerError;
    }
   
    private boolean isRunningError = false;
    public boolean isRunningError(){
        return isRunningError;
    }
    
    private static final String className = "Main";
    private static final String methodName = "main";
   
    
    
    private String getClassOutput(){
        return "/home/run";
    }
    
    public void compileAndRunJavaFile(String code){
    	System.out.println(System.getProperty("java.class.path"));
    	System.out.println(System.getProperty("just.oj"));
        PrintStream ps = null;
        BufferedReader br = null;
        
        InputStream stdIn = System.in;
        
        PrintStream stdOut = System.out;
        ByteArrayInputStream stream =null;
        
        code = "import java.util.*;\n" + code;
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            
            DiagnosticCollector<JavaFileObject> oDiagnosticCollector = new DiagnosticCollector<JavaFileObject>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(oDiagnosticCollector, null, null);
          
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(new File[] { new File(getClassOutput()) }));
            
            StringSourceJavaObject sourceObject = new CompileAndRunJavaFile.StringSourceJavaObject(className, code);
            Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(sourceObject);
            CompilationTask task = compiler.getTask(null, fileManager, oDiagnosticCollector, null, null, fileObjects);
            boolean result = task.call();
            
            if (result) {
                Runtime runtime = Runtime.getRuntime();
                MyClassLoader mcl = new MyClassLoader(); 
                Class<?> clazz = Class.forName("Main", true, mcl); 
                Method method = clazz.getMethod(methodName, new Class<?>[]{String[].class});
                
              
                String abc = getabc();
                stream = new ByteArrayInputStream(abc.getBytes());
                
                System.setIn(stream);
              
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                ps = new PrintStream(bao);
                System.setOut(ps);
                
                long startFreeMemory = runtime.freeMemory();
             
                long startCurrentTime = System.currentTimeMillis();
                method.invoke(null, new Object[]{null});
                long endCurrentTime = System.currentTimeMillis();
                long endFreeMemory = runtime.freeMemory();
             
                useMemory = (startFreeMemory-endFreeMemory)/1024.0;
                if(useMemory > limitMemory) throw new Exception("Out Limit Memory!");
                useTime = endCurrentTime-startCurrentTime;
                if(useTime > limitTime) throw new Exception("Time Limited!");
                
                br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bao.toByteArray())));
                String outc = null;
                while((outc = br.readLine()) != null)
                    outMsg.append(outc).append("\n");
                
                isCompileAndRunOK = true;
            } else {
                isCompilerError = true;
               
                Pattern p = Pattern.compile("Main.java\\D*(\\d+):", Pattern.DOTALL);
                for (Diagnostic<? extends JavaFileObject> oDiagnostic : oDiagnosticCollector.getDiagnostics()){
               
                    Matcher m = p.matcher("Compiler Error: " + oDiagnostic.getMessage(null));
                    if(m.find()) {
                        ce.append(m.replaceAll("Main.java " + String.valueOf(Integer.valueOf(m.group(1))-1)) + ":").append("\n");
                    } else {
                        ce.append("Compiler Error: " + oDiagnostic.getMessage(null)).append("\n");
                    }
                }
            }
            
        } catch (Exception e) {
            isRunningError = true;
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            Pattern p = Pattern.compile("Main.java\\D*(\\d+)", Pattern.DOTALL);
            Matcher m = p.matcher(sw.toString());
            if(m.find()){
                error = m.replaceAll("Main.java " + String.valueOf(Integer.valueOf(m.group(1))-1) + ":");
            } else {
                error = sw.toString();
            }
        } finally {
            //鍏抽棴娴�
            try {
                if(stream != null)
                    stream.close();
                if(ps != null)
                    ps.close();    
                if(br != null)
                    br.close();
                File cls = new File("/home/run/Main.class");
                cls.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //鎭㈠杈撳叆杈撳嚭娴�
            System.setIn(stdIn);
            System.setOut(stdOut);
        }
    }
    
    private class StringSourceJavaObject extends SimpleJavaFileObject {
        private String content = null;
        public StringSourceJavaObject(String name, String content) {
            super(URI.create(name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }
    public ArrayList<String>  panduan() {
		try {
			ArrayList<String> quest = new ArrayList<>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "select * from question where id="+getWID();
			String url="jdbc:mysql://" + "127.0.0.1" + "/" + "JustOJ" + "?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8";
			Connection conn = DriverManager.getConnection(url, "root", "3455H*B87pp");
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			rs.next();
			quest.add(rs.getString("yanglia_1"));
			quest.add(rs.getString("yanglia_2"));
			quest.add(rs.getString("yanglia_3"));
			quest.add(rs.getString("yanglia_4"));
			quest.add(rs.getString("yanglia_5"));
			pst.close();
			conn.close();
			return quest;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}