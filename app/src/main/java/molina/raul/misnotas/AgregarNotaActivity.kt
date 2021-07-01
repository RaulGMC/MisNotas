package molina.raul.misnotas

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_nota.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class AgregarNotaActivity : AppCompatActivity() {
/**
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.id.activity_agregar_nota)

        btn_guardar.setOnClickListener {

        }


    }
*/

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
            235 -> {
                //pregunta si el usuario aceptó los permisos
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    guardar()
                } else {
                    //si no aceptó, coloca un mensaje
                    Toast.makeText(this, "Error: permisos denegados", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    public fun guardar(){
        var titulo = et_titulo.text.toString()
        var cuerpo = et_contenido.text.toString()
        if (titulo == "" || cuerpo == ""){
            Toast.makeText(this, "Error: campos vacíos", Toast.LENGTH_SHORT).show()
        }else{
            try {
                val archivo = File(ubicacion(), titulo + ".txt")
                val fos = FileOutputStream(archivo)
                fos.write(cuerpo.toByteArray())
                fos.close()
                Toast.makeText(
                    this,
                    "se guardó el archivo en la carpeta pública",
                    Toast.LENGTH_SHORT
                ).show()
            }catch (e: Exception){
                Toast.makeText(this,"Error: no se guardó el archivo", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }

    private fun ubicacion(): String{
        val carpeta = File(getExternalFilesDir(null), "notas")
        if (!carpeta.exists()){
            carpeta.mkdir()
        }
        return carpeta.absolutePath
    }
}