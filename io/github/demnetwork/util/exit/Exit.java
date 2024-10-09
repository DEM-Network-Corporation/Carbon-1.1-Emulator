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

package io.github.demnetwork.util.exit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import io.github.demnetwork.Logger.Logger;

@SuppressWarnings("deprecation")
public class Exit {
    public Exit() {
        new Logger("DEBUG", "Saving log file...");
        ArrayList<String> logFileData = new ArrayList<String>();
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        try {
            File logSaver = new File(
                    "./logs/" + year + "-" + month + "-" + day + "." + hour + "." + minute + "." + second + ".log");
            if (logSaver.createNewFile()) {
            } else {
                throw new IOException("The file log file already exists!");
            }
        } catch (IOException err) {
            new Logger(true, err.getMessage(), err.getStackTrace());
        }
        try {
            File latestLogFile = new File("./logs/latest.log");
            Scanner latestLogReader = new Scanner(latestLogFile);
            while (latestLogReader.hasNextLine()) {
                String data = latestLogReader.nextLine();
                logFileData.add(data);
            }
            latestLogReader.close();
        } catch (FileNotFoundException err) {
            new Logger(true, err.getMessage(), err.getStackTrace());
        }
        for (int i = 0; i < logFileData.size(); i++) {
            try {
                FileWriter logWritter = new FileWriter(
                        "./logs/" + year + "-" + month + "-" + day + "." + hour + "." + minute + "." + second + ".log",
                        true);
                logWritter.write(logFileData.get(i) + "\n");
                logWritter.close();
            } catch (IOException err) {
                new Logger(true, err.getMessage(), err.getStackTrace());
            }
        }

    }
}