package me.valour.irisexchange.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class RegisterFragment extends Fragment {

    public RegisterListener regListener;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button button = (Button) view.findViewById(R.id.reg_go);
        button.setOnClickListener(regListener);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        regListener = new RegisterListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public class RegisterListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("email","s@b.com");
            requestBody.addProperty("username","sunny");
            requestBody.addProperty("password", "bite");
            String url = getString(R.string.base_url)+"/register";
            Log.d("point",url);
            Ion.with(view.getContext())
                    .load(url)
                    .setJsonObjectBody(requestBody)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject jsonObject) {
                            if(jsonObject==null){
                                Log.d("point", "error");
                                return;
                            }
                            String token = jsonObject.get("token").getAsString();
                            Log.d("val","token="+token);
                        }
                    });
        }
    }

}
