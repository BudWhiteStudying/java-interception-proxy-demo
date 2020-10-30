package com.budwhite.studying.intercept.execution;

import com.budwhite.studying.intercept.execution.proxy.BeforeAndAfterLoggingProxy;
import com.budwhite.studying.sample.library.Model;
import com.budwhite.studying.sample.library.SomeImplementingClass;
import com.budwhite.studying.sample.library.SomeInterface;

public class Main {
    public static void main(String[] args) {
        try {
            SomeInterface someInterface = (SomeInterface)(BeforeAndAfterLoggingProxy.newInstance(new SomeImplementingClass()));
            someInterface.someInterfaceMethod(new Model("pippo", 7));
        }
        catch (Exception e) {
            System.err.println("Oh noes " + e.getMessage());
            e.printStackTrace();
        }
    }
}
