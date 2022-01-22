package com.example.physicswallahtaskone;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.physicswallahtaskone.databinding.FragmentListviewBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
public class ListviewFragment extends Fragment {

    private String TAG = ListviewFragment.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> contactList;

    FragmentListviewBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentListviewBinding.inflate(inflater, container, false);
        contactList = new ArrayList<>();
        new GetContacts().execute();

        return mBinding.getRoot();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "Facilities Data is  downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = "https://my-json-server.typicode.com/ricky1550/pariksha/db";
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray response = new JSONArray(jsonStr);
                    for (int i = 0; i < response.length(); i++) {
                        JSONArray ja_data = response.getJSONArray(i);
                        JSONObject c = response.getJSONObject(i);
                        String name = c.getString("name");
                        String icon = c.getString("icon");

                        HashMap<String, String> contact = new HashMap<>();

                        contact.put("icon", icon);
                        contact.put("name", name);
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(requireContext(), contactList,
                    R.layout.title_list, new String[]{"id", "title"},
                    new int[]{R.id.list_id, R.id.list_title});
            mBinding.jsonlistview.setAdapter(adapter);
        }
    }
}