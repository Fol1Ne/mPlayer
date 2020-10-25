package com.example.mplayer.MainFragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mplayer.R

class SongHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val image = itemView.findViewById<ImageView>(R.id.imageView2)
    val title = itemView.findViewById<TextView>(R.id.textView4)
    val artist = itemView.findViewById<TextView>(R.id.textView5)

    fun bind(song : Song){
        title.text = song.title
        artist.text = song.artist
    }
}
