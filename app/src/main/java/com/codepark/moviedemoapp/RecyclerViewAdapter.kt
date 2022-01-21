package com.codepark.moviedemoapp

import android.content.Context
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepark.moviedemoapp.network.MovieListResponse

class RecyclerViewAdapter(context: Context, listOfMovies: List<MovieListResponse>, listener: OnClickListener): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private var movieList: List<MovieListResponse> = mutableListOf()
    private var mContext: Context? = null
    private var mListener: OnClickListener? = null
    init {
        movieList = listOfMovies
        mContext = context
        mListener = listener
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var image: ImageView = itemView.findViewById(R.id.thumbnail)
        private var title: TextView = itemView.findViewById(R.id.title)
        private var textSum: TextView = itemView.findViewById(R.id.summary)
        private val container: ConstraintLayout = itemView.findViewById(R.id.container)


        fun bindView(movie: MovieListResponse) {
            movie.show?.let {
                title.text = it.name
                textSum.text =
                    it.summary?.let { it1 -> HtmlCompat.fromHtml(it1, FROM_HTML_MODE_COMPACT) }
                mContext?.let { context -> Glide.with(context).load(it.image?.original).into(image) }

            }

            container.setOnClickListener {
                mListener?.navigateDetails(movie)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_view_model, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movieList[position]
         holder.bindView(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    interface OnClickListener {
        fun navigateDetails(movie: MovieListResponse)
    }
}