package ec.com.ecotrackapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.com.ecotrackapp.controller.SistemaEcoTrack;
import ec.com.ecotrackapp.models.Residuo;

import java.time.LocalDate;

public class RegistroResiduoActivity extends AppCompatActivity {

    private EditText etNombre, etPeso, etZona, etPrioridad;
    private Spinner spTipo;
    private Button btnRegistrar, btnVolver;
    private SistemaEcoTrack sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_residuo);

        sistema = SistemaEcoTrack.getInstance(this);

        inicializarVistas();
        configurarSpinner();
        configurarListeners();
    }

    private void inicializarVistas() {
        etNombre = findViewById(R.id.etNombre);
        etPeso = findViewById(R.id.etPeso);
        etZona = findViewById(R.id.etZona);
        etPrioridad = findViewById(R.id.etPrioridad);
        spTipo = findViewById(R.id.spTipo);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVolver = findViewById(R.id.btnVolver);
    }

    private void configurarSpinner() {
        ArrayAdapter<Residuo.TipoResiduo> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            Residuo.TipoResiduo.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adapter);
    }

    private void configurarListeners() {
        btnRegistrar.setOnClickListener(v -> registrarResiduo());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void registrarResiduo() {
        try {
            String nombre = etNombre.getText().toString().trim();
            String pesoStr = etPeso.getText().toString().trim();
            String zona = etZona.getText().toString().trim();
            String prioridadStr = etPrioridad.getText().toString().trim();

            if (nombre.isEmpty() || pesoStr.isEmpty() || zona.isEmpty() || prioridadStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double peso = Double.parseDouble(pesoStr);
            int prioridad = Integer.parseInt(prioridadStr);
            Residuo.TipoResiduo tipo = (Residuo.TipoResiduo) spTipo.getSelectedItem();

            if (prioridad < 1 || prioridad > 10) {
                Toast.makeText(this, "La prioridad debe estar entre 1 y 10", Toast.LENGTH_SHORT).show();
                return;
            }

            sistema.registrarResiduo(nombre, tipo, peso, LocalDate.now(), zona, prioridad);
            sistema.guardarDatos(this);

            Toast.makeText(this, "Residuo registrado exitosamente", Toast.LENGTH_SHORT).show();
            limpiarFormulario();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Peso y prioridad deben ser números válidos", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etPeso.setText("");
        etZona.setText("");
        etPrioridad.setText("5");
        spTipo.setSelection(0);
        etNombre.requestFocus();
    }
}
