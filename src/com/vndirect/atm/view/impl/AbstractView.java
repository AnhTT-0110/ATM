package com.vndirect.atm.view.impl;

import com.vndirect.atm.view.View;

import java.util.Scanner;

public class AbstractView implements View {

    private static AbstractView abstractView = new AbstractView();
    public Scanner scanner = new Scanner(System.in);

    public static AbstractView getInstance(){
        return abstractView;
    }


}
