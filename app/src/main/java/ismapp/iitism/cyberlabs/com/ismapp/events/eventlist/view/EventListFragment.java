package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListModel;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.Events;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.presenter.EventListPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.presenter.EventListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.provider.EventListProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;

public class EventListFragment extends Fragment implements EventListFragmentInterface {
    ProgressDialog progressDialog;
    private SharedPrefs sharedPrefs;
    private RecyclerView recyclerView;
    private List<EventListModel> eventListModelList;
    private List<EventListModel> eventListModelCompleteList=new ArrayList<>();
    private EventListAdapter eventListAdapter;
    private EventListPresenterInterface eventListPresenterInterface;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.CalendarView)
    MaterialCalendarView materialCalendarView;
    List<CalendarDay> calendarDayList;
    private CalendarDay calendarDay;

    /*CLUBLIST LAYOUT AND ITEM LAYOUT HAS BEEN REUSED*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_events,container,false);
        ButterKnife.bind(this, view);
        Calendar calendar=Calendar.getInstance();

        calendarDay=CalendarDay.from(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE));
        materialCalendarView.state().edit().setMinimumDate(calendarDay)
                .commit();
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_eventlist);
        sharedPrefs = new SharedPrefs(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(eventListModelList==null)
        {
            eventListPresenterInterface = new EventListPresenterImp(EventListFragment.this,new EventListProviderImp());
            eventListPresenterInterface.reqEventList(sharedPrefs.getAccessToken());}
        else
        {
            showProgressBar(false);
            eventListAdapter = new EventListAdapter(eventListModelList,getContext(),getActivity());
            recyclerView.setAdapter(eventListAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) Objects.requireNonNull(getActivity())).addTitletoBar("Events");
        ((MainActivity)getActivity()).addActionBar();
    }

    @Override
    public void getList(List<EventListModel> eventListModels) {

        this.eventListModelList = eventListModels;

        eventListAdapter = new EventListAdapter(eventListModelList,getContext(),getActivity());
        recyclerView.setAdapter(eventListAdapter);
       for(EventListModel e:eventListModels) {
           eventListModelCompleteList.add(e);
           int y = Integer.parseInt(e.getEvent_start_date().substring(0, 4));
           int m = Integer.parseInt(e.getEvent_start_date().substring(5, 7));
           int d = Integer.parseInt(e.getEvent_start_date().substring(8, 10));
           materialCalendarView.addDecorator(new Events(Color.rgb(128, 0, 128), Collections.singleton(CalendarDay.from(y, m, d))));
           materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
               @Override
               public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                  if(b)
                   {    eventListModelList.clear();
                    ArrayList<EventListModel> eventListModels1=new ArrayList<>();
                    for(EventListModel e:eventListModelCompleteList)
                    {
                        int y = Integer.parseInt(e.getEvent_start_date().substring(0, 4));
                        int m = Integer.parseInt(e.getEvent_start_date().substring(5, 7));
                        int d = Integer.parseInt(e.getEvent_start_date().substring(8, 10));
                        if(y>=calendarDay.getYear())
                            if (m>=calendarDay.getMonth())
                                if (d>=calendarDay.getDay()){eventListModels1.add(e);}
                    }
                   //Log.e("aman", "onDateSelected: "+eventListModels1.size() );
                    eventListModelList.addAll(eventListModels1);
                    eventListAdapter.notifyDataSetChanged();}


               }
           });

       }
       }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(), message);
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }
}
