package com.landfilleforms.android.landfille_forms.ime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.landfilleforms.android.landfille_forms.R;
import com.landfilleforms.android.landfille_forms.model.ImeData;

import java.util.List;
import java.util.UUID;

//Done
public class ImeDataPagerActivity extends AppCompatActivity {
    private static final String EXTRA_IME_DATA_ID = "com.landfilleforms.android.landfille_forms.ime_data_id";

    private ViewPager mViewPager;
    private List<ImeData> mImeDataList;

    public static Intent newIntent(Context packageContext, UUID imeDataId) {
        Intent intent = new Intent (packageContext, ImeDataPagerActivity.class);
        intent.putExtra(EXTRA_IME_DATA_ID, imeDataId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ime_data_pager);

        UUID imeDataId = (UUID) getIntent().getSerializableExtra(EXTRA_IME_DATA_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_ime_data_pager_view_pager);

        mImeDataList = ImeForm.get(this).getImeDatas();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                ImeData imeData = mImeDataList.get(position);
                return ImeDataFragment.newInstance(imeData.getId());
            }

            @Override
            public int getCount() {
                return mImeDataList.size();
            }
        });

        for(int i = 0; i < mImeDataList.size(); i++) {
            if (mImeDataList.get(i).getId().equals(imeDataId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


}
