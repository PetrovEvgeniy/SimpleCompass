package de.thu.simplecompass;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Sensor orientationSensor;
    private SensorManager sensorManager;
    private ImageView compassImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        compassImage = (ImageView) findViewById(R.id.compass_image);

    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this,orientationSensor,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values.clone();

        compassImage.setRotation(-values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //When phone battery is low...
        //optimize sensor




    }
}