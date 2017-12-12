package kr.ac.ajou.railroproject.CourseComment;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import kr.ac.ajou.railroproject.R;

public class CourseCommentViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener {

    private final static String DATABASE_REF_NAME = "CourseComments";

    private TextView authorView;
    private TextView bodyView;
    private TextView dateView;
    private String commentKey;
    private String postKey;
    private CourseComment courseComment;

    public CourseCommentViewHolder(View itemView) {
        super(itemView);
        authorView = itemView.findViewById(R.id.comment_author);
        bodyView = itemView.findViewById(R.id.comment_body);
        dateView = itemView.findViewById(R.id.comment_date);
        itemView.setOnCreateContextMenuListener(this);

    }

    public void bindComment(CourseComment model, String postKey, String commentKey) {
        this.courseComment = model;
        authorView.setText(model.getAuthor());
        bodyView.setText(model.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd.", Locale.KOREA);
        dateView.setText(dateFormat.format(model.getTimeStamp()));
        this.commentKey = commentKey;
        this.postKey = postKey;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (!Objects.equals(courseComment.getUid(), FirebaseAuth.getInstance().getCurrentUser().getUid()))
            return;

        MenuItem delete = menu.add(Menu.NONE, 1, 1, "삭제");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == 1) {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child(DATABASE_REF_NAME).child(postKey).child(commentKey).removeValue();
                }

                return true;
            }
        });
    }


}