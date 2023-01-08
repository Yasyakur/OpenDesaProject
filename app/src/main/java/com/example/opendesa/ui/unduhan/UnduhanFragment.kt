package com.example.opendesa.ui.unduhan

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.example.opendesa.R
import com.example.opendesa.databinding.FragmentUnduhanBinding
import com.example.opendesa.model.UnduhanItem
import com.example.opendesa.model.UnduhanModel
import com.example.opendesa.repository.Repository
import com.example.opendesa.ui.home.HomeViewModelFactory
import com.example.opendesa.ui.unduhan.viewModel.UnduhanViewModel
import com.example.opendesa.util.Constants.Companion.BASE_URL
import com.example.opendesa.util.Utils
import okhttp3.ResponseBody


const val TAG = "UnduhanFragment"

class UnduhanFragment : Fragment(), OnRVClickListener, OnUnduhanRVClickListener {

    private lateinit var selectorAdapter: UnduhanSelectorAdapter
    private lateinit var dataAdapter : UnduhanItemAdapter
    private var _binding: FragmentUnduhanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UnduhanViewModel



    private val nameEnum: String by lazy {
        findNavController().currentDestination?.label.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUnduhanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory)[UnduhanViewModel::class.java]
        viewModel.getUnduhanData(nameEnum)
        initSelector()
        initViewModelListener()

    }

    private fun initViewModelListener() {
        viewModel.onGetUnduhanData.observe(
            viewLifecycleOwner, ::onGetUnduhanData
        )
        viewModel.onGetErrorMessage.observe(viewLifecycleOwner,::onGetErrorMessage)

        viewModel.loadingState.observe(viewLifecycleOwner,::onLoading)
    }


    private fun onLoading(isLoading: Boolean) {
        if (isLoading){
           binding.linearProgress.visibility = View.VISIBLE
        }else{
            binding.linearProgress.visibility = View.GONE
        }
    }

    private fun onGetErrorMessage(message: String?) {
        AlertDialog.Builder(requireContext())
            .setTitle("Terjadi Error")
            .setMessage(message)
            .setPositiveButton("Oke"){dialog,_->
               dialog.dismiss()
            }.show()
    }

    private fun onGetUnduhanData(data: UnduhanModel) {
        dataAdapter = UnduhanItemAdapter(this)
        binding.rvUnduhan.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = dataAdapter
            data.data?.let { dataAdapter.setData(it) }
        }
        Log.d(TAG,data.data.toString())
    }


    private fun initSelector() {
        selectorAdapter = UnduhanSelectorAdapter( this)
        binding.unduhanSelector.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = selectorAdapter
            selectorAdapter.setData(UnduhanType.values().map { it.name })

        }

        Log.d(TAG, nameEnum)
        when (UnduhanType.valueOf(nameEnum.uppercase())) {
            UnduhanType.PROSEDUR -> selectorAdapter.setSelectedItem(0)
            UnduhanType.REGULASI -> selectorAdapter.setSelectedItem(1)
            UnduhanType.DOKUMEN -> selectorAdapter.setSelectedItem(2)
        }

    }

    override fun onClick(position: Int, typeText: String) {
        selectorAdapter.setSelectedItem(position)
        Toast.makeText(
            requireContext(),
            "$typeText ${findNavController().currentDestination?.label}",
            Toast.LENGTH_SHORT
        ).show()
        when (UnduhanType.valueOf(typeText)) {
            UnduhanType.DOKUMEN -> findNavController().navigate(R.id.nav_dokumen)
            UnduhanType.PROSEDUR -> findNavController().navigate(R.id.nav_prosedur)
            UnduhanType.REGULASI -> findNavController().navigate(R.id.nav_regulasi)
        }

    }

    override fun onUnduhanClick(position: Int, unduhanItem: UnduhanItem) {
        val url = "$BASE_URL/api/unduhan/${unduhanItem.id}"
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
        val filename = unduhanItem.file?.substringAfter('/')
        Log.d("PAYLOAD" , "$url---$dir---$filename")
        val download = PRDownloader.download(url,dir,filename)
            .build()
            .setOnStartOrResumeListener {
                binding.loadingOverlay.visibility = View.VISIBLE
            }.setOnProgressListener { progress->
                binding.loadingProgress.text ="Downloading ${Utils.getProgressDisplayLine(progress.currentBytes,progress.totalBytes)}/"
            }.start(object : OnDownloadListener{
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onDownloadComplete() {
                    Toast.makeText(requireContext(), "Download complete", Toast.LENGTH_SHORT).show()
                    binding.loadingOverlay.visibility = View.GONE
                    AlertDialog.Builder(requireContext())
                        .setTitle("Download selesai")
                        .setMessage("File $filename telah selesai di download, Apakah ingin membuka folder download?")
                        .setPositiveButton("Buka folder"){_,_->
                            val path = "/storage/emulated/0/Downloads/"
                            val uri = Uri.parse(path)
                            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                            intent.setDataAndType(uri, "*/*")
                            startActivity(intent)
                        }.setNegativeButton("Nanti"){gi,_->
                            gi.dismiss()
                        }.show()
                }

                override fun onError(error: Error?) {
                    Toast.makeText(requireContext(), "Download error -- (${error?.responseCode})", Toast.LENGTH_SHORT).show()
                    binding.loadingOverlay.visibility = View.GONE
                }

            })

    }


}