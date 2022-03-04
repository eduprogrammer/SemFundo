package com.eduardoprogramador.semfundo;

/*
* Copyright 2022. Eduardo Programador
* www.eduardoprogramador.com
* consultoria@eduardoprogramador.com
*
* Todos os direitos reservados
* */

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Layout extends JFrame {
    private boolean isIn, isOut;
    private String in, out;


    public Layout(String title) {
        super(title);
        fillComponents();
    }

    private void fillComponents() {
        Container container = getContentPane();


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        JLabel jLabelBack = new JLabel();


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            Image image = toolkit.getImage(new URL("http://eduardoprogramador.com/img/demo_fundo.jpg"));
            setIconImage(image);
            ImageIcon imageIcon = new ImageIcon(new URL("http://eduardoprogramador.com/img/demo_fundo.jpg"));
            jLabelBack.setIcon(imageIcon);
            jLabelBack.setBounds(0,0,500,500);

        } catch (Exception ex) {
            ex.printStackTrace();
        }



        setLayout(null);
        setResizable(false);


        JLabel jLabelTitle = new JLabel("Imagem Sem Fundo");
        jLabelTitle.setBounds(180,50,150,30);
        JLabel jLabelIn = new JLabel("Imagem Original:");
        jLabelIn.setBounds(50,150,150,30);
        JTextField fieldIn = new JTextField();
        fieldIn.setEditable(false);
        fieldIn.setBounds(210,150,150,30);
        JButton btnIn = new JButton("Procurar");
        btnIn.setBounds(370,150,100,30);
        JLabel jLabelOut = new JLabel("Imagem Convertida");
        jLabelOut.setBounds(50,210,150,30);
        JTextField fieldOut = new JTextField();
        fieldOut.setEditable(false);
        fieldOut.setBounds(210,210,150,30);
        JButton btnOut = new JButton("Salvar");
        btnOut.setBounds(370,210,100,30);

        JLabel jLabelStatus = new JLabel();

        jLabelStatus.setBounds(170,250,300,30);
        jLabelStatus.setFont(new Font("calibri",Font.BOLD,14));

        JButton btnStart = new JButton("Iniciar");
        btnStart.setBounds(200,300,100,30);

        JLabel jLabelCredits = new JLabel("Copyright 2022. Eduardo Programador");
        jLabelCredits.setBounds(130,400,400,30);


        setBounds(0,0,500,500);
        setLocationRelativeTo(null);

        container.add(jLabelTitle);
        container.add(jLabelIn);
        container.add(jLabelOut);
        container.add(jLabelCredits);
        container.add(fieldIn);
        container.add(fieldOut);
        container.add(btnIn);
        container.add(btnOut);
        container.add(btnStart);
        container.add(jLabelStatus);
        container.add(jLabelBack);

        btnIn.addActionListener(e -> {
            in = Algorithm.getSwingFile(container);
            if(in != null) {
                fieldIn.setText(in);
                isIn = true;
            } else {
                isIn = false;
            }
        });

        btnOut.addActionListener(e -> {
            out = Algorithm.putSwingFile(container);
            if(out != null) {
                fieldOut.setText(out);
                isOut = true;
            } else {
                isOut = false;
            }
        });

        btnStart.addActionListener(e -> {
            if(isOut && isIn) {
                jLabelStatus.setText("[*] Aguarde. Processando...");
                jLabelStatus.setVisible(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(Algorithm.removeBackground(in,out)) {
                            jLabelStatus.setText("[*] Fundo Removido com Sucesso");
                            jLabelStatus.setVisible(true);
                        } else {
                            jLabelStatus.setText("[*] Falha na remoção do Fundo");
                            jLabelStatus.setVisible(true);
                        }
                    }
                }).start();
            } else {
                jLabelStatus.setText("[*] Você precisa selecionar os arquivos");
                jLabelStatus.setVisible(true);
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }


}
