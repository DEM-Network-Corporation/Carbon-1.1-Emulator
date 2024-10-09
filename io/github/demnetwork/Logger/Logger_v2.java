/*
 *   Copyright (c) 2024 DEMnetwork

 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package io.github.demnetwork.Logger;

import io.github.demnetwork.util.exception.*;
import io.github.demnetwork.util.exit.Exit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author DEMNetwork(Arthur Y. Arakaki)
 * 
 * 
 * @apiNote This is a optimised version of
 *          {@link io.github.demnetwork.Logger.Logger}.
 *          This uses voids for logging instead of
 *          constructors, optimising the experince for
 *          some users.
 *          The usage of this logger is not required,
 *          as long as any of the loggers in this
 *          package gets used to make debugging easiser
 * 
 */

public class Logger_v2 {
    private boolean saveLogs;

    public Logger_v2() {
        saveLogs = true;
    }

    public Logger_v2(boolean saveCreatedLogs) {
        saveLogs = saveCreatedLogs;
    }

    public void Log(String logtype, String logdata) {
        if (logtype != "FATAL" && logtype != "ERROR" && logtype != "WARN" && logtype != "INFO" && logtype != "DEBUG") {
            throw new InvalidLogTypeException(logtype);
        }
        String color = "null";
        switch (logtype) {
            case "FATAL":
                color = "\u001B[37m\u001B41m ";
                break;
            case "ERROR":
                color = "\u001B[31m ";
                break;
            case "WARN":
                color = "\u001B[33m ";
                break;
            case "INFO":
                color = "\u001B[37m ";
                break;
            case "DEBUG":
                color = "\u001B[35m";
        }
        System.out.println(color + "[" + logtype + "] " + logdata + "\n\u001B[37m\u001B[40m ");
        if (saveLogs == true) {
            try {
                FileWriter logWritter = new FileWriter("./logs/latest.log", true);
                logWritter.write("{" + System.currentTimeMillis() + "} [" + logtype + "] " + logdata + "\n");
                logWritter.close();
            } catch (IOException err) {
                Log(true, err.getMessage(), err.getStackTrace());
            }
        }
    }

    public void Log(int logtype, String logdata) {
        if (logtype != 0 && logtype != 1 && logtype != 2 && logtype != 3 && logtype != 4) {
            throw new InvalidLogTypeException(logtype);
        }
        String color = null;
        String logtype2 = null;
        switch (logtype) {
            case 0:
                color = "\u001B[37\u001B[41m ";
                logtype2 = "FATAL";
                break;
            case 1:
                color = "\u001B[31m ";
                logtype2 = "ERROR";
                break;
            case 2:
                color = "\u001B[33m ";
                logtype2 = "WARN";
                break;
            case 3:
                color = "\u001B[37m ";
                logtype2 = "INFO";
                break;
            case 4:
                color = "\u001B[35m";
                logtype2 = "DEBUG";
        }
        System.out.println(color + "[" + logtype2 + "] " + logdata + "\n\u001B[37m\u001B[40m ");
        if (saveLogs == true) {
            try {
                FileWriter logWritter = new FileWriter("./logs/latest.log", true);
                logWritter.write("{" + System.currentTimeMillis() + "} [" + logtype2 + "] " + logdata + "\n");
                logWritter.close();
            } catch (IOException err) {
                Log(true, err.getMessage(), err.getStackTrace());
            }
        }
    }

    public void Log(boolean isFatal, String err_msg, StackTraceElement[] err) {
        int length = err.length;
        ArrayList<String> logdata = new ArrayList<String>();
        for (int i = 0; i < length; i++) {
            logdata.add(err[i].toString());
        }
        String flogdata;
        String logtype = null;
        if (isFatal == true) {
            logtype = "FATAL";
        } else {
            logtype = "ERROR";
        }
        Log(logtype, err_msg, true);
        for (int i = 0; i < logdata.size(); i++) {
            flogdata = logdata.get(i);
            Log(logtype, "\tat " + flogdata, true);
        }
        System.out.println("\n");
        if (isFatal == true) {
            new Exit();
            System.exit(2);
        }
    }

    public void Log(String logtype, String logdata, boolean noLineFeed) {
        if (logtype != "FATAL" && logtype != "ERROR" && logtype != "WARN" && logtype != "INFO" && logtype != "DEBUG") {
            throw new InvalidLogTypeException(logtype);
        }
        String color = "null";
        switch (logtype) {
            case "FATAL":
                color = "\u001B[37m\u001B[41m ";
                break;
            case "ERROR":
                color = "\u001B[31m ";
                break;
            case "WARN":
                color = "\u001B[33m ";
                break;
            case "INFO":
                color = "\u001B[37m ";
                break;
            case "DEBUG":
                color = "\u001B[35m";
        }
        if (noLineFeed == false) {
            System.out.println(color + "[" + logtype + "] " + logdata + "\n\u001B[37m\u001B[40m ");
            if (saveLogs == true) {
                try {
                    FileWriter logWritter = new FileWriter("./logs/latest.log", true);
                    logWritter.write("{" + System.currentTimeMillis() + "} [" + logtype + "] " + logdata + "\n");
                    logWritter.close();
                } catch (IOException err) {
                    Log(true, err.getMessage(), err.getStackTrace());
                }
            }
        } else {
            System.out.println(color + "[" + logtype + "] " + logdata + "\u001B[37m\u001B[40m ");
            if (saveLogs == true) {
                try {
                    FileWriter logWritter = new FileWriter("./logs/latest.log", true);
                    logWritter.write("{" + System.currentTimeMillis() + "} [" + logtype + "] " + logdata + "\n");
                    logWritter.close();
                } catch (IOException err) {
                    Log(true, err.getMessage(), err.getStackTrace());
                }
            }
        }
    }

    public void Log(String logtype, String[] logdata) {
        for (int i = 0; i < logdata.length; i++) {
            Log(logtype, logdata[i], false);
            System.out.println("\n");
        }
    }
}
