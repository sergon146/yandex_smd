package com.sergon146.mobilization18.ui.fragments.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.core.utils.Const;
import com.sergon146.mobilization18.BuildConfig;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AboutFragment extends BaseMvpFragment<AboutPresenter>
        implements AboutView {

    @Inject
    @InjectPresenter
    AboutPresenter presenter;

    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.copyright)
    TextView copyright;

    private RotateAnimation rotate;

    public static AboutFragment getInstance() {
        return new AboutFragment();
    }

    @Override
    @ProvidePresenter
    protected AboutPresenter providePresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, root);
        setActionBarTitle(R.string.about_app);
        prepareView();
        return root;
    }

    private void prepareView() {
        version.setText(getString(R.string.about_version, BuildConfig.VERSION_NAME));
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        copyright.setText(currentYear == Const.APP_YEAR
                ? getString(R.string.copytight_this_year)
                : getString(R.string.copytight, currentYear));
    }

    @OnClick(R.id.logo)
    void onClick(View view) {
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2000);
        rotate.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotate);
    }

    @Override
    public String getLogName() {
        return "AboutFragment";
    }
}
