package com.alivelife;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ProtecteeList {
    List<ProtecteeItem> items;

    public ProtecteeList(){
        items = new ArrayList<>();
    }

    public List<ProtecteeItem> AddItem(ProtecteeItem protectee){
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

}
