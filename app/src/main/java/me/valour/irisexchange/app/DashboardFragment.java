package me.valour.irisexchange.app;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import nouns.UserCard;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DashboardFragment extends Fragment {

    GridView grid;
    CardGridAdapter adapter;

    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();

        return fragment;
    }
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        grid = (GridView)view.findViewById(R.id.dashboardGrid);
        adapter = new CardGridAdapter(view.getContext());
        grid.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Ion.getDefault(this.getView().getContext()).configure().setLogging("ion", Log.DEBUG);
        if(savedInstanceState==null){
            getUserCardsList("5654313976201216");
        }
    }

    public void getUserCardsList(final String deck_id){
        String url = getString(R.string.base_url)+"/decks/"+deck_id+"/cards";
        String userToken = ((MainActivity)getActivity()).getToken();
        if(userToken!=null){
            url += "?token="+userToken;
        }
        Log.d("url",url);
        Ion.with(this).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>(){
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                if(result==null || !result.has("cards")){
                    return;
                }
                JsonArray cards = result.get("cards").getAsJsonArray();
                for(int i=0; i<cards.size(); i++){
                    JsonObject userCard = cards.get(i).getAsJsonObject();
                     adapter.addUserCard(new UserCard(userCard,deck_id));
                }
                if(cards.size()>0){
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }


}
