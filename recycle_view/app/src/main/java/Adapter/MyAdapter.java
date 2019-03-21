package Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meterstoinches.recycle_view.DetailsActivity;
import com.example.meterstoinches.recycle_view.MainActivity;
import com.example.meterstoinches.recycle_view.R;

import java.util.List;

import model.Listitem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {

    private  Context context;
    private List<Listitem> listitems;

    public  MyAdapter (Context context, List listitems){
            this.context = context;
            this.listitems=listitems;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.line_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
                Listitem l = listitems.get(i);

                viewHolder.name.setText(l.getName());
                viewHolder.Description.setText(l.getDescription());
                viewHolder.Rating.setText(l.getRating());
                //viewHolder.image.setImageDrawable(R.);
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView Description;
        public TextView Rating;
        //public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=(TextView) itemView.findViewById(R.id.title);
            Description=(TextView) itemView.findViewById(R.id.description);
            Rating=(TextView) itemView.findViewById(R.id.rating);
            //image =(ImageView) itemView.findViewById(R.id.D_imageView);

        }

        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
            Listitem item = listitems.get(postion);
            Intent intent = new Intent(context,DetailsActivity.class);
            intent.putExtra("Name", item.getName());
            intent.putExtra("Description", item.getDescription());
            intent.putExtra("Rating" , item.getRating());

            context.startActivity(intent);
            Toast.makeText(context,item.getName(),Toast.LENGTH_LONG).show();

        }
    }
}
