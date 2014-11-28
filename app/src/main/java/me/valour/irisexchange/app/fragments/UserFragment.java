package me.valour.irisexchange.app.fragments;

import android.app.Fragment;

import me.valour.irisexchange.app.activities.MainActivity;

/**
 * Created by alice on 9/16/14.
 */
public class UserFragment extends Fragment {

   public UserFragment(){

    }

    protected void gotToken(String token){
        (  (MainActivity)getActivity() ).setToken(token);
        (  (MainActivity)getActivity() ).showDashboard();
    }

}
