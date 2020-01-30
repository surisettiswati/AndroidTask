package com.example.androidtask.Products

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.example.androidtask.APIs.CustomVolleyFiles.MyVolley
import com.example.androidtask.APIs.interfaces.IParsePostListener
import com.example.androidtask.APIs.utils.CommonUtils
import com.example.androidtask.APIs.wsutils.JSONRequestResponse
import com.example.androidtask.APIs.wsutils.WSUtils
import com.example.androidtask.R
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class ProductsFragment : Fragment(), IParsePostListener {

    var mDataList: ArrayList<DataHolder> = ArrayList<DataHolder>()
    private lateinit var v: View
    private val PRODUCT_LIST_RESPONSE_CODE = 1
    private var mDataHolder: DataHolder? = null
    lateinit var mDataAdapter: RecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressbar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.products, container, false)
        setReferences()
        MyVolley.init(activity)
        callProductListWs()
        return v
    }

    @SuppressLint("WrongConstant")
    private fun setReferences() {
        progressbar = v.findViewById(R.id.progressbar)
        recyclerView = v.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

    }

    private fun callProductListWs() {

        val params = Bundle()
        val mObj = JSONRequestResponse()
        mObj.getResponseObject(
            WSUtils.WS_PRODUCTSLIST,
            PRODUCT_LIST_RESPONSE_CODE,
            this,
            params
        )


    }

    override fun ErrorResponse(error: VolleyError?, requestCode: Int) {

        if (CommonUtils.isNetworkAvailable(activity)) {
            Toast.makeText(activity, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(
                activity,
                getString(R.string.no_internet),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun SuccessResponse(response: String?, requestCode: Int) {
        when (requestCode) {
            PRODUCT_LIST_RESPONSE_CODE -> ProductlistRESPONSE_CODE(response)


        }

    }

    private fun ProductlistRESPONSE_CODE(response: String?) {


        try {
            progressbar.visibility = View.VISIBLE
            val jsonObject = JSONObject(response)
            var status = jsonObject.getString("status")
            if (status.equals("OK")) {
                val jsonArrayresult = JSONArray(jsonObject.getString("results"))
                if (jsonArrayresult.length() > 0) {
                    for (i in 0 until jsonArrayresult.length()) {
                        val jsonObject2 = jsonArrayresult.getJSONObject(i)
                        val jsonArray_Media = JSONArray(jsonObject2.getString("multimedia"))
                        if (jsonArray_Media.length() > 0) {
                            for (j in 0 until jsonArray_Media.length()) {
                                mDataHolder =
                                    DataHolder()
                                val jsonObject3 = jsonArray_Media.getJSONObject(j)
                                mDataHolder!!.Title = (jsonObject3.getString("copyright"))
                                mDataHolder!!.Tag = (jsonObject3.getString("format"))
                                mDataHolder!!.Image = (jsonObject3.getString("url"))
                            }
                        }
                        mDataList.add(mDataHolder!!)
                    }
                    showAdapter()
                    progressbar.visibility = View.GONE
                }
            } else {
                progressbar.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun showAdapter() {
        mDataAdapter =
            RecyclerViewAdapter(
                mDataList,
                activity
            )
        recyclerView.adapter = mDataAdapter
    }

}







