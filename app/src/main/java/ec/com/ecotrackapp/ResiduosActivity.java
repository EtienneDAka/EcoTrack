package ec.com.ecotrackapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ec.com.ecotrackapp.controller.SistemaEcoTrack;
import ec.com.ecotrackapp.models.Residuo;
import ec.com.ecotrackapp.tda.Comparadores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResiduosActivity extends AppCompatActivity {

    private SistemaEcoTrack sistema;
    private ListView lvResiduos;
    private Spinner spOrdenamiento;
    private Button btnActualizar, btnIterarAdelante, btnIterarAtras, btnVolver;
    private TextView tvInfo;

    private Iterator<Residuo> iteradorActual;
    private int posicionIterador;
    private boolean iterandoAdelante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residuos);

        sistema = SistemaEcoTrack.getInstance(this);
        iteradorActual = null;
        posicionIterador = 0;
        iterandoAdelante = true;

        inicializarVistas();
        configurarSpinner();
        configurarListeners();
        actualizarListaResiduos();
    }

    private void inicializarVistas() {
        lvResiduos = findViewById(R.id.lvResiduos);
        spOrdenamiento = findViewById(R.id.spOrdenamiento);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnIterarAdelante = findViewById(R.id.btnIterarAdelante);
        btnIterarAtras = findViewById(R.id.btnIterarAtras);
        btnVolver = findViewById(R.id.btnVolver);
        tvInfo = findViewById(R.id.tvInfo);
    }

    private void configurarSpinner() {
        String[] opciones = {"Peso", "Tipo", "Prioridad Ambiental", "Zona", "Fecha"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            opciones
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrdenamiento.setAdapter(adapter);
    }

    private void configurarListeners() {
        btnActualizar.setOnClickListener(v -> actualizarListaResiduos());
        btnIterarAdelante.setOnClickListener(v -> iterarAdelante());
        btnIterarAtras.setOnClickListener(v -> iterarAtras());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void actualizarListaResiduos() {
        if (sistema.getResiduos().estaVacia()) {
            tvInfo.setText("No hay residuos registrados");
            lvResiduos.setAdapter(null);
            return;
        }

        String criterio = (String) spOrdenamiento.getSelectedItem();
        List<Residuo> listaOrdenada;

        switch (criterio) {
            case "Peso":
                listaOrdenada = sistema.ordenarResiduos(new Comparadores.ComparadorPorPeso());
                break;
            case "Tipo":
                listaOrdenada = sistema.ordenarResiduos(new Comparadores.ComparadorPorTipo());
                break;
            case "Prioridad Ambiental":
                listaOrdenada = sistema.ordenarResiduos(new Comparadores.ComparadorPorPrioridad());
                break;
            case "Zona":
                listaOrdenada = sistema.ordenarResiduos(new Comparadores.ComparadorPorZona());
                break;
            case "Fecha":
                listaOrdenada = sistema.ordenarResiduos(new Comparadores.ComparadorPorFecha());
                break;
            default:
                listaOrdenada = sistema.ordenarResiduos(new Comparadores.ComparadorPorPrioridad());
        }

        List<String> items = new ArrayList<>();
        for (Residuo residuo : listaOrdenada) {
            items.add(residuo.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            items
        );
        lvResiduos.setAdapter(adapter);

        tvInfo.setText(String.format("Total de residuos: %d | Ordenado por: %s",
            listaOrdenada.size(), criterio));
    }

    private void iterarAdelante() {
        if (sistema.getResiduos().estaVacia()) {
            tvInfo.setText("No hay residuos para iterar");
            return;
        }

        if (iteradorActual == null || !iterandoAdelante || !iteradorActual.hasNext()) {
            iteradorActual = sistema.getResiduos().iterator();
            posicionIterador = 0;
            iterandoAdelante = true;
        }

        if (iteradorActual.hasNext()) {
            Residuo residuo = iteradorActual.next();
            posicionIterador++;

            List<String> items = new ArrayList<>();
            items.add(residuo.toString());
            items.add("Fecha: " + residuo.getFechaRecoleccion());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
            );
            lvResiduos.setAdapter(adapter);

            tvInfo.setText(String.format("Iterando hacia adelante - Posición: %d/%d",
                posicionIterador, sistema.getResiduos().getTamanio()));
        } else {
            iteradorActual = null;
            tvInfo.setText("Fin de la lista. Presione nuevamente para reiniciar.");
        }
    }

    private void iterarAtras() {
        if (sistema.getResiduos().estaVacia()) {
            tvInfo.setText("No hay residuos para iterar");
            return;
        }

        if (iteradorActual == null || iterandoAdelante || !iteradorActual.hasNext()) {
            iteradorActual = sistema.getResiduos().iteradorReversa();
            posicionIterador = 0;
            iterandoAdelante = false;
        }

        if (iteradorActual.hasNext()) {
            Residuo residuo = iteradorActual.next();
            posicionIterador++;

            List<String> items = new ArrayList<>();
            items.add(residuo.toString());
            items.add("Fecha: " + residuo.getFechaRecoleccion());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
            );
            lvResiduos.setAdapter(adapter);

            tvInfo.setText(String.format("Iterando hacia atrás - Posición: %d/%d",
                posicionIterador, sistema.getResiduos().getTamanio()));
        } else {
            iteradorActual = null;
            tvInfo.setText("Fin de la lista. Presione nuevamente para reiniciar.");
        }
    }
}
