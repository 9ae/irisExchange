package me.valour.irisexchange.app;

import android.app.Fragment;

/**
 * Created by alice on 9/16/14.
 */
public class UserFragment extends Fragment {

   public UserFragment(){

    }

    protected void gotToken(String token){
        (  (MainActivity)getActivity() ).setToken(token);
    }
}