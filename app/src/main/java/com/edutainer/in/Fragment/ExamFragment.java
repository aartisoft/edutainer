package com.edutainer.in.Fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.edutainer.in.Activity.SelectionActivity;
import com.edutainer.in.Activity.SubCategoryActivity;
import com.edutainer.in.Activity.SubCategoryGridActivity;
import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.CategoryModel;
import com.edutainer.in.R;

import java.util.List;

public class ExamFragment extends BaseFragment implements SingleAdapter.ReturnView {

    private GridView gridview;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_exam;
    }

    @Override
    public void initializeView(View view) {
        gridview = (GridView) view.findViewById(R.id.gridview);

        gridview.setAdapter(new SingleAdapter(getActivity(), R.layout.layout_category_single_item, SubCategoryGridActivity.arrayList, this, 0));

    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        TextView category_name = (TextView) view.findViewById(R.id.category_name);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        final CategoryModel categoryModel = SubCategoryGridActivity.arrayList.get(position);
        category_name.setText(categoryModel.getCat_name());
        if (categoryModel.getImageurl() != null && !categoryModel.getImageurl().equalsIgnoreCase(""))
            Picasso.with(getActivity()).load(categoryModel.getImageurl()).placeholder(R.drawable.logo).into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision(categoryModel.getIsend(),categoryModel.getId(),categoryModel.getCat_name());
            }
        });
    }

    private void decision(String isend,String id, String name) {
        switch (isend) {
            case "0":
                toastMessage("Coming Soon");
                break;
            case "1":
                sendToActivity(SelectionActivity.class, new String[]{"c_id;" + id, "cat_name;" + name});
                break;
            case "2":
                sendToActivity(SubCategoryActivity.class, new String[]{"c_id;" + id, "cat_name;" + name});
                break;
            case "3":
                sendToActivity(SubCategoryGridActivity.class, new String[]{"c_id;" + id, "cat_name;" + name});
                break;
        }
    }
}
