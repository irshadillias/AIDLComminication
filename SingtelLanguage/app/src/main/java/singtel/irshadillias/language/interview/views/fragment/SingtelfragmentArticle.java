package singtel.irshadillias.language.interview.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ice.restring.Restring;

import java.util.HashMap;
import java.util.Map;

import singtel.irshadillias.language.interview.R;
import singtel.irshadillias.language.interview.databinding.FragementArticleBinding;
import singtel.irshadillias.language.interview.viewmodel.HomeViewModel;
import singtel.irshadillias.language.interview.views.base.BaseFragment;

public class SingtelfragmentArticle extends BaseFragment<HomeViewModel,
        FragementArticleBinding> {

    public static SingtelfragmentArticle newInstance() {
        Bundle args = new Bundle();
        SingtelfragmentArticle fragment = new SingtelfragmentArticle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragement_article;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
