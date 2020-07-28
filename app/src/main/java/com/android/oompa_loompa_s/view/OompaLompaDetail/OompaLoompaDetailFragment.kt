package com.android.oompa_loompa_s.view.OompaLompaDetail

import com.android.oompa_loompa_s.core.CoreFragment

interface OompaLoompaDetailFragment : CoreFragment {
    fun setPpalImage(image: String)
    fun setName(name: String)
    fun setEdad(edad: String)
    fun setPais(pais: String)
    fun setGenero(genero: String)
    fun setEmail(email: String)
    fun setProfesion(profesion: String)
    fun setColor(color: String)
    fun setComida(comida: String)
    fun setCancion(cancion: String)
    fun setRandomText(randomText: String)

}