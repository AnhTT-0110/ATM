package com.vndirect.atm.controller;

import com.vndirect.atm.model.LoginModel;
import com.vndirect.atm.service.MenuService;
import com.vndirect.atm.service.impl.ImplMenuService;
import com.vndirect.atm.view.MenuView;
import com.vndirect.atm.view.impl.AbstractView;
import com.vndirect.atm.view.impl.ConsoleMenuView;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class MenuController {

    private MenuView menuView = ConsoleMenuView.getInstance();
    private static MenuController menuController = new MenuController();
    private LoginModel loginAccount = LoginModel.getInstance();
    private MenuService menuService = ImplMenuService.getInstance();

    public static MenuController getInstance() {
        return menuController;
    }

    public void viewFunctionMenu() {
        try {
            if (!loginAccount.getId().isEmpty()) {
                String choice = menuView.functionMenu();
                menuService.choiceFun(choice);
            } else {
                LoginController.getInstance().printUILoginId();
            }
        } catch (NoSuchElementException e) {
            AbstractView.getInstance().printString("You entered the validation error ");
            viewFunctionMenu();
        }
        catch (NumberFormatException e) {
            AbstractView.getInstance().printString("You entered the validation error ");
            viewFunctionMenu();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String printMenu() {
        String choice = "";
        try {
            choice = menuView.printMenu();
        } catch (InputMismatchException e) {
            AbstractView.getInstance().printString("You entered the validation error ");
            viewFunctionMenu();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return choice;
    }

    public void direct() {
        String choice = printMenu();
        if (choice.equals("1")) {
            MenuController.getInstance().viewFunctionMenu();
        } else {
            LoginController.getInstance().viewMenu();
        }
    }


}
