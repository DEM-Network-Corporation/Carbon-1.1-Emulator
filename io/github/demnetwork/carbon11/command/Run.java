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

package io.github.demnetwork.carbon11.command;

import io.github.demnetwork.carbon11.command.arithmetic.*;
import io.github.demnetwork.carbon11.command.ctrlunit.*;

/**
 * This class allows the user to run a specific command.
 * 
 * @see io.github.demnetwork.carbon11.command.Command
 */

public class Run {
    /**
     * Used to run a command, however this can only run registered commands.
     * 
     * 
     * @param CommandID - ID of the Command.
     *                  This <strong>ONLY</strong> runs registered commands
     * @param args      - Arguments for the command.
     * 
     * @see io.github.demnetwork.carbon11.command.Command
     */
    public Run(int CommandID, byte[] args) {
        Command command;
        switch (CommandID) {
            case 1:
                command = new INC();
                command.CommandActions(args);
                break;
            case 2:
                command = new DEC();
                command.CommandActions(args);
                break;
            case 3:
                command = new ADR();
                command.CommandActions(args);
                break;
            case 4:
                command = new ADD();
                command.CommandActions(args);
            case 5:
                command = new NEG();
                command.CommandActions(args);
                break;
            case 6:
                command = new SUB();
                command.CommandActions(args);
                break;
            case 8:
                command = new CMP();
                command.CommandActions(args);
                break;
            case 9:
                command = new BOR();
                command.CommandActions(args);
                break;
            default:
                Unknown(CommandID);
        }
    }

    /**
     * Used to run commands
     * <p>
     * This class can run </strong>ANY</strong>
     * {@link io.github.demnetwork.carbon11.command Command}.
     * </p>
     * 
     * 
     * @param Command - Command to execute
     * @param args    - Arguments for the command
     * 
     * @see io.github.demnetwork.carbon11.command.Command
     */
    public Run(Command Command, byte[] args) {
        Command.CommandActions(args);
    }

    private void Unknown(int CommandID) {
        io.github.demnetwork.carbon11.Main.MainLogger.Log("WARN",
                "The Command with the ID " + CommandID + " was not found");
    }

}
