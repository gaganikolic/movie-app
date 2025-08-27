package com.example.myapplication.example

interface IMyService {
    fun hello(): String
}

class MyService : IMyService{
    override fun hello(): String {
        return "Hello"
    }
}