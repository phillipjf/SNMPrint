package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

    import android.app.Activity;
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;

public class LevelAdapter extends ArrayAdapter<Printer> {

    static Context context;
    static int layoutResourceId;
    Printer data[] = null;

    public LevelAdapter(Context context, int layoutResourceId, Printer[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            //row.setMinimumHeight(200);
            holder = new WeatherHolder();
            // holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.txtTitle2 = (TextView)row.findViewById(R.id.txtTitle2);

            row.setTag(holder);
        }
        else{
            holder = (WeatherHolder)row.getTag();
        }

        Printer weather = data[position];
        holder.txtTitle.setText(weather.getPrinterName());
        //    holder.imgIcon.setImageResource(weather.icon);
        holder.txtTitle2.setText("131.204.116." + weather.getIP());
        return row;
    }

    static class WeatherHolder{
        //   ImageView imgIcon;
        TextView txtTitle;
        TextView txtTitle2;
        //    ImageView imgIcon2;
    }

}