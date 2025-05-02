package com.example.campuspulse

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.campuspulse.AddEventActivity

class MainActivity : AppCompatActivity() {
    private lateinit var eventListView: ListView
    private lateinit var btnAddEvent: Button
    private val events = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eventListView = findViewById(R.id.eventListView)
        btnAddEvent = findViewById(R.id.btnAddEvent)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, events)
        eventListView.adapter = adapter

        btnAddEvent.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val newEvent = data?.getStringExtra("event")
            newEvent?.let {
                events.add(it)
                (eventListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            }
        }
    }
}
