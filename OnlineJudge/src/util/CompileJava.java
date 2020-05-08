package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CompileJava extends Thread{
	private String code=null;
	private String id=null;
	private String input=null;
	private String output=null;
	private String result=null;
	public void setData(String code,String id,String input,String output) {
		this.code=code;
		this.id=id;
		this.input=input;
		this.output=output;
	}
	public void run() {
		compileJava();
	}
	public void compileJava() {
		String source =  code;
        File folder = new File("C:\\Users\\Administrator\\Desktop");
        File sourceFile = new File(folder, "Solution"+id+".java");
        try {
            Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            result= "Running Environment Error!";
        }

        int compilationResult = compiler.run(null, null, null, sourceFile.getPath());
        if (compilationResult != 0) {
        	result= "Compile Error";
        }
        try {
        	URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {folder.toURI().toURL() });
            Class<?> cls = Class.forName("Solution"+id, true, classLoader);
            Object instance = cls.newInstance();
            Method method = cls.getDeclaredMethod("solution", String.class);
            String ans=(String)method.invoke(instance, input);
            
            File classfile = new File(folder, "Solution"+id+".class");
            sourceFile.delete();
            classfile.delete();
            result= ans.equals(output)?"Accept":"Wrong Answer";
        } catch (Exception e) {
            e.printStackTrace();
            sourceFile.delete();
            result= "Compile Error";
        }
	}
	public String getResult(){
		return result;
	}
}
