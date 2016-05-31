package my3dfragment.my3dfragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/5/5.
 */
public class Fragment_First extends Fragment implements View.OnClickListener {

    private View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first,null);
        root.findViewById(R.id.btn_first).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        ((MainActivity)getActivity()).applyRotation(true, new Fragment_Second(), 0, 90);
    }
}
