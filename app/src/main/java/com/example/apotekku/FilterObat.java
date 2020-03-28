package com.example.apotekku;

import android.animation.ObjectAnimator;
import android.widget.Filter;

import com.example.apotekku.Adapter.ObatAdapter;
import com.example.apotekku.DAO.ObatDAO;

import java.util.ArrayList;

public class FilterObat extends Filter{

    ObatAdapter adapter;
    ArrayList<ObatDAO> filterList;

    public FilterObat(ArrayList<ObatDAO> filterList, ObatAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<ObatDAO> filteredCabang=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_obat().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredCabang.add(filterList.get(i));
                }
            }
            results.count=filteredCabang.size();
            results.values=filteredCabang;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }


    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.result= (ArrayList<ObatDAO>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
