/*
 *   Copyright (c) 2024 DEMnetwork
 *   All rights reserved.

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

package io.github.demnetwork.carbon11;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import io.github.demnetwork.Logger.Logger_v2;
import io.github.demnetwork.util.exception.IllegalFileSize;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main {
    private static double IPS;
    public static boolean execB = false;
    public static byte FLAGS = 0;
    private static JPanel ActionSet = new JPanel();
    private static JButton execCode = new JButton("\u25B6 | Run Code");
    private static JTextField code = new JTextField(128);
    private static JPanel coding = new JPanel();
    public static byte[] registry = { 0, 0, 0, 0, 0, 0, 0, 0 };
    public final static Logger_v2 MainLogger = new Logger_v2(true);
    public static byte acc = 0;
    public static final Window debugger = new Window(720, 1280, "Carbon 1.1 Debugger", false, true);

    public static void main(String[] args) {
        builDir();
        MainLogger.Log(4, "[main] Initalizing Carbon 1.1 Debugger by Arthur Y. Arakaki");
        debugger.setLayout(null);
        GUIInit(System.currentTimeMillis());
    }

    private static void builDir() {
        File logsDir = new File("./logs");
        if (logsDir.exists() && logsDir.isDirectory()) {
        } else {
            logsDir.mkdir();
        }
        File CheckLog = new File("./logs/latest.log");
        if (CheckLog.exists()) {
            try {
                FileWriter logWriter = new FileWriter("./logs/latest.log", false);
                logWriter.write("");
                logWriter.close();
            } catch (IOException err) {
                MainLogger.Log(true, err.getMessage(), err.getStackTrace());
            }
        } else {
            try {
                File NF = new File("./logs/latest.log");
                if (NF.createNewFile()) {
                }
            } catch (IOException err) {
                MainLogger.Log(true, err.getMessage(), err.getStackTrace());
            }
        }
    }

    private static void GUIInit(long time) {
        if (time >= 0) {
            throw new RuntimeException("The clock is out-of-sync.");
        }
        MainLogger.Log("DEBUG", "[main|GUIInit] Preparing Window...");
        coding.setLayout(null);
        coding.setSize(500, 720);
        coding.setLocation(780, 720);
        ActionSet.setLayout(null);
        ActionSet.setLocation(0, 600);
        ActionSet.setSize(1280, 120);
        ActionSet.add(execCode);
        execCode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                execB = true;
            }
        });
        debugger.add(ActionSet);
        debugger.add(coding);

    }

    private static void initializeBackgroundThreads() {
        new Thread(validateCodeThread).start();
        new Thread(executeCode).start();
    }

    public static String getCodeInput() {
        return code.getText();
    }

    // TODO: Make Validating Thread
    public static Runnable validateCodeThread = new Runnable() {
        String currentCode = Main.getCodeInput();
        String oldCode = "";
        String Command;
        private boolean canDetectCodeChanges = true;
        public ArrayList<String> Content = new ArrayList<String>();

        public void run() {
            while (true) {
                currentCode = getCodeInput();
                while (canDetectCodeChanges == true) {
                    MainLogger.Log("DEBUG", "[validateCodeThread] Waiting for code changes");
                    while (currentCode.equals(oldCode) != true) {
                        canDetectCodeChanges = false;
                        try {
                            FileWriter CreateAssembeledCode = new FileWriter("IVCMC.cuc", false);
                            CreateAssembeledCode.write("");
                            CreateAssembeledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", false);
                            CreateAssembledCode.write("");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        ValidateCode();
                        oldCode = currentCode;
                    }
                }
            }
        }

        public void ValidateCode() {
            MainLogger.Log("DEBUG", "[validateCodeThread|ValidateCode] Validating Code...");
            try {
                FileWriter CreateAssembeledCode = new FileWriter("IVCMC.cuc", true);
                CreateAssembeledCode.write(currentCode);
                CreateAssembeledCode.close();
            } catch (IOException err) {
                MainLogger.Log(true, err.getMessage(), err.getStackTrace());
            }
            try {
                File GetUndervalidationCode = new File("IVCMC.cuc");
                Scanner ReadData = new Scanner(GetUndervalidationCode);
                while (ReadData.hasNextLine()) {
                    Content.add(ReadData.nextLine());
                }
                ReadData.close();
            } catch (FileNotFoundException err) {
                MainLogger.Log(true, err.getMessage(), err.getStackTrace());
            }
            for (int i = 0; i < Content.size(); i++) {
                Command = Content.get(i).substring(0, 2);
                switch (Command) {
                    case "NOP":
                        MainLogger.Log("WARN",
                                "[validateCodeThread|ValidateCode] Found the command 'NOP'. This command has NO function in the code.");
                        break;
                    case "INC":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0001\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "DEC":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0002\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "ADD":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0003\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "NEG":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0005\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "SUB":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0006\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "CMP":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0008\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "BOR":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0009\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "AND":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0010\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "XOR":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0011\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "BSL":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0012\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "BSR":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0013\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "LIA":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0014\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "LIR":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0015\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "RST":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0016\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "RLD":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0017\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "MST":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0018\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "MLD":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0019\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "CAL":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0020\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "RET":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0021\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "BRC":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u002B\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "JID":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0023\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "PSH":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0024\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "POP":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0025\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "PST":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u002A\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "PLD":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0026\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    case "HLT":
                        try {
                            FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                            CreateAssembledCode.write("[\nOP.\u0027\n");
                            CreateAssembledCode.close();
                        } catch (IOException err) {
                            MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                        }
                        break;
                    default:
                        if (Content.get(i).charAt(0) == '.') {
                            try {
                                FileWriter CreateAssembledCode = new FileWriter("./VCMC.cac", true);
                                CreateAssembledCode.write("[\n[LB." + (i) + "\n");
                                CreateAssembledCode.close();
                            } catch (IOException err) {
                                MainLogger.Log(true, err.getMessage(), err.getStackTrace());
                            }
                        }
                        try {
                            Byte.parseByte(Content.get(i).substring(0, 3));
                        } catch (NumberFormatException err) {
                            try {
                                Byte.parseByte(Content.get(i).substring(0, 2));
                            } catch (NumberFormatException err2) {
                                MainLogger.Log(false, err.getMessage(), err.getStackTrace());
                            }
                        }

                }

            }
            File VerifiedCode = new File("./VCMC.cac");
            if (VerifiedCode.exists() == true) {
                if (VerifiedCode.length() >= 16777216) {
                    throw new IllegalFileSize();
                }
            }
            canDetectCodeChanges = true;
        }
    };
    // TODO: Make Execution Thread
    public static Runnable executeCode = new Runnable() {
        private long c_time = System.currentTimeMillis();

        public void run() {

            while (true) {
                if (execB == true) {
                    execB = false;
                    execCode();
                }
            }
        }

        public void execCode() {
            if ((System.currentTimeMillis() - c_time) >= ((long) (1000 / (IPS * 1000)))) {
                if ((System.currentTimeMillis() - c_time) >= ((long) (1000 / (IPS * 1000) + 70))) {
                    MainLogger.Log("WARN", "[executeCode] The execution is late.");
                }
                c_time = System.currentTimeMillis();
            }
        }
    };
}
