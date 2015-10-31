package miw.dasm.inigo.genderguesser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecentFirstNameAdapter extends ArrayAdapter<FirstName> {

    public RecentFirstNameAdapter(Context context, int resource, ArrayList<FirstName> firstNames) {
        super(context, resource, firstNames);
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recent_firstnames_adapter, parent, false);
        }
        FirstName firstName = getItem(position);
        TextView nameText = (TextView) convertView.findViewById(R.id.name_text);
        nameText.setText(firstName.getName());
        TextView genderText = (TextView) convertView.findViewById(R.id.gender_text);
        genderText.setText(firstName.getGender());
        return convertView;
    }
}
