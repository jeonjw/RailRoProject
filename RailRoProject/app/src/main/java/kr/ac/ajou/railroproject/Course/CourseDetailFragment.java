package kr.ac.ajou.railroproject.Course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.ac.ajou.railroproject.GlideApp;
import kr.ac.ajou.railroproject.R;

/**
 * Created by admin on 2017-11-06.
 */

public class CourseDetailFragment extends Fragment {

    private TextView tvCourseDetailTitle;
    private TextView tvCourseDetailLikeCount;
    private TextView tvCourseDetailCommentCount;
    private TextView tvCourseDetailHitCount;
    private RecyclerView courseRecyclerView;
    private CircleImageView profileImgview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_detail,container,false);

        Course curCourse = getArguments().getParcelable("COURSE");

        tvCourseDetailTitle =view.findViewById(R.id.tv_course_detail_title);
        tvCourseDetailLikeCount =view.findViewById(R.id.tv_course_detail_like_count);
        tvCourseDetailCommentCount =view.findViewById(R.id.tv_course_detail_comment_count);
        tvCourseDetailHitCount =view.findViewById(R.id.tv_course_detail_hit_count);
        profileImgview = view.findViewById(R.id.iv_course_detail_profile);
        courseRecyclerView = view.findViewById(R.id.course_detail_recycler_view);

        tvCourseDetailTitle.setText(""+curCourse.getTitle());
        tvCourseDetailLikeCount.setText(""+curCourse.getLikeCount());
        tvCourseDetailCommentCount.setText(""+curCourse.getCommentCount());
        tvCourseDetailHitCount.setText(""+(curCourse.getHitCount()+1));
        GlideApp.with(profileImgview).load(curCourse.getProfileImageURL()).fitCenter().into(profileImgview);

        CourseDetailAdapter courseDetailAdapter = new CourseDetailAdapter(curCourse.getStationList(), curCourse);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        courseRecyclerView.setLayoutManager(layoutManager);
        courseRecyclerView.setAdapter(courseDetailAdapter);

        return view;
    }

    public static CourseDetailFragment newInstance(Course course) {
        CourseDetailFragment courseDetailFragment = new CourseDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("COURSE",course);

        courseDetailFragment.setArguments(args);

        return courseDetailFragment;
    }
}
