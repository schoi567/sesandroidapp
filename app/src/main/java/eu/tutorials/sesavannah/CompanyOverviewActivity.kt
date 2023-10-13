package eu.tutorials.sesavannah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CompanyOverviewActivity : AppCompatActivity(), OnMapReadyCallback  {
    private var mMap: GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Coordinates for New York City
        val newYork = LatLng(40.7128, -74.0060)


        // Move camera to the specified location
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 10F))

        // Optionally, add a marker on the location
        mMap?.addMarker(MarkerOptions().position(newYork).title("New York City"))
    }

}
