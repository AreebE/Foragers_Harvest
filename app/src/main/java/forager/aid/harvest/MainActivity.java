package forager.aid.harvest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
    implements WarningFragment.WarningListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportFragmentManager().findFragmentById(R.id.fragView) == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragView, WarningFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void acceptedWarning() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragView, CameraFragment.newInstance())
                .commit();
    }
}