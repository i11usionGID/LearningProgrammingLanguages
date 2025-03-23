package com.example.learningprogramminglanguages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningprogramminglanguages.model.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> lessons = new ArrayList<>();
    private OnLessonClickListener onLessonClickListener;

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    public void setOnLessonClickListener(OnLessonClickListener onLessonClickListener) {
        this.onLessonClickListener = onLessonClickListener;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.lesson,
                parent,
                false
        );
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        if (lesson.isLock()) {
            holder.imageViewLock.setVisibility(View.VISIBLE);
            holder.imageViewComplete.setVisibility(View.INVISIBLE);
        } else {
            holder.imageViewLock.setVisibility(View.INVISIBLE);
            holder.imageViewComplete.setVisibility(View.VISIBLE);
        }
        holder.textViewLessonTitle.setText(lesson.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLessonClickListener != null) {
                    onLessonClickListener.onLessonClick(lesson);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public interface OnLessonClickListener {
        void onLessonClick(Lesson lesson);
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewLock;
        ImageView imageViewComplete;
        TextView textViewLessonTitle;
        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewLock = itemView.findViewById(R.id.imageViewLock);
            imageViewComplete = itemView.findViewById(R.id.imageViewComplete);
            textViewLessonTitle = itemView.findViewById(R.id.textViewLessonTitle);
        }
    }
}
