package ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.news.createandeditnews.view.CreateNewsFragment;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.model.News;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.presenter.NewsListPresenterImplementation;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.presenter.NewsListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.news.feedandclubfeed.provider.NewsListProviderImplementation;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    private List<News> newsList;
    private final Context context;
    private final FragmentActivity fragmentActivity;
    private final boolean is_admin;
    private final static int maxHeight = 450;
    private  NewsListInterface newsListInterface;
    private final SharedPrefs sharedPrefs;

    NewsListAdapter(Context context, FragmentActivity fragmentActivity, boolean is_admin,NewsListInterface newsListInterface) {
        this.context = context;
        this.fragmentActivity = fragmentActivity;
        this.is_admin = is_admin;
        this.newsListInterface = newsListInterface;
        this.sharedPrefs=new SharedPrefs(context);
    }

    void setData(List<News> newsList){
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_feed,viewGroup,false);
        return new NewsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder newsListViewHolder, int i) {
        News news = newsList.get(i);

        Picasso.get().load(news.getNews_pic_url()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                float targetWidth = newsListViewHolder.iv_newsListImage.getWidth();
                float ratio = (float) bitmap.getHeight()/(float) bitmap.getWidth();
                float heightFloat = ((float) targetWidth) * ratio;
                final android.view.ViewGroup.MarginLayoutParams layoutParams =
                        (ViewGroup.MarginLayoutParams) newsListViewHolder.iv_newsListImage.getLayoutParams();
                layoutParams.height = (int) heightFloat;
                newsListViewHolder.iv_newsListImage.setLayoutParams(layoutParams);
                newsListViewHolder.iv_newsListImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        newsListViewHolder.tv_newsList_shortDescription.setText(news.getDescription());
        newsListViewHolder.tv_newsList_date_time.setText(news.getCreated_date());
        newsListViewHolder.tv_newsList_clubName.setText(news.getClub_name());
//        if ( newsListViewHolder.iv_newsListImage.getHeight() > maxHeight)
//            newsListViewHolder.iv_newsListImage.getLayoutParams().height = maxHeight;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsListViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_newsList_clubName;
        final TextView tv_newsList_date_time;
        final TextView tv_newsList_shortDescription;
      final ImageView iv_newsListImage;
      final CardView NewsCard;
      ImageView menu;
       NewsListViewHolder(@NonNull View itemView) {
           super(itemView);
           tv_newsList_clubName = itemView.findViewById(R.id.tv_newsList_club_name);
           tv_newsList_date_time = itemView.findViewById(R.id.newsList_date);
           tv_newsList_shortDescription = itemView.findViewById(R.id.tv_news_list_short_desc);
           iv_newsListImage = itemView.findViewById(R.id.newsList_image);
           NewsCard = itemView.findViewById(R.id.card_view_news);
           if (is_admin)
           {
               menu=itemView.findViewById(R.id.iv_newsList_menu);
               menu.setVisibility(View.VISIBLE);
               PopupMenu popupMenu=new PopupMenu(fragmentActivity,menu);
               popupMenu.getMenuInflater().inflate(R.menu.event_menu,popupMenu.getMenu());
               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       if(item.getItemId()==R.id.event_edit){
                           Bundle bundle=new Bundle();
                           bundle.putString("description",newsList.get(getAdapterPosition()).getDescription());
                           bundle.putString("news_url",newsList.get(getAdapterPosition()).getNews_pic_url());
                           bundle.putInt("news_id",newsList.get(getAdapterPosition()).getNews_id());
                           CreateNewsFragment createNewsFragment=new CreateNewsFragment();
                           createNewsFragment.setArguments(bundle);
                           ((MainActivity)fragmentActivity).addFragment(createNewsFragment );}
                      else {
                           AlertDialog alertDialog = new AlertDialog.Builder(context)
                                   .setTitle("Are you sure you want to remove this Feed?")
                                   .setCancelable(false)
                                   .setPositiveButton("Remove", (dialog, which) -> {
                                       NewsListPresenterInterface newsListPresenterInterface = new NewsListPresenterImplementation(newsListInterface, new NewsListProviderImplementation());
                                       newsListPresenterInterface.getRemoveEventResponse(sharedPrefs.getAccessToken(),newsList.get(getAdapterPosition()).getNews_id());
                                       newsList.remove(getAdapterPosition());
                                       notifyDataSetChanged();

                                   })
                                   .setNegativeButton("Cancel", (dialog, which) -> {


                                   })

                                   .create();
                           alertDialog.show();
                       }

                       return true;
                   }
               });
               menu.setOnClickListener(v -> popupMenu.show());

           }

       }
   }
}
