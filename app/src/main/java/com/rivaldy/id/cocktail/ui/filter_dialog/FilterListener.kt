package com.rivaldy.id.cocktail.ui.filter_dialog

import com.rivaldy.id.core.data.model.local.FilterDataLocal

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

interface FilterListener {
    fun filterDrinkByName(filter: FilterDataLocal)
}