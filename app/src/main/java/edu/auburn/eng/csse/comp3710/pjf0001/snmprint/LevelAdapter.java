package edu.auburn.eng.csse.comp3710.pjf0001.snmprint;

    import android.app.Activity;
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;

    import java.util.ArrayList;

public class LevelAdapter extends ArrayAdapter<Printer> {

    static Context context;
    static int layoutResourceId;
    ArrayList<Printer> data = null;

    public LevelAdapter(Context context, int layoutResourceId, ArrayList<Printer> data) {
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
        PrinterHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new PrinterHolder();

            holder.txtTitle = (TextView)row.findViewById(R.id.txtName);
            holder.txtTitle2 = (TextView)row.findViewById(R.id.txtIP);
            holder.txtTitle3 = (TextView)row.findViewById(R.id.txtID);

            row.setTag(holder);
        }
        else{
            holder = (PrinterHolder)row.getTag();
        }

        Printer p = data.get(position);
        holder.txtTitle.setText(p.getPrinterName());
        holder.txtTitle2.setText("131.204.116." + p.getIP());
        holder.txtTitle3.setText(String.valueOf(p.getID()));
        return row;
    }

    static class PrinterHolder{
        TextView txtTitle;
        TextView txtTitle2;
        TextView txtTitle3;
    }

}