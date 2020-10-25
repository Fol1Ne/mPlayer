package com.example.mplayer.MainFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mplayer.R

class Adapter(val songList:ArrayList<Song>) : RecyclerView.Adapter<SongHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.song, parent, false)
        return SongHolder(view)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(songList[position])
    }


}