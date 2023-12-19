package com.example.tpapi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addoffer: FloatingActionButton
    private lateinit var offerAdapter: OfferAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addoffer = findViewById(R.id.insert)
        recyclerView.layoutManager = LinearLayoutManager(this)
        offerAdapter = OfferAdapter(emptyList())
        recyclerView.adapter = offerAdapter

        addoffer.setOnClickListener {
            // Call the showInsertDialog method when the button is clicked
            showInsertDialog()
        }
        fetchData()
    }

    private fun fetchData() {
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            try {
                val response = ApiClient.apiService.getOffres()
                if (response.isSuccessful && response.body() != null) {
                    val offers = response.body()!!
                    offerAdapter.submitList(offers)
                } else {
                    Log.e("Error", response.message())
                    Log.e("Response Code", response.code().toString())
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
    fun showInsertDialog() {
        Log.d("MainActivity", "showInsertDialog called")
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.insert, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        val editTextCode = dialogView.findViewById<EditText>(R.id.editTextCode)
        val editTextIntitule = dialogView.findViewById<EditText>(R.id.editTextIntitule)
        val editTextSpecialite = dialogView.findViewById<EditText>(R.id.editTextSpecialite)
        val editTextSociete = dialogView.findViewById<EditText>(R.id.editTextSociete)
        val editTextNbPostes = dialogView.findViewById<EditText>(R.id.editTextNbPostes)
        val editTextPays = dialogView.findViewById<EditText>(R.id.editTextPays)
        val buttonInsert = dialogView.findViewById<Button>(R.id.add)
        buttonInsert.setOnClickListener {
            val code = editTextCode.text.toString().toInt()
            val intitule = editTextIntitule.text.toString()
            val specialite = editTextSpecialite.text.toString()
            val societe = editTextSociete.text.toString()
            val nbPostes = editTextNbPostes.text.toString().toInt()
            val pays = editTextPays.text.toString()
            val newOffer = Offer(code, intitule, specialite, societe, nbPostes, pays)

            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                try {
                    val response: Response<Offer> = ApiClient.apiService.saveOffre(newOffer)
                    if (response.isSuccessful && response.body() != null) {
                        val savedOffer = response.body()!!
                        offerAdapter.insert(savedOffer)
                        alertDialog.dismiss()

                        // Update the UI with the new data
                        offerAdapter.notifyDataSetChanged()
                    } else {
                        Log.e("Error", response.message())
                        Log.e("Response Code", response.code().toString())
                    }
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
        alertDialog.show()
    }
}




/*   val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            try{

                val updatedOffre = Offer(10,"Updated Healthcare Specialist", "Updated Healthcare", "Updated Health Solutions", 8, "Tunisia")

                val response: Response<Offer> = ApiClient.apiService.updateOffre(10, updatedOffre)
               // val newOffre = Offer(11, "Healthcare Specialist", "Healthcare",  "Health Solutions", 5, "Tunisia")
               // val response: Response<Offer> = ApiClient.apiService.saveOffre(newOffre)
              // val response = ApiClient.apiService.getOffres()
              //  val response = ApiClient.apiService.getOffres(5)
               //  val response =ApiClient.apiService.deleteOffre(5)
                if (response.isSuccessful && response.body() != null) {
                    text.setText(response.body().toString());
                    Log.i("Success",response.body().toString())

                }else{
                    text.setText("no")
                    Log.e("Error", response.message())
                    Log.e("Response Code", response.code().toString())
                }
            } catch (e: Exception) {

                Log.e("Error",e.message.toString())
            }
        }*/