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

package com.grarak.kerneladiutor.utils.kernel;

import android.content.Context;

import com.grarak.kerneladiutor.utils.Constants;
import com.grarak.kerneladiutor.utils.Utils;
import com.grarak.kerneladiutor.utils.root.Control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by willi on 27.12.14.
 */
public class VM implements Constants {

    private static List<String> vmFiles = new ArrayList<>();

    public static void setVM(String value, String name, Context context) {
        Control.runCommand(value, VM_PATH + "/" + name, Control.CommandType.GENERIC, context);
    }

    public static String getVMValue(String file) {
        if (Utils.existFile(VM_PATH + "/" + file)) {
            String value = Utils.readFile(VM_PATH + "/" + file);
            if (value != null) return value;
        }
        return null;
    }

    public static List<String> getVMfiles() {
        if (vmFiles.size() < 1) {
            File[] files = new File(VM_PATH).listFiles();
            if (files.length > 0) {
                for (String supported : SUPPORTED_VM)
                    for (File file : files)
                        if (file.getName().equals(supported))
                            vmFiles.add(file.getName());
            }
        }
        return vmFiles;
    }

}
