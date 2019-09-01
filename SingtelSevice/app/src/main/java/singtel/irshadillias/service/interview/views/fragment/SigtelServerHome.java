package singtel.irshadillias.service.interview.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;

import singtel.irshadillias.service.interview.R;
import singtel.irshadillias.service.interview.databinding.FragmentHomeBinding;
import singtel.irshadillias.service.interview.viewmodel.HomeViewModel;
import singtel.irshadillias.service.interview.views.base.BaseFragment;

public class SigtelServerHome extends BaseFragment<HomeViewModel,
        FragmentHomeBinding> {



    public static SigtelServerHome newInstance() {
        Bundle args = new Bundle();
        SigtelServerHome fragment = new SigtelServerHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getJonReslt()
                .observe(this, listResource -> {
                    Gson gson = new Gson();
                    String json = gson.toJson(listResource);
                    Toast.makeText(getContext(),json,Toast.LENGTH_SHORT).show();
                });
        //viewModel.getConvertJson("irshad illias");
    }
}

