package com.digitalsmart.mutify.model

import android.location.Address
import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.digitalsmart.mutify.util.Constants
import com.google.android.gms.location.Geofence
import com.google.android.gms.maps.model.LatLng


@Entity
class UserLocation
{
    //unique id for geofencing object
    @PrimaryKey
    var id: String

    @ColumnInfo(name = "name")
    var name = ""

    @ColumnInfo(name = "radius")
    var radius = 0f

    @ColumnInfo(name = "delay")
    var delay = 30000

    @ColumnInfo(name = "latitude")
    var latitude: Double

    @ColumnInfo(name = "longitude")
    var longitude: Double

    @ColumnInfo(name = "transition")
    var transition: Int = Geofence.GEOFENCE_TRANSITION_EXIT

    @ColumnInfo(name = "address_line", defaultValue = "no address info")
    var addressLine = "address info not available"

    @ColumnInfo(name = "country", defaultValue = "no country info")
    var country = "country info not available"

    @ColumnInfo(name = "postal_code", defaultValue = "unknown zip code")
    var postalCode = "unknown zip code"


    val latLng: LatLng get() = LatLng(latitude, longitude)

    val geofence: Geofence get() =
        Geofence.Builder()
                .setRequestId(id)
                .setCircularRegion(latitude, longitude, radius)
                .setLoiteringDelay(delay) //delay for dwelling transition
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(transition or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()





    //sqlite constructor
    constructor(name: String, radius: Float, delay: Int, latitude: Double, longitude: Double, addressLine: String, country: String, postalCode: String)
    {
        this.name = name
        this.radius = radius
        this.delay = delay
        this.latitude = latitude
        this.longitude = longitude
        this.addressLine = addressLine
        this.country = country
        this.postalCode = postalCode
        this.id = Constants.PACKAGE_NAME + this.latitude + "_" + this.longitude
    }


    //standard constructor
    constructor(name: String, location: Location)
    {
        this.name = name
        latitude = location.latitude
        longitude = location.longitude
        id = Constants.PACKAGE_NAME + latitude + "_" + longitude
    }

    //save only the address line, country, and postal code
    fun updateAddress(a: Address)
    {
        addressLine = a.getAddressLine(0)
        if (a.countryName != null)
            country = a.countryName
        if (a.postalCode != null)
            postalCode = a.postalCode
    }
}