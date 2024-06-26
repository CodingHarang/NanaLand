package com.jeju.nanaland.util.daterangecalendar.customviews

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff.Mode.SRC_IN
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.jeju.nanaland.R
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.DISABLE
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.END
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.HIDDEN
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.MIDDLE
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.SELECTABLE
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.START
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.START_END_SAME
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.OnDateClickListener
import com.jeju.nanaland.util.daterangecalendar.models.CalendarStyleAttrImpl
import com.jeju.nanaland.util.daterangecalendar.models.CalendarStyleAttributes
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Suppress("TooManyFunctions")
class CustomDateView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), DateView {

    private val tvDate: CustomTextView
    private val strip: View
    private val simpleDateFormat = SimpleDateFormat(CalendarDateRangeManager.DATE_FORMAT, Locale.getDefault())
    private val filterMode = SRC_IN

    private var onDateClickListener: OnDateClickListener? = null
    private var mDateState: DateState
    private val isRightToLeft = resources.getBoolean(R.bool.cdr_is_right_to_left)
    private val fontScale = resources.configuration.fontScale
    private val dpi = resources.configuration.densityDpi

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_calendar_day, this, true)
        tvDate = findViewById(R.id.dayOfMonthText)
        tvDate.layoutParams.height = 40 * resources.displayMetrics.widthPixels / 360
        tvDate.layoutParams.width = 40 * resources.displayMetrics.widthPixels / 360
        strip = findViewById(R.id.viewStrip)
        strip.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        strip.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        mDateState = SELECTABLE
        if (!isInEditMode) {
            setDateStyleAttributes(CalendarStyleAttrImpl.getDefAttributes(context))
            updateDateBackground(mDateState)
        }
    }

    private val defCalendarStyleAttr: CalendarStyleAttrImpl = CalendarStyleAttrImpl.getDefAttributes(context)
    override var dateTextSize: Float = defCalendarStyleAttr.textSizeDate / fontScale / dpi * 360
    override var defaultDateColor: Int = defCalendarStyleAttr.defaultDateColor
    override var disableDateColor: Int = defCalendarStyleAttr.disableDateColor
    override var selectedDateCircleColor: Int = defCalendarStyleAttr.selectedDateCircleColor
    override var selectedDateColor: Int = defCalendarStyleAttr.selectedDateColor
    override var rangeDateColor: Int = defCalendarStyleAttr.rangeDateColor
    override var stripColor: Int = defCalendarStyleAttr.rangeStripColor

    private val mViewClickListener = OnClickListener {
        val key = it.tag as Long
        if (onDateClickListener != null) {
            val selectedCal = Calendar.getInstance()
            var date = Date()
            try {
                date = simpleDateFormat.parse(key.toString())
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            selectedCal.time = date
            onDateClickListener?.onDateClicked(it, selectedCal)
        }
    }

    override fun setDateText(date: String) {
        tvDate.text = date
    }

    override fun setDateStyleAttributes(attr: CalendarStyleAttributes) {
        disableDateColor = attr.disableDateColor
        defaultDateColor = attr.defaultDateColor
        selectedDateCircleColor = attr.selectedDateCircleColor
        selectedDateColor = attr.selectedDateColor
        stripColor = attr.rangeStripColor
        rangeDateColor = attr.rangeDateColor
        tvDate.textSize = attr.textSizeDate
        refreshLayout()
    }

    override fun setTypeface(typeface: Typeface) {
        tvDate.typeface = typeface
    }

    override fun setDateTag(date: Calendar) {
        tag = DateView.getContainerKey(date)
    }

    override fun updateDateBackground(dateState: DateState) {
        mDateState = dateState
        when (dateState) {
            START, END, START_END_SAME -> makeAsSelectedDate(dateState)
            HIDDEN -> hideDayContainer()
            SELECTABLE -> enabledDayContainer()
            DISABLE -> disableDayContainer()
            MIDDLE -> makeAsRangeDate()
        }
    }

    override fun refreshLayout() {
        tvDate.textSize = 16f / fontScale * 160 / dpi * resources.displayMetrics.widthPixels / 360
    }

    override fun setDateClickListener(listener: OnDateClickListener) {
        onDateClickListener = listener
    }

    /**
     * To hide date if date is from previous month.
     */
    private fun hideDayContainer() {
        tvDate.text = ""
        tvDate.setBackgroundColor(Color.TRANSPARENT)
        strip.setBackgroundColor(Color.TRANSPARENT)
        setBackgroundColor(Color.TRANSPARENT)
        visibility = View.INVISIBLE
        setOnClickListener(null)
    }

    /**
     * To disable past date. Click listener will be removed.
     */
    private fun disableDayContainer() {
        tvDate.setBackgroundColor(Color.TRANSPARENT)
        strip.setBackgroundColor(Color.TRANSPARENT)
        setBackgroundColor(Color.TRANSPARENT)
        tvDate.setTextColor(disableDateColor)
        visibility = View.VISIBLE
        setOnClickListener(null)
    }

    /**
     * To enable date by enabling click listeners.
     */
    private fun enabledDayContainer() {
        tvDate.setBackgroundColor(Color.TRANSPARENT)
        strip.setBackgroundColor(Color.TRANSPARENT)
        setBackgroundColor(Color.TRANSPARENT)
        tvDate.setTextColor(defaultDateColor)
        visibility = View.VISIBLE
        setOnClickListener(mViewClickListener)
    }

    /**
     * To draw date container as selected as end selection or middle selection.
     *
     * @param state - DateState
     */
    private fun makeAsSelectedDate(state: DateState) {
        when (state) {
            START_END_SAME -> {
                val layoutParams = strip.layoutParams as LayoutParams
                strip.setBackgroundColor(Color.TRANSPARENT)
                layoutParams.setMargins(0, 0, 0, 0)
                strip.layoutParams = layoutParams
            }
            START -> {
                if (isRightToLeft) {
                    setRightFacedSelectedDate()
                } else {
                    setLeftFacedSelectedDate()
                }
            }
            END -> {
                if (isRightToLeft) {
                    setLeftFacedSelectedDate()
                } else {
                    setRightFacedSelectedDate()
                }
            }
            else -> {
                throw IllegalArgumentException("$state is an invalid state.")
            }
        }
        val mDrawable = ContextCompat.getDrawable(context, R.drawable.calendar_selected_circle)
        mDrawable!!.colorFilter = PorterDuffColorFilter(selectedDateCircleColor, filterMode)
        tvDate.background = mDrawable
        setBackgroundColor(Color.TRANSPARENT)
        tvDate.setTextColor(selectedDateColor)
        visibility = View.VISIBLE
        setOnClickListener(mViewClickListener)
    }

    private fun setLeftFacedSelectedDate() {
//        strip.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//        strip.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        val layoutParams = strip.layoutParams as LayoutParams
        val drawable = ContextCompat.getDrawable(context, R.drawable.range_bg_left)
        drawable!!.colorFilter = PorterDuffColorFilter(stripColor, filterMode)
        strip.background = drawable
        layoutParams.setMargins(MARGIN_LEFT, 0, 0, 0)
        strip.layoutParams = layoutParams
    }

    private fun setRightFacedSelectedDate() {
//        strip.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//        strip.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        val layoutParams = strip.layoutParams as LayoutParams
        val drawable = ContextCompat.getDrawable(context, R.drawable.range_bg_right)
        drawable!!.colorFilter = PorterDuffColorFilter(stripColor, filterMode)
        strip.background = drawable
        layoutParams.setMargins(0, 0, MARGIN_RIGHT, 0)
        strip.layoutParams = layoutParams
    }

    /**
     * To draw date as middle date
     */
    private fun makeAsRangeDate() {
        strip.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        strip.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        tvDate.setBackgroundColor(Color.TRANSPARENT)
        val mDrawable = ContextCompat.getDrawable(context, R.drawable.range_bg)
        mDrawable!!.colorFilter = PorterDuffColorFilter(stripColor, filterMode)
        strip.background = mDrawable
        setBackgroundColor(Color.TRANSPARENT)
        tvDate.setTextColor(rangeDateColor)
        visibility = View.VISIBLE
        val layoutParams = strip.layoutParams as LayoutParams
        layoutParams.setMargins(0, 0, 0, 0)
        strip.layoutParams = layoutParams
        setOnClickListener(mViewClickListener)
    }

    companion object {
        private const val MARGIN_RIGHT = 12
        private const val MARGIN_LEFT = 12
    }
}
