package singtel.irshadillias.language.interview.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Locale;

import singtel.irshadillias.language.interview.R;
import singtel.irshadillias.language.interview.databinding.FragmentHomeBinding;
import singtel.irshadillias.language.interview.utilities.BaseUtils;
import singtel.irshadillias.language.interview.utilities.LocaleManager;
import singtel.irshadillias.language.interview.viewmodel.HomeViewModel;
import singtel.irshadillias.language.interview.views.base.BaseFragment;

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
        ArrayAdapter mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.language_option));
        dataBinding.languages.setAdapter(mAdapter);

        dataBinding.languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dataBinding.chnagelang.setOnClickListener(v->{

            switch (dataBinding.languages.getSelectedItemPosition()){
                case 0 : setNewLocale(LocaleManager.LANGUAGE_ENGLISH, false); break;
                case 1 : setNewLocale(LocaleManager.LANGUAGE_INDONESIA, false); break;
                case 2 : setNewLocale(LocaleManager.LANGUAGE_SPANISH, false); break;

            }
        });


    }

    private void populatedefaultVal(Locale locale){
        int postion = 0;
        switch (locale.getLanguage()){
            case LocaleManager.LANGUAGE_INDONESIA : postion = 1;break;
            case LocaleManager.LANGUAGE_SPANISH : postion = 2;break;
        }
        dataBinding.languages.setSelection(postion);
    }

    @Override
    public void onResume() {
        super.onResume();
        populatedefaultVal(LocaleManager.getLocale(getActivity().getApplication().getResources()));
    }
}

