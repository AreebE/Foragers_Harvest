package forager.aid.harvest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarningFragment extends Fragment {

    public interface WarningListener
    {
        public void acceptedWarning();
    }

    private WarningListener warningListener;

    public WarningFragment() {
        // Required empty public constructor
    }


    public static WarningFragment newInstance() {
        WarningFragment fragment = new WarningFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.warningListener = (WarningListener) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_warning, container, false);
        Button button =  (Button) view.findViewById(R.id.confirm_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                warningListener.acceptedWarning();
            }
        });
        return view;
    }
}