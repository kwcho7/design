package kr.co.cools.design.progress

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kr.co.cools.design.R

open class CircularProgress: View {
    var min: Int = 0
    var max: Int= 0
    private var value: Int= 0
    private var animator: ValueAnimator? = null

    private var colorArray = intArrayOf(Color.BLUE, Color.BLUE)
    private var position = floatArrayOf(0.0f, 1.0f)
    private var strokeWidth = 1f

    private val progressPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val progressBgPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
    }

    private var isNeedUpdateConfig = true


    constructor(context: Context?) : super(context) {
        context?.let {
            init(0, 100, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, it.resources.displayMetrics).toInt(), Color.BLUE, Color.GRAY)
        }

    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        ifNotNull(context, attrs) { _context, _attrs ->
            init(_context, _attrs)
        }

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        ifNotNull(context, attrs) { _context, _attrs ->
            init(_context, _attrs)
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ){
        ifNotNull(context, attrs) { _context, _attrs ->
            init(_context, _attrs)
        }
    }

    private fun init(context: Context, attrs: AttributeSet){

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularProgress)
        val minValue = typedArray.getInt(R.styleable.CircularProgress_minValue, 0)
        val maxValue = typedArray.getInt(R.styleable.CircularProgress_maxValue, 100)
        val strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            typedArray.getDimension(R.styleable.CircularProgress_strokeWidth, 10f),
            context.resources.displayMetrics
        ).toInt()
        val strokeColor = typedArray.getColor(R.styleable.CircularProgress_strokeColor, Color.BLUE)
        val strokeBgColor = typedArray.getColor(R.styleable.CircularProgress_strokeBgColor, Color.GRAY)
        typedArray.recycle()

        init(minValue, maxValue, strokeWidth, strokeColor, strokeBgColor)
    }

    private fun init(minValue: Int, maxValue: Int, strokeWidth: Int, strokeColor: Int, strokeBgColor: Int) {
        this.min = minValue
        this.max = maxValue
        this.strokeWidth = strokeWidth.toFloat()
        progressPaint.strokeWidth = strokeWidth.toFloat()
        progressBgPaint.strokeWidth = strokeWidth.toFloat()
        progressBgPaint.color = strokeBgColor

        colorArray = intArrayOf(strokeColor, strokeColor)
        position = floatArrayOf(0f, 1f)
    }

    fun setValue(value: Int){
        this.value = value
        invalidate()
    }

    fun setValue(value: Int, duration: Long){
        animator?.apply {
            removeAllUpdateListeners()
            cancel()
        }
        animator = ValueAnimator.ofInt(0, value).apply {
            this.duration = duration
            this.addUpdateListener {
                setValue(it.animatedValue as Int)
            }
            this.start()
        }
    }

    // max 360
    private fun getSweepAngle(): Float {
        return value * 360f / max
    }


    override fun draw(c: Canvas?) {
        super.draw(c)
        c?.also { canvas ->
            updateConfigIfNeed()
            canvas.drawArc(strokeWidth/2, strokeWidth/2, width.toFloat() - strokeWidth/2, height.toFloat() - strokeWidth/2, 270f, 360f, false, progressBgPaint)
            canvas.save()
            canvas.rotate(-90f, width/2f, height/2f)
            canvas.drawArc(strokeWidth/2, strokeWidth/2, width.toFloat() - strokeWidth/2, height.toFloat() - strokeWidth/2, 0f, getSweepAngle(), false, progressPaint)
            canvas.restore()

        }
    }

    private fun updateConfigIfNeed() {
        if(isNeedUpdateConfig){
            isNeedUpdateConfig = true
            progressPaint.shader = SweepGradient(width/2f, height/2f, colorArray, position)
            progressPaint.strokeWidth = strokeWidth
            progressBgPaint.strokeWidth = strokeWidth
        }
    }

    private inline fun <A, B, R> ifNotNull(a: A?, b:B?, function:(A, B) -> R) {
        if(a != null && b != null){
            function(a, b)
        }
    }

    fun setStrokeColors(intArrayOf: IntArray, floatArray: FloatArray) {
        colorArray = intArrayOf
        position = floatArray
        isNeedUpdateConfig = true
    }

}