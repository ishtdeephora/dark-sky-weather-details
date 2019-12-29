package com.example.android.weatherdetails.remote.remoteexceptions

//Exception class for http code 404
class RequestedDataNotFoundException(errorMessage: String) : NoSuchElementException("No such element exception for $errorMessage")
