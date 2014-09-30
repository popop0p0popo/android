package yi.cui.shenmidepangzi.pangnote.gui;

import java.util.ArrayList;
import java.util.List;

import yi.cui.shenmidepangzi.pangnote.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DrawerListAdapter extends ArrayAdapter<String> {
	private List<String> items = new ArrayList<String>();
	
	public DrawerListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
	
	static class DrawerViewHolder {
		TextView content;
	}
	
	@Override
    public void add(String s) {
        items.add(s);
        super.add(s);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int index) {
        return items.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DrawerViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_drawer, parent, false);
            viewHolder = new DrawerViewHolder();
            viewHolder.content = (TextView) row.findViewById(R.id.drawer_line);
            row.setTag(viewHolder);
        } else {
            viewHolder = (DrawerViewHolder) row.getTag();
        }
        String s = getItem(position);
        viewHolder.content.setText(s);
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
