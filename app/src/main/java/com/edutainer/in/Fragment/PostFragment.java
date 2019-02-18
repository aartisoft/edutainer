package com.edutainer.in.Fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import com.edutainer.in.R;

public class PostFragment extends BaseFragment {
    private WebView desc;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_post;
    }

    @Override
    public void initializeView(View view) {
        desc = (WebView) view.findViewById(R.id.desc);
        Bundle save = getArguments();
        if (save != null) {
            int pos = save.getInt("pos");
//            PostModel postModel = SelectionActivity.postModelArrayList.get(pos);
//            desc.loadData(postModel.getDescription(),"text/html", "utf-8");
        }
    }
}
