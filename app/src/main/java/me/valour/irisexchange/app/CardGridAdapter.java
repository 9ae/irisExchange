package me.valour.irisexchange.app;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import nouns.UserCard;

/**
 * Created by alice on 8/6/14.
 */
public class CardGridAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<UserCard> userCards;

    public CardGridAdapter(Context c){
        mContext = c;
        userCards = new ArrayList<UserCard>();
    }

    public void addUserCard(UserCard uc){
        userCards.add(uc);
    }

    public int getCount(){
        return userCards.size();
    }

    public UserCard getItem(int position){
        return userCards.get(position);
    }

    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        CardHolder holder = new CardHolder();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_small_card, null);

            holder.img = (ImageView) v.findViewById(R.id.smallCardImage);
            holder.number = (TextView) v.findViewById(R.id.smallCardNumber);
            holder.name =  (TextView) v.findViewById(R.id.smallCardName);
            holder.self = v;

            v.setTag(holder);
        }
        else {
            holder = (CardHolder) v.getTag();
        }
        //TODO: set values, image url, card no and name, and deck background color
        UserCard uc = userCards.get(position);
        holder.number.setText(Integer.toString(uc.number));
        holder.name.setText(uc.name);
        return v;
    }

    private static class CardHolder{
        public ImageView img;
        public TextView number;
        public TextView name;
        public View self;
    }

}
