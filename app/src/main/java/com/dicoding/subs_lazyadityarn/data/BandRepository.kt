package com.dicoding.subs_lazyadityarn.data

import com.dicoding.subs_lazyadityarn.model.Band
import com.dicoding.subs_lazyadityarn.model.BandData

class BandRepository {
    fun getBands(): List<Band> {
        return BandData.detBands
    }

}