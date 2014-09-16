package me.valour.irisexchange.app;



import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;


public class LoginFragment extends UserFragment {


    private EditText usernameField;
    private EditText passwordField;
    protected LoginListener loginListener;


    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        usernameField = (EditText) view.findViewById(R.id.login_username);
        passwordField = (EditText) view.findViewById(R.id.login_password);

        Button button = (Button) view.findViewById(R.id.login_go);
        button.setOnClickListener(loginListener);

        TextView toggle = (TextView) view.findViewById(R.id.to_register_fragment);
        toggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                toRegisterFragment();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        loginListener = new LoginListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void toRegisterFragment(){
            (  (MainActivity)getActivity() ).toggleLoginRegister(false);
    }

    protected class LoginListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("username",username);
            requestBody.addProperty("password", password);

            String url = getString(R.string.base_url)+"/login";
            Log.d("point", url);
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
                            gotToken(token);
                        }
                   });
        }
    }


}
