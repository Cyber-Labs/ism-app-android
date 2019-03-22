package ismapp.iitism.cyberlabs.com.ismapp.news.newslist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.news.newsdetails.view.NewsDetailsImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.newslist.model.News;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    List<News> newsList;
    Context context;
    Fragment fragment;

    public NewsListAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    void setData(List<News> newsList){
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_feed,null);
        return new NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder newsListViewHolder, int i) {
        News news = newsList.get(i);
        Picasso.get().load(news.getNews_pic_url()).into(newsListViewHolder.iv_newsListImage);
        newsListViewHolder.tv_newsList_shortDescription.setText(news.getDescription());
        newsListViewHolder.tv_newsList_date_time.setText(news.getCreated_date());
        newsListViewHolder.tv_newsList_clubName.setText(news.getClub_name());
        newsListViewHolder.NewsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news = newsList.get(i);
                ((MainActivity)fragment.getActivity()).addFragment( NewsDetailsImplementation.newInstance(news.getNews_id()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsListViewHolder extends RecyclerView.ViewHolder{
      TextView tv_newsList_clubName,tv_newsList_date_time,tv_newsList_shortDescription;
      ImageView iv_newsListImage;
      CardView NewsCard;
       public NewsListViewHolder(@NonNull View itemView) {
           super(itemView);
           tv_newsList_clubName = (TextView)itemView.findViewById(R.id.tv_newsList_club_name);
           tv_newsList_date_time = (TextView)itemView.findViewById(R.id.newsList_date);
           tv_newsList_shortDescription = (TextView)itemView.findViewById(R.id.tv_news_list_short_desc);
           iv_newsListImage = (ImageView)itemView.findViewById(R.id.newsList_image);
           NewsCard = (CardView)itemView.findViewById(R.id.card_view_news);

       }
   }
}
