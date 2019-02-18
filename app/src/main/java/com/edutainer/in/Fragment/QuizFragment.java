package com.edutainer.in.Fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.edutainer.in.Activity.QuestionActivity;
import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.CategoryModel;
import com.edutainer.in.QuizApplication;
import com.edutainer.in.R;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends BaseFragment implements SingleAdapter.ReturnView {
    private GridView gridview;
    private ArrayList<CategoryModel> categoryModels = new ArrayList<>();

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_quiz;
    }

    @Override
    public void initializeView(View view) {
        gridview = (GridView) view.findViewById(R.id.gridview);

        addData();
        gridview.setAdapter(new SingleAdapter(getActivity(), R.layout.layout_category_single_item, categoryModels, this, 0));

    }

    private void addData() {
        for (byte b = 0; b < QuizApplication.categoryModelArrayList.size(); b++) {
            ArrayList<CategoryModel> categoryModelArrayList = QuizApplication.categoryModelArrayList.get(b).getCategoryModelArrayList();
            for (byte b1 = 0; b1 < categoryModelArrayList.size(); b1++) {
                if (!categoryModelArrayList.get(b1).getIsonline().equalsIgnoreCase("0")) {
                    categoryModels.add(categoryModelArrayList.get(b1));
                }
            }
        }
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        TextView category_name = (TextView) view.findViewById(R.id.category_name);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        final CategoryModel categoryModel = categoryModels.get(position);
        category_name.setText(categoryModel.getCat_name());
        if (categoryModel.getImageurl() != null && !categoryModel.getImageurl().equalsIgnoreCase(""))
            Picasso.with(getActivity()).load(categoryModel.getImageurl()).placeholder(R.drawable.logo).into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryModel.getIsend().equalsIgnoreCase("0")) {
                    toastMessage("Coming Soon");
                    return;
                }
                sendToActivity(QuestionActivity.class, new String[]{"c_id;" + categoryModel.getId(), "online;true"});
            }
        });
    }

}
