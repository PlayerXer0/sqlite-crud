package com.example.loginsql;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNombre, editTextApellido, editTextEdad;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextEdad = findViewById(R.id.editTextEdad);
        Button buttonGuardar = findViewById(R.id.buttonGuardar);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String edadText = editTextEdad.getText().toString();

                if (!nombre.isEmpty() && !apellido.isEmpty() && !edadText.isEmpty()) {
                    int edad = Integer.parseInt(edadText);
                    if (db.insertarUsuario(nombre, apellido, edad)) {
                        Toast.makeText(MainActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        db.close(); // Cierra la base de datos cuando se destruye la actividad
        super.onDestroy();
    }
}