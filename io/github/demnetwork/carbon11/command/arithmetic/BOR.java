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

package io.github.demnetwork.carbon11.command.arithmetic;

import io.github.demnetwork.carbon11.command.Command;
import io.github.demnetwork.util.annotation.CommandAnnotation;
import io.github.demnetwork.util.exception.IllegalCommandArgument;

@CommandAnnotation(CommandOPName = "BOR", CommandID = 9, CommandName = "Bitwise OR", CommandDescription = "")
public class BOR implements Command {
    public void CommandActions(byte[] args) {

        if (args.length <= 0) {
            throw new IllegalCommandArgument("Expected one argument but found " + args.length + " argument(s).");
        }
        if (args.length >= 1) {
            throw new IllegalCommandArgument("Expected one argument but found " + args.length + " arguments.");
        }
        switch (args[0]) {
            case 0:
                io.github.demnetwork.carbon11.Main.MainLogger.Log("WARN", "Unable to change registry 0: Access Denied");
                break;
            case 1:
                io.github.demnetwork.carbon11.Main.registry[1] |= args[0];
                break;
            case 2:
                io.github.demnetwork.carbon11.Main.registry[2] |= args[0];
                break;
            case 3:
                io.github.demnetwork.carbon11.Main.registry[3] |= args[0];
                break;
            case 4:
                io.github.demnetwork.carbon11.Main.registry[4] |= args[0];
                break;
            case 5:
                io.github.demnetwork.carbon11.Main.registry[5] |= args[0];
                break;
            case 6:
                io.github.demnetwork.carbon11.Main.registry[6] |= args[0];
                break;
            case 7:
                io.github.demnetwork.carbon11.Main.registry[7] |= args[0];
                break;
            default:
                io.github.demnetwork.carbon11.Main.MainLogger.Log("WARN", "Unknown Registry");
        }
    }

    public String Syntax() {
        return "BOR <registry>";
    }
}
