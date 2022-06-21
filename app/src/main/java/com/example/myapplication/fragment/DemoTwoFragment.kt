package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class DemoTwoFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("fragmentLifeCycle", "onAttach Method Call")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("fragmentLifeCycle", "onCreate Method Call")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("fragmentLifeCycle", "onCreateView Method Call")
        return inflater.inflate(R.layout.fragment_demo_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("fragmentLifeCycle", "onViewCreated Method Call")
    }

    override fun onStart() {
        super.onStart()
        Log.d("fragmentLifeCycle", "onStart Method Call")
    }

    override fun onResume() {
        super.onResume()
        Log.d("fragmentLifeCycle", "onResume Method Call")
    }

    override fun onPause() {
        super.onPause()
        Log.d("fragmentLifeCycle", "onPause Method Call")
    }

    override fun onStop() {
        super.onStop()
        Log.d("fragmentLifeCycle", "onStop Method Call")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("fragmentLifeCycle", "onDestroyView Method Call")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("fragmentLifeCycle", "onDestroy Method Call")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("fragmentLifeCycle", "onDetach Method Call")
    }

}