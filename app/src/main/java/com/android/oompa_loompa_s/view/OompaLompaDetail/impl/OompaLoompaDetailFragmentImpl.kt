package com.android.oompa_loompa_s.view.OompaLompaDetail.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.oompa_loompa_s.R
import com.android.oompa_loompa_s.application.OompaLoompaApplication
import com.android.oompa_loompa_s.core.CoreFragment
import com.android.oompa_loompa_s.core.impl.CoreFragmentImpl
import com.android.oompa_loompa_s.presenter.OompaLoompaDetailFragmentPresenterImpl
import com.android.oompa_loompa_s.view.OompaLompaDetail.OompaLoompaDetailFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class OompaLoompaDetailFragmentImpl : CoreFragmentImpl(), OompaLoompaDetailFragment {

    @Inject
    lateinit var oompaLoompaDetailFragmentPresenter: OompaLoompaDetailFragmentPresenterImpl

    //region Lifcycle Fragment
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // View is created so postpone the transition for now
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        (requireActivity().application as OompaLoompaApplication).oompaLoompaComponent.inject(this)
        oompaLoompaDetailFragmentPresenter.setView(this)
        initializeIdFromBundle()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oompaLoompaDetailFragmentPresenter.onViewBinded()
    }

    private fun initializeIdFromBundle() {
        val bundle = arguments
        if (bundle != null) {
            oompaLoompaDetailFragmentPresenter.setId(bundle.getLong("id"))
        }
    }

    //endregion Lifcycle Fragment

    //region Core
    override fun onInitLoading() {
        view?.visibility = View.GONE
    }

    override fun onLoaded() {
        view?.visibility = View.VISIBLE
    }
    //endregion Core

    //region set View

    override fun setPpalImage(image: String) {
        Picasso.get().load(image).into(collapsinImage)
    }

    override fun setName(name: String) {
        toolbarTitle.setTitle(name)
    }

    override fun setEdad(edad: String) {
        textViewAge.text = edad
    }

    override fun setPais(pais: String) {
        textViewPais.text = pais
    }

    override fun setGenero(genero: String) {
        textViewGenero.text = genero
    }

    override fun setEmail(email: String) {
        textViewCorreo.text = email
    }

    override fun setProfesion(profesion: String) {
        textViewProfesion.text = profesion
    }

    override fun setColor(color: String) {
        textViewColorFavorite.text = color
    }

    override fun setComida(comida: String) {
        textViewFoodFavorite.text = comida
    }

    override fun setCancion(cancion: String) {
        textViewSongFavorite.text = cancion
    }

    override fun setRandomText(randomText: String) {
        textViewRandomString.text = randomText
    }
    //endregion set View
}