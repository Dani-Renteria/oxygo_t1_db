package com.example.oxygo_t1_fb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.oxygo_t1_fb.databinding.ActivityMainBinding
import com.example.oxygo_t1_fb.ui.theme.Oxygo_t1_fbTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
//    private lateinit var firebaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.signupFragment))

        setupActionBarWithNavController(this, navController, appBarConfiguration)


        /*firebaseRef = FirebaseDatabase.getInstance().getReference("test")

        binding.tvSendData.setOnClickListener{

                firebaseRef.setValue("Test completed")
                    .addOnCompleteListener {
                        Toast.makeText(this, "data stored successfully", Toast.LENGTH_SHORT).show()
                    }
        }*/

    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Oxygo_t1_fbTheme {
        Greeting("Android")
    }
}*/
