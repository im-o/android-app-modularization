import kotlin.reflect.full.memberProperties

/**
 * Created by rivaldy on 03/02/22.
 * Find me on my Github -> https://github.com/im-o
 */

object Modules {
    const val base = ":base"
    const val app = ":app"
    const val core = ":core"
    const val testFeature = ":features:test_feature"

    fun getAllModules() = Modules::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()
}