package com;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@ManagedBean(name = "compilerBean")
public class CompilerBeanBean {

    private Part file;

    private String text;

    public CompilerBeanBean() {
    }

    public void upload(){

        try {
            InputStream is = file.getInputStream();

            file.getContentType();

            int i = 0;
            while((i=is.read()) != -1){
                text+=(char)i;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setFile(Part file){
        this.file = file;
    }

    public Part getFile(){
        return file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
