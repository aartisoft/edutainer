package com.edutainer.in.workplace.TopicQuestions;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.edutainer.in.Activity.DashBoardActivity;
import com.edutainer.in.R;
import com.edutainer.in.workplace.Model.QuestionModel;
import com.edutainer.in.workplace.TopicQuestions.Result.ResultActivity;

import java.util.ArrayList;

public class RecyclerAdapterQuestions extends RecyclerView.Adapter<RecyclerAdapterQuestions.ViewHolderQuestions> {
    private Context context;
    private ArrayList<QuestionModel> list;
    String topic_id;
    String course_id;

    public RecyclerAdapterQuestions(Context context, ArrayList<QuestionModel> list, String topic_id, String course_id) {
        this.context = context;
        this.list = list;
        this.topic_id = topic_id;
        this.course_id = course_id;
    }

    @NonNull
    @Override
    public ViewHolderQuestions onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_questions, viewGroup, false);
        return new ViewHolderQuestions(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderQuestions viewHolderQuestions, int i) {
        QuestionModel model = list.get(i);
        viewHolderQuestions.tv_question.setText(model.getQuestion());
        viewHolderQuestions.tv_option1.setText(model.getOption1());
        viewHolderQuestions.tv_option2.setText(model.getOption2());
        viewHolderQuestions.tv_option3.setText(model.getOption3());
        viewHolderQuestions.tv_option4.setText(model.getOption4());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderQuestions extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_question;
        private TextView tv_option1;
        private TextView tv_option2;
        private TextView tv_option3;
        private TextView tv_option4;
        private TextView tv_next;

        public ViewHolderQuestions(@NonNull View itemView) {
            super(itemView);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_option1 = itemView.findViewById(R.id.tv_option1);
            tv_option1.setOnClickListener(this);
            tv_option2 = itemView.findViewById(R.id.tv_option2);
            tv_option2.setOnClickListener(this);
            tv_option3 = itemView.findViewById(R.id.tv_option3);
            tv_option3.setOnClickListener(this);
            tv_option4 = itemView.findViewById(R.id.tv_option4);
            tv_option4.setOnClickListener(this);
            tv_next = itemView.findViewById(R.id.tv_next);
            tv_next.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_option1:
                    if (list.get(getAdapterPosition()).getCorrect_answer().equalsIgnoreCase("1")) {
                        list.get(getAdapterPosition()).setSelection("true");
                    } else
                        list.get(getAdapterPosition()).setSelection("false");
                    tv_option1.setTextColor(Color.WHITE);
                    tv_option3.setTextColor(ContextCompat.getColor(context, R.color.background_color));
                    tv_option2.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option4.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option1.setBackground(ContextCompat.getDrawable(context, R.drawable.background_round_green));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));

                    break;
                case R.id.tv_option2:
                    if (list.get(getAdapterPosition()).getCorrect_answer().equalsIgnoreCase("2")) {
                        list.get(getAdapterPosition()).setSelection("true");
                    } else
                        list.get(getAdapterPosition()).setSelection("false");
                    tv_option2.setTextColor(Color.WHITE);
                    tv_option1.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option3.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option4.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_green));
                    tv_option1.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));

                    break;
                case R.id.tv_option3:
                    if (list.get(getAdapterPosition()).getCorrect_answer().equalsIgnoreCase("3")) {
                        list.get(getAdapterPosition()).setSelection("true");
                    } else
                        list.get(getAdapterPosition()).setSelection("false");
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_green));
                    tv_option3.setTextColor(Color.WHITE);
                    tv_option1.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option2.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option4.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option1.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                
                    break;
                case R.id.tv_option4:
                    if (list.get(getAdapterPosition()).getCorrect_answer().equalsIgnoreCase("4")) {
                        list.get(getAdapterPosition()).setSelection("true");
                    } else
                        list.get(getAdapterPosition()).setSelection("false");
                    tv_option4.setTextColor(Color.WHITE);
                    tv_option1.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option2.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option3.setTextColor(ContextCompat.getColor(context,R.color.background_color));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_green));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                    tv_option1.setBackground(ContextCompat.getDrawable(context,R.drawable.background_rounded));
                
                    break;
                case R.id.tv_next:
                    String text_value = tv_next.getText().toString();
                    if (text_value.equalsIgnoreCase("next"))
                        ((QuestionsActivity)context).nextQuestion(getAdapterPosition());
                    else if (text_value.equalsIgnoreCase("finish"))
                        result();
                    else{
                        decision();
                    }
                    break;
            }
        }

        private void decision() {
            switch (list.get(getAdapterPosition()).getCorrect_answer()) {
                case "1":
                    tv_option1.setTextColor(Color.WHITE);
                    tv_option3.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option2.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option4.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option1.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_green));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    break;
                case "2":
                    tv_option2.setTextColor(Color.WHITE);
                    tv_option1.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option3.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option4.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option1.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_green));
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    break;
                case "3":
                    tv_option2.setTextColor(Color.WHITE);
                    tv_option1.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option3.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option4.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option1.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_green));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    break;
                case "4":
                    tv_option2.setTextColor(Color.WHITE);
                    tv_option1.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option3.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option4.setTextColor(ContextCompat.getColor(context,R.color.white));
                    tv_option1.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option2.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option3.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_red));
                    tv_option4.setBackground(ContextCompat.getDrawable(context,R.drawable.background_round_green));
                    break;
            }

            tv_option1.setEnabled(false);
            tv_option2.setEnabled(false);
            tv_option3.setEnabled(false);
            tv_option4.setEnabled(false);
            if (getAdapterPosition() == list.size()-1)
                tv_next.setText("Finish");
            else
                tv_next.setText("Next");
        }

        private void result() {
            Intent intent = new Intent(context, ResultActivity.class);
            int correct = 0;
            int incorrect = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSelection().equalsIgnoreCase("true")) {
                    correct += 1;
                } else
                    incorrect += 1;
            }
            intent.putExtra("CORRECT", correct);
            intent.putExtra("INCORRECT", incorrect);
            intent.putExtra("TOPIC_ID", topic_id);
            intent.putExtra("COURSE_ID", course_id);

            context.startActivity(intent);
            ((QuestionsActivity)context).finish();
        }
    }
}
