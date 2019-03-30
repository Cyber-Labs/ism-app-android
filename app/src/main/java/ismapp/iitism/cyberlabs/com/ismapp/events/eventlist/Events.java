package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class Events implements DayViewDecorator {

    private final int color;
    private final HashSet<CalendarDay> dates;

    public Events(int color, Collection<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

   

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(10, color));
    }


}
