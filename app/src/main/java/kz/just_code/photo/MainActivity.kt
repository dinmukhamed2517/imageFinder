package kz.just_code.photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kz.just_code.photo.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val unsplashBaseUrl = "https://source.unsplash.com/800x800/?"
    private var currentImageQuery = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.search.setOnClickListener{
            val searchQuery = binding.inputView.text.toString().trim()

            if (searchQuery.isNotEmpty()) {
                currentImageQuery = searchQuery
                displayImageBasedOnQuery(currentImageQuery)
            } else {
                Toast.makeText(this, "Input is wrong", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnLike.setOnClickListener {
            displayImageBasedOnQuery(currentImageQuery)
        }
        binding.btnDislike.setOnClickListener {
            displayImageBasedOnQuery(currentImageQuery)

        }
    }

    private fun displayImageBasedOnQuery(query: String) {
        val imageUrl = "$unsplashBaseUrl$query"

        // Disable caching in Picasso to ensure a fresh image load
        Picasso.get()
            .load(imageUrl)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(binding.imageView)
    }

}


