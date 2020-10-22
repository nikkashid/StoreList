package com.nikhil.storelistapp.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.nikhil.storelistapp.R
import com.nikhil.storelistapp.databinding.ActivityMainBinding
import com.nikhil.storelistapp.entities.StoreListResponse
import com.nikhil.storelistapp.ui.adapter.StoreAdapter
import com.nikhil.storelistapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StoreAdapter.IClickListener {

    private lateinit var mainActivity: ActivityMainBinding

    //Hilt ViewModel Injection
    private val homeViewModel: MainViewModel by viewModels()

    private lateinit var storeAdapter: StoreAdapter

    var backPressedCount: Int = 0

    var storeList: List<StoreListResponse.App> = ArrayList()

    lateinit var slideUpAnimation: Animation

    lateinit var slideDownAnimation: Animation

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        supportActionBar?.hide()
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)

        slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        slideDownAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down)

        lifecycleScope.launch {
            homeViewModel.getStoreList()
        }

        mainActivity.button.setOnClickListener {
            setUpSortView()
            mainActivity.button.isEnabled = false
        }

        homeViewModel.observeResponse().observe(this, { response ->
            storeAdapter = StoreAdapter(this)
            mainActivity.rvStoreList.layoutManager = LinearLayoutManager(this)
            mainActivity.rvStoreList.hasFixedSize()
            mainActivity.rvStoreList.adapter = storeAdapter
            storeList = response
            storeAdapter.submitList(storeList.sortedBy { it.data.total_sale.total })
        })

    }

    private fun setUpSortView() {
        backPressedCount++

        mainActivity.inSortBy.rlSortedMain.startAnimation(slideUpAnimation)
        mainActivity.inSortBy.rlSortedMain.visibility = View.VISIBLE

        mainActivity.inSortBy.rlSortedMain.setOnClickListener { onBackPressed() }

        mainActivity.inSortBy.llTs.setOnClickListener {
            storeAdapter.submitList(storeList.sortedBy { it.data.total_sale.total })
            removeImageVisibility()
            mainActivity.inSortBy.ivTs.visibility = View.VISIBLE
            onBackPressed()
        }

        mainActivity.inSortBy.llAtc.setOnClickListener {
            storeAdapter.submitList(storeList.sortedBy { it.data.add_to_cart.total })
            removeImageVisibility()
            mainActivity.inSortBy.ivAtc.visibility = View.VISIBLE
            onBackPressed()
        }

        mainActivity.inSortBy.llD.setOnClickListener {
            storeAdapter.submitList(storeList.sortedBy { it.data.downloads.total })
            removeImageVisibility()
            mainActivity.inSortBy.ivDownload.visibility = View.VISIBLE
            onBackPressed()
        }

        mainActivity.inSortBy.llUs.setOnClickListener {
            storeAdapter.submitList(storeList.sortedBy { it.data.sessions.total })
            removeImageVisibility()
            mainActivity.inSortBy.ivUs.visibility = View.VISIBLE
            onBackPressed()
        }
    }

    private fun removeImageVisibility() {
        mainActivity.inSortBy.ivAtc.visibility = View.INVISIBLE
        mainActivity.inSortBy.ivDownload.visibility = View.INVISIBLE
        mainActivity.inSortBy.ivTs.visibility = View.INVISIBLE
        mainActivity.inSortBy.ivUs.visibility = View.INVISIBLE
    }

    override fun onClickListener(storeItem: StoreListResponse.App) {

        backPressedCount++
        mainActivity.button.isEnabled = false

        mainActivity.inGraph.clGraphMain.setOnClickListener { onBackPressed() }

        mainActivity.inGraph.clGraphMain.startAnimation(slideUpAnimation)
        mainActivity.inGraph.clGraphMain.visibility = View.VISIBLE
        mainActivity.inGraph.tvCompanyName.text = storeItem.name
        mainActivity.inGraph.tvSaleCount.text = storeItem.data.total_sale.total.toString()

        mainActivity.inGraph.lcGraph.isDragEnabled = false
        mainActivity.inGraph.lcGraph.setScaleEnabled(false)

        val monthList: ArrayList<String> = ArrayList()
        monthList.add("0")
        monthList.add("Jan")
        monthList.add("Feb")
        monthList.add("Mar")
        monthList.add("Apr")
        monthList.add("May")
        monthList.add("Jun")

        val alGraphList: ArrayList<Entry> = ArrayList()
        alGraphList.add(Entry(1f, storeItem.data.total_sale.month_wise.jan.toFloat()))
        alGraphList.add(Entry(2f, storeItem.data.total_sale.month_wise.feb.toFloat()))
        alGraphList.add(Entry(3f, storeItem.data.total_sale.month_wise.mar.toFloat()))
        alGraphList.add(Entry(4f, storeItem.data.total_sale.month_wise.apr.toFloat()))
        alGraphList.add(Entry(5f, storeItem.data.total_sale.month_wise.may.toFloat()))
        alGraphList.add(Entry(6f, storeItem.data.total_sale.month_wise.jun.toFloat()))

        val set1 = LineDataSet(alGraphList, "Month")
        set1.fillAlpha = 110

        var dataSet = java.util.ArrayList<ILineDataSet>()
        dataSet.add(set1)

        val lineData = LineData(dataSet)
        mainActivity.inGraph.lcGraph.data = lineData

        set1.color = Color.GRAY
        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
        mainActivity.inGraph.lcGraph.description.text = ""
        mainActivity.inGraph.lcGraph.legend.isEnabled = false
        mainActivity.inGraph.lcGraph.invalidate()
        mainActivity.inGraph.lcGraph.axisRight.isEnabled = false
        mainActivity.inGraph.lcGraph.axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        mainActivity.inGraph.lcGraph.axisRight.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)

        val xAxis = mainActivity.inGraph.lcGraph.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = 4
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.valueFormatter = XAxisValueFormatter(monthList)

    }

    class XAxisValueFormatter(private val monthList: ArrayList<String>) : ValueFormatter() {

        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
            return value.toString()
        }

        override fun getAxisLabel(value: Float, axis: AxisBase): String {
            if (value.toInt() >= 0 && value.toInt() <= monthList.size - 1) {
                return monthList[value.toInt()]
            } else {
                return ("").toString()
            }
        }
    }

    override fun onBackPressed() {
        if (backPressedCount == 1) {
            backPressedCount--
            mainActivity.button.isEnabled = true
            if (mainActivity.inGraph.clGraphMain.isVisible) {
                mainActivity.inGraph.clGraphMain.startAnimation(slideDownAnimation)
                mainActivity.inGraph.clGraphMain.visibility = View.GONE
            }
            if (mainActivity.inSortBy.rlSortedMain.isVisible) {
                mainActivity.inSortBy.rlSortedMain.startAnimation(slideDownAnimation)
                mainActivity.inSortBy.rlSortedMain.visibility = View.GONE
            }
        } else {
            super.onBackPressed()
        }
    }
}