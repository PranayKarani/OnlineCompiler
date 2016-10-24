package serv;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;


@WebServlet(name = "CompilerServlet", urlPatterns = "/compile")
@MultipartConfig
public class CompilerServlet extends HttpServlet {

    private final String JAVA = "java";
    private final String CPP = "cpp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        Part file = request.getPart("file");

        String fileName = file.getSubmittedFileName();
        String fileType = isValidFile(fileName);

        switch(fileType){

            case JAVA:

                String path = writeFile(file);
                String terminal = "open /Applications/Utilities/Terminal.app";
                String d = " -d /Users/pranay/IntelliJIDEAProjects/web/crap/";
                String exec = "javac " + path + d;
                try{
                    Runtime.getRuntime().exec(exec);
                }catch(Exception e){
                    out.println(e.getMessage());
                }

                out.println(exec);
                break;
            case CPP:
                // to be done later
                break;

            default:
                out.println("Not a valid file");
                break;

        }


    }

    private String writeFile(Part file) {

        try{
            String path = "/Users/pranay/IntelliJIDEAProjects/web/crap/"+file.getSubmittedFileName();
            FileWriter fos = new FileWriter(path);
            String data = readFile(file).get("data");
            for(int i = 0; i < data.length(); i++){
                fos.write(data.charAt(i));
            }
            fos.close();
            return path;
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;

    }

    private String isValidFile(String filename){

        boolean isJavaFile = false;

        String ext = filename.substring(filename.length()-4).toLowerCase();

        switch(ext){
            case JAVA: return JAVA;
            case CPP: return CPP;
            default:
                return "-1";
        }

    }

    private HashMap<String,String> readFile(Part file){

        String text = "";
        HashMap<String,String> fileData = new HashMap<>();

        try {
            InputStream is = file.getInputStream();

            file.getContentType();

            int i = 0;
            while((i=is.read()) != -1){
                text+=(char)i;
            }

            is.close();

            String classname =

            return fileData;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
