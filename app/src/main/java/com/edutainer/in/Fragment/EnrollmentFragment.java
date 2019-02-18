package com.edutainer.in.Fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.edutainer.in.Activity.SubCategoryActivity;
import com.edutainer.in.Adapter.RecyclerAdapter;
import com.edutainer.in.Model.EnrollmentModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EnrollmentFragment extends BaseFragment implements RecyclerAdapter.ReturnView, ApiResponse {

    private RecyclerView recycle_view;
    private ArrayList<EnrollmentModel> enrollmentModelArrayList = new ArrayList();
    private LinearLayout no_data;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_enrollment;
    }

    @Override
    public void initializeView(View view) {
        recycle_view = (RecyclerView) view.findViewById(R.id.recyclerView);
        no_data=(LinearLayout) view.findViewById(R.id.no_data);
        addDummyData();


    }


    private void addDummyData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id=").append(AppPref.getInstance().getUserId());

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(getActivity(), AppUrl.ENROLLMENT_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }


    @Override
    public void returnView(ArrayList objects, int position, View view) {
        final EnrollmentModel enrollmentModel = enrollmentModelArrayList.get(position);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView desc = (TextView) view.findViewById(R.id.desc);
        ImageView imageView=(ImageView) view.findViewById(R.id.image);
        title.setText(enrollmentModel.getTitle());
        image(imageView,enrollmentModel.getImg());
        desc.setText(enrollmentModel.getDesc());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("name", enrollmentModel.getTitle());
                intent.putExtra("c_id", enrollmentModel.getId()+"");
                intent.putExtra("mode","unlocked");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
            }
        });
    }

    private void image(ImageView textView,String name)
    {
        String path=AppUrl.IMAGE_URL+name;
        Picasso.with(getActivity()).load(path).into(textView);
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONArray jsonArray = new JSONArray(responseData);

            if(jsonArray.length()>0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int id = jsonObject.getInt("course_id");
                    String course_name = jsonObject.getString("course_name");
                    String img=jsonObject.getString("web_img");


                    enrollmentModelArrayList.add(new EnrollmentModel(id, course_name, "",img));

                }

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recycle_view.setLayoutManager(mLayoutManager);
                recycle_view.setItemAnimator(new DefaultItemAnimator());
                recycle_view.setAdapter(new RecyclerAdapter(enrollmentModelArrayList, R.layout.layout_enrollmentfragment_singleitem, this));
                recycle_view.setVisibility(View.VISIBLE);
                no_data.setVisibility(View.GONE);
            }
            else
            {
                no_data.setVisibility(View.VISIBLE);
                recycle_view.setVisibility(View.GONE);
            }
        } catch (Exception e) {

            no_data.setVisibility(View.VISIBLE);
            recycle_view.setVisibility(View.GONE);
        }
    }
}
