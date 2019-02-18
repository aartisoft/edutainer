package com.edutainer.in.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edutainer.in.Activity.SubCategoryActivity;
import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.CategoryModel;
import com.edutainer.in.QuizApplication;
import com.edutainer.in.R;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.ExpandableHeightGridView;

import java.util.List;

public class HomeFragment extends BaseFragment implements SingleAdapter.ReturnView {

    private ExpandableHeightGridView gridview;
    private TextView name;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initializeView(View view) {
        getActivity().setTitle("");
        gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);
        name=(TextView) view.findViewById(R.id.name);

        name.setText("Hi, "+AppPref.getInstance().getUSERNAME());

        gridview.setAdapter(new SingleAdapter(getActivity(), R.layout.layout_category_single_item, QuizApplication.subcategoryModelArrayList, this, 0));
        gridview.setExpanded(true);

        
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        TextView category_name = (TextView) view.findViewById(R.id.category_name);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        final CategoryModel categoryModel = QuizApplication.subcategoryModelArrayList.get(position);
        category_name.setText(categoryModel.getCat_name());
//        if (categoryModel.getImageurl().equalsIgnoreCase("")) {
//            image.setVisibility(View.GONE);
//        } else {
//
//            image.setVisibility(View.VISIBLE);
//        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog(categoryModel.getInstructions(), categoryModel.getId(),categoryModel.getActive());
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("c_id", categoryModel.getId());
                intent.putExtra("name",categoryModel.getCat_name());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
            }
        });
    }


    private void showDialog(String data, final String cat_id, String status) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Info");
        adb.setMessage(data);
        if (status.equalsIgnoreCase("true")) {
            adb.setPositiveButton("Get Started", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity()
                            , SubCategoryActivity.class);
                    intent.putExtra("cat_id", cat_id);
                    startActivity(intent);
                }
            });
        } else {
            adb.setPositiveButton("OK", null);
        }
        adb.show();
    }
}
