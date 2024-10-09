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

package io.github.demnetwork.util.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import io.github.demnetwork.util.exception.IllegalAnnotationAttributeValue;

@SupportedAnnotationTypes("MyCustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_18)
public class CommandAnnotationValidator extends AbstractProcessor {

    private String CommandName;
    private String CommandDescription;
    private int CommandID;
    private String CommandOPName;
    private String[] Commands = { "No Operation", "Increment", "Decrement", "Add", "Add to registry", "Negate",
            "Decrement", "", "Compare",
            "Bitwise OR", "Bitwise AND", "Bitwise XOR" };

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(CommandAnnotation.class)) {
            CommandAnnotation Annotation = element.getAnnotation(CommandAnnotation.class);

            CommandName = Annotation.CommandName();
            CommandDescription = Annotation.CommandDescription();
            CommandID = Annotation.CommandID();
            CommandOPName = Annotation.CommandOPName();

            if (CommandID <= -1 || CommandID >= 32) {
                throw new IllegalAnnotationAttributeValue("CommandAnnotation", "CommandID", CommandID);
            }
            if (CommandOPName.length() > 3) {
                throw new IllegalAnnotationAttributeValue("CommandAnnotation", "CommandOPName", CommandOPName);
            }
        }
        return false;
    }

    public String getCommandName(int CommandID) {
        if (CommandID > (Commands.length - 1)) {
            throw new ArrayIndexOutOfBoundsException(CommandID);
        }
        return Commands[CommandID];
    }

    public String getCommandName() {
        return this.CommandName;
    }

    public String[] getCommandInfo() {
        String[] CommandInfo = { this.CommandName, this.CommandDescription, this.CommandOPName, "" + this.CommandID };
        return CommandInfo;
    }

    public String getCommandDescription() {
        return this.CommandDescription;
    }
}
