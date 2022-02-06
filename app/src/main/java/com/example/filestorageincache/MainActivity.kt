package com.example.filestorageincache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.filestorageincache.databinding.ActivityMainBinding
import java.io.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val TEMP_FILE_NAME = "cacheFile.txt"
    private var tempFile: File ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tempFile = File(cacheDir.path + "/" + TEMP_FILE_NAME)

        binding.save.setOnClickListener{
            saveData()
            binding.show.isEnabled = true
        }
        binding.show.setOnClickListener {
            showData()
        }
    }

    private fun saveData(){
        val writer:FileWriter

        try {
            writer = FileWriter(tempFile)
            writer.write(binding.enterData.text.toString())
            writer.close()
            Toast.makeText(this, "File Saved in cache", Toast.LENGTH_LONG).show()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    private fun showData() {
    var text = ""
        try {
            val fReader = FileReader(tempFile)
            val bReader = BufferedReader(fReader)
            text = bReader.use(BufferedReader::readText)
        }
        catch (e:FileNotFoundException){
            e.printStackTrace()
        }
        catch (e:IOException){
            e.printStackTrace()
        }
        binding.loadData.setText(text)
    }
}