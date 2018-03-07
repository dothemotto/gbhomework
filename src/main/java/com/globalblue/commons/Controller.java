package com.globalblue.commons;

import javafx.scene.Parent;

public interface Controller {

    Parent getView() throws IllegalStateException;

    void setView(Parent view) throws IllegalStateException;
}
