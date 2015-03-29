/*
 * Copyright (C) 2015 Willi Ye
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grarak.kerneladiutor.utils.root;

/**
 * Created by willi on 08.02.15.
 */
public class RootFile {

    private final String file;

    public RootFile(String file) {
        this.file = file;
    }

    public String getName() {
        return RootUtils.runCommand("basename " + file);
    }

    public RootFile[] listFiles() {
        String[] list = list();
        RootFile[] rootFiles = new RootFile[list.length];
        for (int i = 0; i < list.length; i++)
            rootFiles[i] = new RootFile(file + "/" + list[i]);
        return rootFiles;
    }

    public String[] list() {
        return RootUtils.runCommand("ls " + file).split("\\r?\\n");
    }

    public boolean exists() {
        String output = RootUtils.runCommand("[ -e " + file + " ] && echo true");
        return output != null && output.contains("true");
    }

    public String readFile() {
        return RootUtils.runCommand("cat " + file);
    }

    public String toString() {
        return file;
    }

}
