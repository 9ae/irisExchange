package me.valour.irisexchange.app;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import nouns.UserCard;
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
        adapter.addUserCard(new UserCard("","","","","red",1));
        adapter.addUserCard(new UserCard("","","","","blue",2));
        grid.setAdapter(adapter);

        return view;
    }

    private void getUserCardsList(){
        String url = getString(R.string.base_url)+"/cards";
        String userToken = ((MainActivity)getActivity()).getToken();
        if(userToken!=null){
            url += "?token="+userToken;
        }
        Ion.with(this).load(url).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray jsonElements) {
                //TODO: do something with array return result;
            }
        });
    }


}
