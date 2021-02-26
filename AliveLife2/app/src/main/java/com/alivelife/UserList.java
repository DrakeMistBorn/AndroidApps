package com.alivelife;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserList {
    List<UserItem> items;

    public UserList(){

        items = new ArrayList<UserItem>();
    }

    public List<UserItem> AddItem(UserItem protectee){
        items.add(protectee);
        return items;
    }

    public List<String> ToString(){ //convert to string just with the email
        List<String> list = new ArrayList<String>();
        int i;
        for (i = 0; i < items.size(); i++){
            list.add(this.items.get(i).toString());
        }
        return list;
    }

    public Set<String> ToStringSet(){ //convert to string just with the email
        Set<String> set = new HashSet<String>();
        int i;
        for (i = 0; i < items.size(); i++){
            set.add(this.items.get(i).toString());
        }
        return set;
    }



}
