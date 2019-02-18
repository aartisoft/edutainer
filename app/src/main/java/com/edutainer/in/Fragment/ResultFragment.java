package com.edutainer.in.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.ResultModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends BaseFragment implements SingleAdapter.ReturnView, ApiResponse {

    private ListView listview;
    private ArrayList<ResultModel> resultModelArrayList = new ArrayList();

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_result;
    }

    @Override
    public void initializeView(View view) {
        listview = (ListView) view.findViewById(R.id.listview);
        addData();
    }


    private void addData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(getActivity(), AppUrl.RESULT_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        ResultModel resultModel = (ResultModel) resultModelArrayList.get(position);
        TextView exam_title = (TextView) view.findViewById(R.id.exam_title);
        TextView score = (TextView) view.findViewById(R.id.score);
        TextView date = (TextView) view.findViewById(R.id.date);

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");

        exam_title.setText(resultModel.getTitle());
        score.setText("Score : "+resultModel.getScore());
        date.setText(resultModel.getDate());
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String trk_id = jsonObject.getString("trk_id");
                String category_name = jsonObject.getString("category_name");
                String number = jsonObject.getString("number");
                String trk_date = jsonObject.getString("trk_date");

                resultModelArrayList.add(new ResultModel(trk_id, category_name, number, trk_date));
            }
            listview.setAdapter(new SingleAdapter(getActivity(), R.layout.layout_result_single_item, resultModelArrayList, this, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
