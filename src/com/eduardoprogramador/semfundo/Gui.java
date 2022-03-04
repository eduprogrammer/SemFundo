package com.eduardoprogramador.semfundo;

/*
 * Copyright 2022. Eduardo Programador
 * www.eduardoprogramador.com
 * consultoria@eduardoprogramador.com
 *
 * Todos os direitos reservados
 * */

import javax.swing.*;

public class Gui {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Layout("Imagem Sem Fundo");
        });
    }
}