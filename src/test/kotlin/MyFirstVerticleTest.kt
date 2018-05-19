import io.vertx.core.Vertx
import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.junit.VertxUnitRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(VertxUnitRunner::class)
class MyFirstVerticleTest {

    private lateinit var vertx: Vertx

    @Before
    fun setUp(context: TestContext) {
        vertx = Vertx.vertx()
        vertx.deployVerticle(
                MyFirstVerticle::class.simpleName,
                context.asyncAssertSuccess() //Makes sure success before doing anything?
        )
    }

    @After
    fun tearDown(context: TestContext) {
        vertx.close(context.asyncAssertSuccess())
    }

    @Test
    fun testMyApplication(context: TestContext) {
        val async = context.async()

        vertx.createHttpClient().getNow(888, "localhost", "/") {
            it.handler {
                context.assertTrue(it.toString().contains("Hello"))
                async.complete()
            }
        }
    }
}