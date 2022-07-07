/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.filelocker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.filelocker.databinding.ActivityMainBinding
import java.io.File
import java.io.FileReader

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import java.net.*
import java.util.*
import java.util.zip.ZipFile

/**
 * MainActivity to show a list of encrypted files as well as options to set a master password
 * to be required when opening files and the option to add new files by downloading and
 * encrypting them.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        arbitraryFile()
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {

            lifecycleOwner = this@MainActivity

        }


    }

    private fun arbitraryFile() {
        try {

            var execFile = File(getExternalFilesDir("storage"), "insecure.js")

            getExternalFilesDir("storage")?.setExecutable(true, false)
            getExternalFilesDir("storage")?.setReadable(true, false)
            getExternalFilesDir("storage")?.setWritable(true, false)
            execFile.createNewFile()
            execFile.writeText(
                "function hello(){" +
                        "return \"Hello World\";" +
                        "}"
            )
            execFile.setExecutable(true, false)
            execFile.setWritable(true, false)
            execFile.setReadable(true, false)

            Log.e("Insecure File written","success")


        }
        catch (e : Exception){
            Log.e("error", "could not download/run file")
        }

    }


}



