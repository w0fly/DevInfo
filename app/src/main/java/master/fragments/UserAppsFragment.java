package master.fragments;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.master.deviceinfo.R;
import com.master.deviceinfo.models.DeviceInfoModel;
import master.adapters.AppsListAdapter;
import master.utils.AppConst;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserAppsFragment extends BaseFragment {

    private AppsListAdapter adapter;
    @BindView(R.id.rv_apps_list)
    RecyclerView rv_apps_list;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apps, container, false);
        ButterKnife.bind(this, view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.dark_parrot_green_blue));
            window.setNavigationBarColor(getResources().getColor(R.color.dark_parrot_green_blue));

        }
        initUI();
        return view;
    }


    private void initUI() {
        adapter = new AppsListAdapter(getActivity(), AppConst.userAppsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_apps_list.setLayoutManager(mLayoutManager);
        rv_apps_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}
