package ec.com.ecotrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import ec.com.ecotrackapp.controller.SistemaEcoTrack;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SistemaEcoTrack sistema;
    private TextView tvEstadisticasRapidas;
    private CardView cardRegistro, cardResiduos, cardVehiculos, cardCentro, cardEstadisticas, cardZonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar sistema
        sistema = SistemaEcoTrack.getInstance(this);

        inicializarVistas();
        configurarListeners();
        cargarDatosDePrueba();
        actualizarEstadisticasRapidas();
    }

    private void inicializarVistas() {
        tvEstadisticasRapidas = findViewById(R.id.tvEstadisticasRapidas);
        cardRegistro = findViewById(R.id.cardRegistro);
        cardResiduos = findViewById(R.id.cardResiduos);
        cardVehiculos = findViewById(R.id.cardVehiculos);
        cardCentro = findViewById(R.id.cardCentro);
        cardEstadisticas = findViewById(R.id.cardEstadisticas);
        cardZonas = findViewById(R.id.cardZonas);
    }

    private void configurarListeners() {
        cardRegistro.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegistroResiduoActivity.class));
        });

        cardResiduos.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ResiduosActivity.class));
        });

        cardVehiculos.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, VehiculosActivity.class));
        });

        cardCentro.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CentroReciclajeActivity.class));
        });

        cardEstadisticas.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EstadisticasActivity.class));
        });

        cardZonas.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ZonasActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarEstadisticasRapidas();
    }

    private void actualizarEstadisticasRapidas() {
        int totalResiduos = sistema.getResiduos().getTamanio();
        int residuosEnCentro = sistema.getCentroReciclaje().getTamanio();
        int vehiculosDisponibles = sistema.getVehiculosDisponibles().getTamanio();
        int zonasCriticas = sistema.obtenerZonasCriticas().size();

        String estadisticas = String.format(Locale.getDefault(),
            "📊 Resumen:\n" +
            "Residuos: %d | Centro: %d | Vehículos: %d | Zonas Críticas: %d",
            totalResiduos, residuosEnCentro, vehiculosDisponibles, zonasCriticas
        );

        tvEstadisticasRapidas.setText(estadisticas);
    }

    private void cargarDatosDePrueba() {
        if (sistema.getResiduos().getTamanio() == 0) {
            // Agregar datos de prueba solo la primera vez
            sistema.registrarVehiculo("ABC-123", "Norte", 1000, 9);
            sistema.registrarVehiculo("XYZ-789", "Sur", 800, 7);
            sistema.registrarVehiculo("DEF-456", "Centro", 1200, 8);
        }
    }
}
