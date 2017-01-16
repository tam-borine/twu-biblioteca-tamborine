package com.twu.biblioteca;

import sun.applet.Main;

import java.util.ArrayList;

/**
 * Created by tam on 13/01/2017.
 */
public class MainMenu {

    private ArrayList<String> options;

    public MainMenu() {
        options = new ArrayList<String>();
        options.add("List Books");
        options.add("Checkout Book");
        options.add("Return Book");
    }

    public ArrayList<String> showOptions() {
        return options;
    }

    public void addOption(String newMenuOption) {
        options.add(newMenuOption);
    }

    public String selectOption(String someOptionObjMock) {
        return someOptionObjMock;
        //for now... will be a Option() from bookList at some point
    }

    public boolean optionIsValid(String input) {
        return options.contains(input);
    }
}
