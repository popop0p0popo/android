package yi.cui.shenmidepangzi.pangnote.gui;

import java.util.ArrayList;
import java.util.List;

import yi.cui.shenmidepangzi.pangnote.R;
import yi.cui.shenmidepangzi.pangnote.object.Note;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CardListAdapter extends ArrayAdapter<Note> {
	private List<Note> notes = new ArrayList<Note>();
	
	static class CardViewHolder {
		TextView time;
		TextView content;
	}
	
	public CardListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
	
	@Override
    public void add(Note note) {
        notes.add(note);
        super.add(note);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int index) {
        return notes.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.time = (TextView) row.findViewById(R.id.line1);
            viewHolder.content = (TextView) row.findViewById(R.id.line2);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        Note note = getItem(position);
        viewHolder.time.setText(note.getTime());
        viewHolder.content.setText(note.getContent());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
