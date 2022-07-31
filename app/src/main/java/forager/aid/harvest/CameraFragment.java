package forager.aid.harvest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {



    private static final String TAG = "Is a camera";
    private final Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    private static final int MAKE_CHECK_FOR_PICTURES = 402;
    private static final int REQUEST_GET_PLANT_IMAGE = 499;
    private ImageButton cameraButton;
    private ImageView plantImgView;

    public CameraFragment()
    {
        super();
    }

    public static Fragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_camera, null);
        cameraButton = (ImageButton) v.findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED)
                {
                    Log.d(TAG,"Is printed");
                    startActivityForResult(pictureIntent, REQUEST_GET_PLANT_IMAGE);
                }
                else
                {
                    requestPermissions(
                            new String[] { Manifest.permission.CAMERA },
                            MAKE_CHECK_FOR_PICTURES);
                }

            }
        });
        plantImgView = (ImageView) v.findViewById(R.id.plantDisplay);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_GET_PLANT_IMAGE:
                if (resultCode == Activity.RESULT_OK)
                {
                    Bundle extras = data.getExtras();
                    Bitmap image = (Bitmap) extras.get("data");
                    plantImgView.setImageBitmap(image);
                }
            case MAKE_CHECK_FOR_PICTURES:
                if (resultCode == Activity.RESULT_OK)
                {
                    startActivityForResult(pictureIntent, REQUEST_GET_PLANT_IMAGE);
                }
                else
                {
                    Toast.makeText(getContext(), R.string.askToEnable, Toast.LENGTH_SHORT);
                }
        }
    }
}