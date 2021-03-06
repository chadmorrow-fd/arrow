package arrow.data

import arrow.mtl.monadReader
import arrow.test.UnitSpec
import arrow.test.laws.MonadLaws
import arrow.typeclasses.Eq
import arrow.typeclasses.applicative
import arrow.typeclasses.functor
import arrow.typeclasses.monad
import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.shouldNotBe
import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class)
class Function1Test : UnitSpec() {
    val EQ: Eq<Function1Of<Int, Int>> = Eq { a, b ->
        a(1) == b(1)
    }

    init {

        "instances can be resolved implicitly" {
            functor<Function1PartialOf<Int>>() shouldNotBe null
            applicative<Function1PartialOf<Int>>()  shouldNotBe null
            monad<Function1PartialOf<Int>>()  shouldNotBe null
            monadReader<Function1PartialOf<Int>, Int>()  shouldNotBe null
        }

        testLaws(MonadLaws.laws(Function1.monad<Int>(), EQ))
    }
}