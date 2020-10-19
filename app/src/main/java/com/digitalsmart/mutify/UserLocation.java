package com.digitalsmart.mutify;

import android.location.Address;
import android.location.Location;


//class defining UserLocation object
//todo: add more data to store in this class
public class UserLocation
{
    private String name ="";
    private Location location;
    private Address address;

    //todo: add time, date, and time duration

    public UserLocation(Location location)
    {
        this.location = location;
    }

    public UserLocation(String name)
    {
        this.name = name;
    }

    public UserLocation(String name, Location location)
    {
        this.name = name;
        this.location = location;
    }

    //this constructor initializes the address information
    public UserLocation(String name, Address a)
    {
        this.name = name;
        this.address = a;
    }


    public String getName()
    {
        return this.name;
    }

    public String getAddressLine()
    {
        if (address != null)
            return this.address.getAddressLine(0);
        return "no address info";
    }

    public String getCountry()
    {
        if (address != null)
            return this.address.getCountryName();
        return "no country info";
    }
}
