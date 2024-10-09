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

package io.github.demnetwork.util.exception;

public class IllegalValueException extends Exception {
    public IllegalValueException(double value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(float value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(long value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(int value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(short value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(byte value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(boolean value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(char value) {
        super("The value(" + value + ") is not accepted");
    }

    public IllegalValueException(String value) {
        super("The value(" + value + ") is not accepted");
    }
}