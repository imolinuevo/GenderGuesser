package miw.dasm.inigo.genderguesser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GenderFirstNameAdapter extends ArrayAdapter<FirstName> {

    public GenderFirstNameAdapter(Context context, int resource, ArrayList<FirstName> firstNames) {
        super(context, resource, firstNames);
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gender_firstnames_adapter, parent, false);
        }
        FirstName firstName = getItem(position);
        TextView nameText = (TextView) convertView.findViewById(R.id.male_name_text);
        nameText.setText(firstName.getName());
        TextView descriptionText = (TextView) convertView.findViewById(R.id.male_description_text);
        descriptionText.setText(firstName.getGender());
        return convertView;
    }
}