package com.murat.hashtag_hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.murat.hashtag_hw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TagsAdapter.OnItemClickListener {


    private var filterTags: ArrayList<String> = ArrayList()

    private var tags: ArrayList<String> = ArrayList()
    lateinit var binding: ActivityMainBinding
    private var adapter = TagsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.setListener(this)

        binding.btnSend.setOnClickListener {
            saveTag()
        }

        binding.etMessage.addTextChangedListener(object : MyTextChangedListener() {
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(text, start, before, count)

                if (!text.isNullOrEmpty()) {
                    if (text.startsWith("#")) {
                        filterTags =
                            tags.filter { it.contains(text.toString()) } as ArrayList<String>
                        adapter.setTagsList(filterTags)
                        binding.rvMessage.adapter = adapter
                    }


                } else filterTags.clear()

                binding.rvMessage.isVisible = filterTags.isNotEmpty()

            }
        })
    }

    private fun saveTag() {
        val data: String = binding.etMessage.text.toString()
        val wor = data.split(" ")
        wor.map {
            if (it.startsWith("#")) {
                tags.add(it)
            }
        }

        binding.etMessage.text.clear()
        binding.rvMessage.isVisible = false
    }

    override fun onItemClick(tags: String) {
       binding.etMessage.setText(tags)
        binding.rvMessage.isVisible = false
    }
}