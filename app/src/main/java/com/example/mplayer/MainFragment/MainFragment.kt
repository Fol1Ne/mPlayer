package com.example.mplayer.MainFragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mplayer.R
import java.io.Console
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment(), View.OnClickListener {
    private var songList: ArrayList<Song>? = null
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter
    lateinit var favoriteTracksButton: ImageView
    lateinit var playListsButton: ImageView
    lateinit var currentMusicButton: ImageView
    lateinit var textView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                activity?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    0
            )
        }

        songList = ArrayList()
        adapter = Adapter(songList!!)
        recyclerView = view.findViewById(R.id.recycler1)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        favoriteTracksButton = view.findViewById(R.id.imageButton)
        playListsButton = view.findViewById(R.id.imageButton2)
        currentMusicButton = view.findViewById(R.id.imageButton3)

        textView = view.findViewById(R.id.textView)

        favoriteTracksButton.setOnClickListener{textView.text = "Hello"}
        playListsButton.setOnClickListener(this)
        currentMusicButton.setOnClickListener(this)

        getSongList()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    fun getSongList(){
        //retrieve song info
        val musicResolver = requireActivity().contentResolver
        val musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val musicCursor = musicResolver.query(musicUri, null, null, null, null)
        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            val titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            //add songs to list
            do {
                val thisId = musicCursor.getLong(idColumn)
                val thisTitle = musicCursor.getString(titleColumn)
                val thisArtist = musicCursor.getString(artistColumn)
                songList!!.add(Song(thisId, thisTitle, thisArtist))
            } while (musicCursor.moveToNext())
        }
    }

    override fun onClick(v: View) {
        when(v.getId()){
            R.id.imageButton -> textView.text = "Hello" //findNavController(v).navigate(R.id.favoriteTracksFragmant)
        }
    }
}