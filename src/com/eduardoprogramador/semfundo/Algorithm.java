package com.eduardoprogramador.semfundo;

/*
 * Copyright 2022. Eduardo Programador
 * www.eduardoprogramador.com
 * consultoria@eduardoprogramador.com
 *
 * Todos os direitos reservados
 * */

import com.eduardoprogramador.dujava.file.FileOperations;
import com.eduardoprogramador.http.Http;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Algorithm {

    public static boolean removeBackground(String imagePath, String outputImagePath) {
        Http http = Http.buildRequest();

        ArrayList<ArrayList> post = new ArrayList<>();

        ArrayList<String> raw = new ArrayList<>();
        raw.add("raw");
        FileOperations operations = new FileOperations();
        byte[] read = operations.readFromFile(imagePath);
        raw.add(Base64.encode(read));
        post.add(raw);

        ArrayList<String> raw_name = new ArrayList<>();
        raw_name.add("raw_name");
        raw_name.add(new File(imagePath).getName());
        post.add(raw_name);

        ArrayList<String> password = new ArrayList<>();
        password.add("password");
        password.add("clubedoeduardoprogramador");
        post.add(password);

        return http.post("eduardoprogramador.com",true,443,"/php/sem_fundo_api.php",post,null,outputImagePath);
    }

    public static String getSwingFile(Container container) {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens","png","jpg","jpeg");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(container);
        if(chooser.getSelectedFile() != null)
            return chooser.getSelectedFile().getPath();
        else
            return null;

    }

    public static String putSwingFile(Container container) {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens","png","jpg","jpeg");
        chooser.setFileFilter(filter);
        chooser.showSaveDialog(container);
        if(chooser.getSelectedFile() != null)
            return chooser.getSelectedFile().getPath() + ".png";
        else
            return null;

    }
}
