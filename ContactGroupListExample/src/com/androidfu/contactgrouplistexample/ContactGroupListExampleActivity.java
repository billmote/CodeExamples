package com.androidfu.contactgrouplistexample;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactGroupListExampleActivity extends ListActivity {
    private static final String TAG = "ContactGroupListExampleActivity";
    private List<ContactGroup> _groupList;
    private ContactGroupAdapter _adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "onCreate()");

        this._groupList = getContactGroupList();
        _adapter = new ContactGroupAdapter(this);
        setListAdapter(_adapter);
    }

    public ArrayList<ContactGroup> getContactGroupList() {
        Log.d(TAG, "getContactGroupList()");

        ArrayList<ContactGroup> groupList = new ArrayList<ContactGroup>();
        ContactGroup group = new ContactGroup();
        ContactGroupMember member = new ContactGroupMember();

        Log.d(TAG, "Creating a member.");
        member.name = "Test Member";
        member.mobilePhoneNumber = "(123) 456-7890";
        member.id = 1;

        Log.d(TAG, "Creating a group.");
        group.name = "One Group";
        group.id = 1;
        groupList.add(group);

        Log.d(TAG, "Returning our list.");
        return groupList;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick()");
        ContactGroup selectedGroup = new ContactGroup();
        selectedGroup = _groupList.get(position);
        Toast.makeText(getApplicationContext(), String.valueOf(selectedGroup.id), Toast.LENGTH_SHORT).show();
        // groupList.removeAll(groupList);
        _groupList.clear();
        _adapter.notifyDataSetChanged();

    }

    public class ContactGroupAdapter extends BaseAdapter {

        public ContactGroupAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return _groupList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                LayoutInflater vi = LayoutInflater.from(this.mContext);
                convertView = vi.inflate(R.layout.listitem, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                // Get ViewHolder back
                holder = (ViewHolder) convertView.getTag();
            }
            ContactGroup contactGroup = _groupList.get(position);
            if (contactGroup != null) {
                holder.name.setText(contactGroup.name);
            }
            return convertView;
        }

        private Context mContext;
    }

    public class ViewHolder {
        TextView name;
        
        ViewHolder(View v) {
                this.name = (TextView) v.findViewById(R.id.lv_topText);
        }
    }

    public class ContactGroup {
        private static final String TAG = "ContactGroupsClass";
        public String name;
        public long id;
        public List<ContactGroupMember> memberIdList;

        ContactGroup() {
            Log.d(TAG, "A new ContactGroup()");
        }
    }

    public class ContactGroupMember {
        private static final String TAG = "ContactGroupMembersClass";
        public String name;
        public String mobilePhoneNumber;
        public long id;

        ContactGroupMember() {
            Log.d(TAG, "A new ContactGroupMembersClass()");
        }
    }
}
